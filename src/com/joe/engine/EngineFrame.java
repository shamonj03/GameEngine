package com.joe.engine;

import java.awt.Frame;

import com.joe.engine.input.Window;

public abstract class EngineFrame<T extends EngineCanvas> {
	/**
	 * The canvas we are going to be drawing to.
	 */
	private T canvas;
	
	/**
	 * A method used for setting any additional components
	 * the AWT Components the frame may have.
	 * 
	 * @param frame
	 * 		The frame used for the engine.
	 */
	public abstract void setAWTComponenets(Frame frame);
	
	/**
	 * Create a new frame.
	 * 
	 * @param name
	 * 		Name of the frame.
	 * 
	 * @param canvas
	 * 		The canvas to draw to.
	 */
	public void create(String name, T canvas) {
		Frame frame = new Frame(name);
		
		this.canvas = canvas;
		
		
		frame.setResizable(false);
		frame.addWindowListener(new Window());
		
		setAWTComponenets(frame);
		
		frame.add(this.canvas);
		
		frame.pack();
		frame.setVisible(true);
		
		frame.pack();
		
		this.canvas.start();
	}
	
	/**
	 * @return the canvas we will be drawing to.
	 */
	public T getCanvas() {
		return canvas;
	}
}
