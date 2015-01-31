package com.joe.engine.graphics.renderable.inter.base;

import com.joe.engine.graphics.renderable.inter.BaseInterface;

public abstract class ChildInterface extends BaseInterface {
	/**
	 * The parent of this child.
	 */
	private BaseInterface parent;

	/**
	 * Create a new child interface.
	 * 
	 * @param x
	 *            X Position in the parent.
	 * @param y
	 *            Y position in the parent.
	 * @param width
	 *            Width of child interface.
	 * @param height
	 *            Height of child interface.
	 */
	public ChildInterface(BaseInterface parent, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.parent = parent;
	}

	/**
	 * @return the parent of this child.
	 */
	public BaseInterface getParent() {
		return parent;
	}
}
