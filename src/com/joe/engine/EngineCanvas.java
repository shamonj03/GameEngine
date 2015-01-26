package com.joe.engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import com.joe.engine.graphics.renderable.Screen;
import com.joe.engine.input.Keyboard;
import com.joe.engine.input.Mouse;
import com.joe.engine.util.EngineConstants;

@SuppressWarnings("serial")
public abstract class EngineCanvas extends Canvas {

	private final Mouse mouseListener = new Mouse();
	private final Keyboard keyListener = new Keyboard();

	private Screen screen;
	private BufferedImage screenImage;
	private int[] pixels;

	private boolean running = true;
	private boolean paused = false;

	private boolean showFps = true;

	private float interpolation;

	private BufferStrategy buffer;

	private int displayedUpdates = 60;
	private int displayedFps = 60;
	
	private double targetFps = EngineConstants.FRAME_RATE_UNLIMITED;

	/*
	 * Set hardware accelerated graphics setting before startup.
	 */
	static {
		System.setProperty("sun.java2d.opengl", "true");
	}

	/**
	 * Creates a new canvas to draw two that has mouse and keyboard actions
	 * bound to it.
	 * 
	 * @param width
	 *            Width of canvas.
	 * 
	 * @param height
	 *            Height of canvas.
	 */
	public EngineCanvas(int width, int height) {
		this.setSize(new Dimension(width, height));
		this.setPreferredSize(new Dimension(width, height));
		this.setFocusable(true);

		this.setBackground(Color.WHITE);

		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
		this.addKeyListener(keyListener);
		this.setIgnoreRepaint(true);

		screen = new Screen(width, height);

		screenImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) screenImage.getRaster().getDataBuffer())
				.getData();
	}

	/**
	 * Things to do before the game starts up goes here.
	 */
	public abstract void startUp();

	/**
	 * All game logic such as moving collision detection physic etc are handled
	 * here.
	 */
	public abstract void updateGame();

	/**
	 * Anything the game is going to draw is handled in here.
	 */
	public abstract void drawScreen(Screen screen);

	/**
	 * Uses a bufferer strategy 3 layers deep for screen flipping. Similar to
	 * double buffering.
	 */
	private void processGraphics() {
		do {
			do {
				Graphics2D off_g = (Graphics2D) buffer.getDrawGraphics();
				try {
					render(off_g);
				} finally {
					off_g.dispose();
				}
			} while (buffer.contentsRestored());
			buffer.show();
		} while (buffer.contentsLost());
	}

	/**
	 * render the game.
	 * 
	 * @param g
	 */
	private void render(Graphics2D g) {
		screen.clear();

		drawScreen(screen);

		System.arraycopy(screen.getPixels(), 0, pixels, 0, pixels.length);
		
		g.drawImage(screenImage, 0, 0, getWidth(), getHeight(), this);

		if (showFps) {
			g.setColor(Color.ORANGE);
			g.drawString("(FPS: " + displayedFps + " Updates: " + displayedUpdates + ")", 15, 15);
		}
	}

	private void createBufferStrategy() {
		createBufferStrategy(EngineConstants.BUFFER_STRATEGY_LAYERS);
		buffer = getBufferStrategy();
	}

	/**
	 * Starts a new thread to run the game loop in.
	 */
	public void start() {
		createBufferStrategy();
		startUp();
		Thread loop = new Thread() {
			public void run() {
				gameLoop();
			}
		};
		loop.start();
	}

	/**
	 * @author Eli Delventhal
	 * {@link http://www.java-gaming.org/index.php?topic=24220.0}
	 * 
	 * I prefer this loop over chernos. Chernos seemed very
	 * basic.
	 */
	private void gameLoop() {
		// This value would probably be stored elsewhere.
		final double GAME_HERTZ = 60.0;
		// Calculate how many ns each frame should take for our target game
		// hertz.
		final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
		// At the very most we will update the game this many times before a new
		// render.
		// If you're worried about visual hitches more than perfect timing, set
		// this to 1.
		final int MAX_UPDATES_BEFORE_RENDER = 5;
		// We will need the last update time.
		double lastUpdateTime = System.nanoTime();
		// Store the last time we rendered.
		double lastRenderTime = System.nanoTime();

		// If we are able to get as high as this FPS, don't render again.
		final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / getTargetFps();

		// Simple way of finding FPS.
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);

		int upz = 0;
		int frameCount = 0;
		
		while (running) {
			double now = System.nanoTime();
			int updateCount = 0;

			if (!paused) {
				// Do as many game updates as we need to, potentially playing
				// catchup.
				while (now - lastUpdateTime > TIME_BETWEEN_UPDATES
						&& updateCount < MAX_UPDATES_BEFORE_RENDER) {
					updateGame();
					
					lastUpdateTime += TIME_BETWEEN_UPDATES;
					updateCount++;
					upz++;
				}

				// If for some reason an update takes forever, we don't want to
				// do an insane number of catchups.
				// If you were doing some sort of game that needed to keep EXACT
				// time, you would get rid of this.
				if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
					lastUpdateTime = now - TIME_BETWEEN_UPDATES;
				}

				// Render. To do so, we need to calculate interpolation for a
				// smooth render.
				float interpolation = Math
						.min(1.0f,
								(float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES));

				setInterpolation(interpolation);
				processGraphics();
				lastRenderTime = now;
				frameCount++;
				// Update the frames we got.
				int thisSecond = (int) (lastUpdateTime / 1000000000);
				if (thisSecond > lastSecondTime) {
					displayedFps = frameCount;
					displayedUpdates = upz;
					frameCount = 0;
					upz = 0;
					lastSecondTime = thisSecond;
				}

				// Yield until it has been at least the target time between
				// renders. This saves the CPU from hogging.
				while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS
						&& now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
					Thread.yield();

					// This stops the app from consuming all your CPU. It makes
					// this slightly less accurate, but is worth it.
					// You can remove this line and it will still work (better),
					// your CPU just climbs on certain OSes.
					// FYI on some OS's this can cause pretty bad stuttering.
					// Scroll down and have a look at different peoples'
					// solutions to this.
					try {
						Thread.sleep(1);
					} catch (Exception e) {
					}

					now = System.nanoTime();
				}
			}
		}
	}

	public void setInterpolation(float interpolation) {
		this.interpolation = interpolation;
	}

	public float getInterpolation() {
		return interpolation;
	}

	public void setShowFps(boolean showFps) {
		this.showFps = showFps;
	}

	public boolean isShowFps() {
		return showFps;
	}

	public Screen getScreen() {
		return screen;
	}
	
	public void setTargetFps(double targetFps) {
		this.targetFps = targetFps;
	}
	
	public double getTargetFps() {
		return targetFps;
	}
}
