package com.joe.engine.graphics;

import com.joe.engine.graphics.color.HSL;
import com.joe.engine.graphics.color.HSV;
import com.joe.engine.graphics.color.RGB16BitInteger;

public abstract class Renderable {
	/*
	 * I really like cherno's idea of a screen class. I have heavily taken over
	 * this as you can tell.
	 * 
	 * @Credits to cherno's videos for the basics.
	 */
	private int width;
	private int height;
	private int x;
	private int y;
	protected int[] pixels;
	private int[] emptyScreen;

	/**
	 * Creates a renderable based on dimensions.
	 * 
	 * @param x
	 *            X position in parent.
	 * @param y
	 *            Y position in parent.
	 * @param width
	 *            Width of renderable.
	 * @param height
	 *            Height of renderable.
	 */
	public void initialize(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.pixels = new int[width * height];
		this.emptyScreen = new int[width * height];

		for (int i = 0; i < this.pixels.length; i++) {
			this.emptyScreen[i] = RGB16BitInteger.BLACK;
		}
	}

	/**
	 * Creates a renderable based on dimensions.
	 * 
	 * @param width
	 *            Width of renderable.
	 * @param height
	 *            Height of renderable.
	 */
	public void initialize(int width, int height) {
		initialize(0, 0, width, height);
	}

	/**
	 * Sets a pixel in the pixels array.
	 * 
	 * @param index
	 *            Index in pixel array.
	 * 
	 * @param rgb
	 *            RGB Color of pixel.
	 */
	public void setPixel(int index, int rgb) {
		/*
		 * Check index bounds.
		 */
		if (index < 0 || index > pixels.length - 1)
			return;
		this.pixels[index] = rgb;
	}

	/**
	 * Clears pixels on renderable. Sets all current pixels to black.
	 */
	public void clear() {
		System.arraycopy(emptyScreen, 0, this.pixels, 0, this.pixels.length);
	}

	/**
	 * Draws a pixel on the renderable. Cannot exceed boundary's.
	 * 
	 * @param RGB
	 *            RGB Integer of pixel.
	 * @param x
	 *            X position on renderable.
	 * @param y
	 *            Y position on renderable.
	 */
	public void drawPixel(int RGB, int x, int y) {
		/*
		 * Check location bounds.
		 */
		if (x < 0 || x > getWidth() - 1 || y < 0 || y > getHeight() - 1) {
			return;
		}
		int pixel_index = x + (y * getWidth());
		setPixel(pixel_index, RGB);
	}

	/**
	 * Sets RGB of all other pixels. Ignores the transparency pixels.
	 * 
	 * @param RGB
	 *            RGB Integer to set other pixels to.
	 */
	public void setRGB(int RGB) {
		for (int i = 0; i < pixels.length; i++) {
			int color = pixels[i];

			if (isTransparentColor(color)) {
				continue;
			}
			setPixel(i, RGB);
		}
	}

	/**
	 * Offsets the renderable's RGB
	 * 
	 * @param redOff
	 *            Offset to change red component.
	 * 
	 * @param greenOff
	 *            Offset to change green component.
	 * 
	 * @param blueOff
	 *            Offset to change blue component.
	 */
	public void offsetRenderableRGB(int redOff, int greenOff, int blueOff) {
		for (int i = 0; i < pixels.length; i++) {
			int rgb = pixels[i];
			if (this.isTransparentColor(rgb)) {
				continue;
			}
			setPixel(i,
					RGB16BitInteger.offsetRGB(rgb, redOff, greenOff, blueOff));
		}
	}

	/**
	 * Offsets the renderable's HSL
	 * 
	 * @param hueOff
	 *            Offset to change hue component.
	 * 
	 * @param satOff
	 *            Offset to change saturation component.
	 * 
	 * @param lightOff
	 *            Offset to change lighting component.
	 */
	public void offsetRenderableHSL(int hueOff, float satOff, float lightOff) {
		for (int i = 0; i < pixels.length; i++) {
			int rgb = pixels[i];
			if (this.isTransparentColor(rgb)) {
				continue;
			}
			setPixel(i, HSL.offsetHSL(rgb, hueOff, satOff, lightOff));
		}
	}

	public void offsetHSL(int x, int y, int rgb, int hueOff, float satOff,
			float lightOff) {
		drawPixel(HSL.offsetHSL(rgb, hueOff, satOff, lightOff), x, y);
	}

	public void offsetHSV(int x, int y, int rgb, float hueOff, float satOff,
			float value) {
		drawPixel(HSV.offsetHSV(rgb, hueOff, satOff, value), x, y);
	}

	public void offsetRGB(int x, int y, int RGB, byte redOffset,
			byte greenOffset, byte blueOffset) {
		drawPixel(RGB16BitInteger.offsetRGB(RGB, redOffset, greenOffset,
				blueOffset), x, y);
	}

	public int getRGB(int x, int y) {
		/*
		 * Check location bounds.
		 */
		if (x < 0 || x > getWidth() - 1) {
			return 0;
		}
		if (y < 0 || y > getHeight() - 1) {
			return 0;
		}
		/*
		 * Check index bounds.
		 */
		int pixel_index = x + (y * getWidth());

		if (pixel_index < 0 || pixel_index > pixels.length - 1) {
			return 0;
		}
		return pixels[x + (y * getWidth())];
	}
	

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return width. Width of renderable.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return height Height of renderable.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 
	 * @return x position in parent.
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @return y position in parent.
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return pixels Pixels on renderable.
	 */
	public int[] getPixels() {
		return pixels;
	}

	public boolean isTransparentColor(int RGB) {
		return RGB == RGB16BitInteger.FUCHSIA || RGB == -65281;
	}
}
