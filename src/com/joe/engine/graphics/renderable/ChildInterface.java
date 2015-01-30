package com.joe.engine.graphics.renderable;

import java.awt.event.MouseEvent;

import com.joe.engine.input.InputAction;
import com.joe.engine.input.Mouse;

public abstract class ChildInterface extends ParentInterface {
	/**
	 * The parent interface to draw to.
	 */
	private ParentInterface parent;

	/**
	 * Create a new child interface.
	 * 
	 * @param parent
	 *            The parent interface to draw to.
	 * @param x
	 *            X Position in the parent.
	 * @param y
	 *            Y position in the parent.
	 * @param width
	 *            Width of child interface.
	 * @param height
	 *            Height of child interface.
	 */
	public ChildInterface(ParentInterface parent, int x, int y, int width,
			int height) {
		super(x, y, width, height);
		this.parent = parent;
		this.registerMouseActions();
	}

	/**
	 * Perform an on click action when the mouse
	 * is pressed.
	 */
	public abstract void onClick();

	/**
	 * Perform an on hover action when the mouse
	 * is moved.
	 */
	public abstract void onHover();

	/**
	 * Register mouse actions associated with this child.
	 */
	private void registerMouseActions() {
		Mouse.getPressedActions().register(
				this.getClass().getSimpleName() + "_PressedAction",
				new InputAction<MouseEvent>() {
					@Override
					public void actionPerformed(MouseEvent event) {
						onClick();
					}
				});

		Mouse.getMovedActions().register(
				this.getClass().getSimpleName() + "_MovedAction",
				new InputAction<MouseEvent>() {
					@Override
					public void actionPerformed(MouseEvent event) {
						onHover();
					}
				});
	}

	/**
	 * @return the parent interface of the child.
	 */
	public ParentInterface getParent() {
		return parent;
	}
}
