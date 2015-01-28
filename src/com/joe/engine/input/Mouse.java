package com.joe.engine.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {
	/**
	 * Create a listener for mouse clicked actions.
	 */
	private static InputActionListener<MouseEvent> clickedActions = new InputActionListener<>(
			"Clicked Action");
	
	/**
	 * Create a listener for mouse pressed actions.
	 */
	private static InputActionListener<MouseEvent> pressedActions = new InputActionListener<>(
			"Pressed Action");
	
	/**
	 * Create a listener for mouse dragged actions.
	 */
	private static InputActionListener<MouseEvent> draggedActions = new InputActionListener<>(
			"Dragged Action");
	
	/**
	 * Create a listener for mouse moved actions.
	 */
	private static InputActionListener<MouseEvent> movedActions = new InputActionListener<>(
			"Moved Action");
	
	/**
	 * Create a listener for mouse release actions.
	 */
	private static InputActionListener<MouseEvent> releasedActions = new InputActionListener<>(
			"Released Action");

	/**
	 * Boolean to check if mouse buttons are being held or not.
	 */
	private static boolean holding;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		clickedActions.process(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		pressedActions.process(e);
		holding = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		draggedActions.process(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		movedActions.process(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		releasedActions.process(e);
		holding = false;
	}

	/**
	 * @return true/false mouse button is being held.
	 */
	public static boolean isHolding() {
		return holding;
	}

	/**
	 * @return the input listener for clicked actions.
	 */
	public static InputActionListener<MouseEvent> getClickedActions() {
		return clickedActions;
	}

	/**
	 * @return the input listener for dragged actions.
	 */
	public static InputActionListener<MouseEvent> getDraggedActions() {
		return draggedActions;
	}

	/**
	 * @return the input listener for moved actions.
	 */
	public static InputActionListener<MouseEvent> getMovedActions() {
		return movedActions;
	}

	/**
	 * @return the input listener for pressed actions.
	 */
	public static InputActionListener<MouseEvent> getPressedActions() {
		return pressedActions;
	}

	/**
	 * @return the input listener for released actions.
	 */
	public static InputActionListener<MouseEvent> getReleasedActions() {
		return releasedActions;
	}

}
