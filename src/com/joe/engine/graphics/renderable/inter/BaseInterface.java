package com.joe.engine.graphics.renderable.inter;

import java.util.ArrayList;

import com.joe.engine.graphics.renderable.DrawingArea;
import com.joe.engine.graphics.renderable.inter.base.ChildInterface;

public abstract class BaseInterface extends DrawingArea {
	/**
	 * Enable vision of the interface.
	 */
	private boolean visible = true;

	/**
	 * List of child interfaces to be displayed in the parent interface.
	 */
	private ArrayList<ChildInterface> children = new ArrayList<>();

	/**
	 * Create a new parent interface and binds the mouse actions.
	 * 
	 * @param x
	 *            X position in the drawing area.
	 * @param y
	 *            Y position in the drawing area.
	 * @param width
	 *            Width of the parent interface.
	 * @param height
	 *            Height of the parent interface.
	 */
	public BaseInterface(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	/**
	 * Draw basic things in the parent interface such as backgrounds. Any
	 * components should be a child!
	 */
	public abstract void draw();

	/**
	 * Add a child to the interface.
	 * 
	 * @param child
	 *            The child interface we are adding.
	 */
	public void addChild(ChildInterface child) {
		children.add(child);
	}
	
	/**
	 * Display the interface with all its children.
	 * 
	 * @param parent
	 *            Screen to draw interface to.
	 */
	public void displayInterface(DrawingArea parent) {
		if (isVisible()) {
			this.draw();

			for (ChildInterface child : getChildren()) {
				child.displayInterface(child.getParent());
			}
			parent.drawRenderable(this);
		}
	}

	/**
	 * Set the visibility of the interface.
	 * 
	 * @param visible
	 *            True visible, false off.
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Check if one location is within another.
	 * 
	 * @param x
	 *            Input X.
	 * @param y
	 *            Input Y.
	 * @return true if inside location.
	 */
	public boolean inBounds(int x, int y) {
		return (x >= getX() && x <= getX() + getWidth())
				&& (y >= getY() && y <= getY() + getHeight());
	}

	/**
	 * @return visibility of interface.
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * @return the children this interface contains.
	 */
	public ArrayList<ChildInterface> getChildren() {
		return children;
	}
}
