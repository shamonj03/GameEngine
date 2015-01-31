package com.joe.engine.graphics.renderable.inter.base;

import java.awt.event.MouseEvent;

import com.joe.engine.graphics.renderable.inter.BaseInterface;
import com.joe.engine.input.InputAction;
import com.joe.engine.input.Mouse;

public abstract class ParentInterface extends BaseInterface {
	/**
	 * Is this interface being dragged. 
	 */
	private boolean dragging = false;

	/**
	 * Can this interface be dragged.
	 */
	private boolean draggable = false;

	/**
	 * Create a new parent interface and binds the mouse actions.
	 * 
	 * @param draggable
	 *            Set the interface to be draggable.
	 * @param x
	 *            X position in the drawing area.
	 * @param y
	 *            Y position in the drawing area.
	 * @param width
	 *            Width of the parent interface.
	 * @param height
	 *            Height of the parent interface.
	 */
	public ParentInterface(boolean draggable, int x, int y, int width,
			int height) {
		super(x, y, width, height);
		this.draggable = draggable;
		registerMouseActions();
	}

	/**
	 * Create a new parent interface and binds the mouse actions with the
	 * draggable set to false by default.
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
	public ParentInterface(int x, int y, int width, int height) {
		this(false, x, y, width, height);
	}

	/**
	 * Register mouse actions associated with this child.
	 */
	public void registerMouseActions() {
		Mouse.getPressedActions().register(
				this.getClass().getSimpleName() + "_PressedAction",
				new InputAction<MouseEvent>() {
					@Override
					public void actionPerformed(MouseEvent event) {
						int x = event.getX();
						int y = event.getY();

						if (inBounds(x, y) && isVisible() && isDraggable()) {
							dragging = true;
							setLocation(x, y);
						}
					}
				});

		Mouse.getDraggedActions().register(
				this.getClass().getSimpleName() + "_DraggedAction",
				new InputAction<MouseEvent>() {
					@Override
					public void actionPerformed(MouseEvent event) {
						int x = event.getX();
						int y = event.getY();

						if (dragging && isVisible() && isDraggable()) {
							setLocation(x, y);
						}
					}
				});

		Mouse.getReleasedActions().register(
				this.getClass().getSimpleName() + "_ReleasedAction",
				new InputAction<MouseEvent>() {
					@Override
					public void actionPerformed(MouseEvent event) {
						dragging = false;
					}
				});

	}

	/**
	 * @return dragability of the interface.
	 */
	public boolean isDraggable() {
		return draggable;
	}

}
