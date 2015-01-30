package com.joe.engine.graphics.renderable;

import java.util.ArrayList;

public abstract class ParentInterface extends DrawingArea {
	/**
	 * List of child interfaces to be displayed in the parent interface.
	 */
	private ArrayList<ChildInterface> children = new ArrayList<>();

	/**
	 * Create a new parent interface and binds the mouse actions.
	 * 
	 * @param x
	 * 		X position in the drawing area.
	 * @param y
	 * 		Y position in the drawing area.
	 * @param width
	 * 		Width of the parent interface.
	 * @param height
	 * 		Height of the parent interface.
	 */
	public ParentInterface(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	/**
	 * Draw basic things in the parent interface such as backgrounds.
	 * Any components should be a child!
	 */
	public abstract void draw();

	/**
	 * Display the interface with all its children.
	 * 
	 * @param screen
	 * 		Screen to draw interface to.
	 */
	public void displayInterface(DrawingArea screen) {
		this.draw();

		for (ChildInterface child : children) {
			child.draw();
		}
		screen.drawRenderable(this);
	}

	/**
	 * Add a child to the interface.
	 * 
	 * @param child
	 * 		The child interface we are adding.
	 */
	public void addChild(ChildInterface child) {
		children.add(child);
	}
}
