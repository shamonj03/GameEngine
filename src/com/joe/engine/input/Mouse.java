package com.joe.engine.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {
	/**
	 * Create a listener for mouse clicked actions.
	 */
	private static InputActionListener<MouseEvent> clickedActions = new InputActionListener<>(
			"Mouse Clicked Action");
	
	/**
	 * Create a listener for mouse pressed actions.
	 */
	private static InputActionListener<MouseEvent> pressedActions = new InputActionListener<>(
			"Mouse Pressed Action");
	
	/**
	 * Create a listener for mouse dragged actions.
	 */
	private static InputActionListener<MouseEvent> draggedActions = new InputActionListener<>(
			"Mouse Dragged Action");
	
	/**
	 * Create a listener for mouse moved actions.
	 */
	private static InputActionListener<MouseEvent> movedActions = new InputActionListener<>(
			"Mouse Moved Action");
	
	/**
	 * Create a listener for mouse release actions.
	 */
	private static InputActionListener<MouseEvent> releasedActions = new InputActionListener<>(
			"Mouse Released Action");

	/**
	 * Create a listener for mouse release actions.
	 */
	private static InputActionListener<MouseEvent> mouseEnteredActions = new InputActionListener<>(
			"Mouse Entered Action");
	
	/**
	 * Create a listener for mouse release actions.
	 */
	private static InputActionListener<MouseEvent> mouseExitedActions = new InputActionListener<>(
			"Mouse Exited Action");
	
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
	
	@Override
	public void mouseEntered(MouseEvent e) {
		mouseEnteredActions.process(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		mouseExitedActions.process(e);
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

	/**
	 * @return the input listener for entered actions.
	 */
	public static InputActionListener<MouseEvent> getMouseEnteredActions() {
		return mouseEnteredActions;
	}

	/**
	 * @return the input listener for exited actions.
	 */
	public static InputActionListener<MouseEvent> getMouseExitedActions() {
		return mouseExitedActions;
	}

}
