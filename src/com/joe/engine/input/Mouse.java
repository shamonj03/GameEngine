package com.joe.engine.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Mouse extends MouseAdapter {

	private static ArrayList<InputAction<MouseEvent>> clickedActions = new ArrayList<>();
	private static ArrayList<InputAction<MouseEvent>> pressedActions = new ArrayList<>();
	private static ArrayList<InputAction<MouseEvent>> draggedActions = new ArrayList<>();
	private static ArrayList<InputAction<MouseEvent>> movedActions = new ArrayList<>();
	private static ArrayList<InputAction<MouseEvent>> releasedActions = new ArrayList<>();
	private static boolean holding;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		processAction(e, getClickedActions());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		processAction(e, getPressedActions());
		holding = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		processAction(e, getDraggedActions());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		processAction(e, getMovedActions());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		processAction(e, getReleasedActions());

		holding = false;
	}

	/**
	 * Processes the mouse event.
	 * 
	 * @param e
	 *            MouseEvent used by Mouse Adapter.
	 * 
	 * @param actions
	 *            Actions to be used for the MouseEvent.
	 */
	private void processAction(MouseEvent e,
			ArrayList<InputAction<MouseEvent>> actions) {

		ArrayList<InputAction<MouseEvent>> temp = new ArrayList<>(actions);

		synchronized(temp) {
			for (InputAction<MouseEvent> m : temp) {
				m.actionPerformed(e);
			}
		}
	}

	/**
	 * Registers a mouse clicked action.
	 * 
	 * @param action
	 *            Action used when mouse clicked.
	 */
	public static synchronized void addClickedAction(
			InputAction<MouseEvent> action) {
		synchronized (getClickedActions()) {
			getClickedActions().add(action);
		}
	}

	/**
	 * Registers a mouse pressed action.
	 * 
	 * @param action
	 *            Action used when mouse pressed.
	 */
	public static synchronized void addPressedAction(
			InputAction<MouseEvent> action) {
		synchronized (getPressedActions()) {
			getPressedActions().add(action);
		}
	}

	/**
	 * Registers a mouse dragged action.
	 * 
	 * @param action
	 *            Action used when mouse dragged.
	 */
	public static synchronized void addDraggedAction(
			InputAction<MouseEvent> action) {
		synchronized (getDraggedActions()) {
			getDraggedActions().add(action);
		}
	}

	/**
	 * Registers a mouse moved action.
	 * 
	 * @param action
	 *            Action used when mouse moved.
	 */
	public static synchronized void addMovedAction(
			InputAction<MouseEvent> action) {
		synchronized (getMovedActions()) {
			getMovedActions().add(action);
		}
	}

	/**
	 * Registers a mouse released action.
	 * 
	 * @param action
	 *            Action used when mouse released.
	 */
	public static synchronized void addReleasedAction(
			InputAction<MouseEvent> action) {
		synchronized (getReleasedActions()) {
			getReleasedActions().add(action);
		}
	}

	public static boolean isHolding() {
		return holding;
	}

	public static ArrayList<InputAction<MouseEvent>> getClickedActions() {
		return clickedActions;
	}

	public static ArrayList<InputAction<MouseEvent>> getDraggedActions() {
		return draggedActions;
	}

	public static ArrayList<InputAction<MouseEvent>> getMovedActions() {
		return movedActions;
	}

	public static ArrayList<InputAction<MouseEvent>> getPressedActions() {
		return pressedActions;
	}

	public static ArrayList<InputAction<MouseEvent>> getReleasedActions() {
		return releasedActions;
	}

}
