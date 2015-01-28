package com.joe.engine.input;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Window implements WindowListener {
	/**
	 * Create a listener for window closing actions.
	 */
	private static InputActionListener<WindowEvent> closingActions = new InputActionListener<>("Window Closing Action");

	/**
	 * Create a listener for window activated actions.
	 */
	private static InputActionListener<WindowEvent> activatedActions = new InputActionListener<>("Window Activated Action");

	/**
	 * Create a listener for window closed actions.
	 */
	private static InputActionListener<WindowEvent> closedActions = new InputActionListener<>("Window Closed Action");

	/**
	 * Create a listener for window deactivated actions.
	 */
	private static InputActionListener<WindowEvent> deactivatedActions = new InputActionListener<>("Window Deactivated Action");

	/**
	 * Create a listener for window deiconified actions.
	 */
	private static InputActionListener<WindowEvent> deiconifiedActions = new InputActionListener<>("Window Deiconified Action");

	/**
	 * Create a listener for window iconified actions.
	 */
	private static InputActionListener<WindowEvent> iconifiedActions = new InputActionListener<>("Window iconified Action");
	
	/**
	 * Create a listener for window opened actions.
	 */
	private static InputActionListener<WindowEvent> openedActions = new InputActionListener<>("Window Opened Action");

	/*
	 * Create a default close operation.
	 * 
	 * (non-Javadoc)
	 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		closingActions.process(e);
		System.exit(1);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		activatedActions.process(e);
	}


	@Override
	public void windowClosed(WindowEvent e) {
		closedActions.process(e);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		deactivatedActions.process(e);
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		deiconifiedActions.process(e);
	}

	@Override
	public void windowIconified(WindowEvent e) {
		iconifiedActions.process(e);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		openedActions.process(e);
	}

	/**
	 * @return the input listener for closing actions.
	 */
	public static InputActionListener<WindowEvent> getClosingActions() {
		return closingActions;
	}

	/**
	 * @return the input listener for activated actions.
	 */
	public static InputActionListener<WindowEvent> getActivatedActions() {
		return activatedActions;
	}

	/**
	 * @return the input listener for closed actions.
	 */
	public static InputActionListener<WindowEvent> getClosedActions() {
		return closedActions;
	}

	/**
	 * @return the input listener for deactivated actions.
	 */
	public static InputActionListener<WindowEvent> getDeactivatedActions() {
		return deactivatedActions;
	}

	/**
	 * @return the input listener for deiconified actions.
	 */
	public static InputActionListener<WindowEvent> getDeiconifiedActions() {
		return deiconifiedActions;
	}

	/**
	 * @return the input listener for iconified actions.
	 */
	public static InputActionListener<WindowEvent> getIconifiedActions() {
		return iconifiedActions;
	}

	/**
	 * @return the input listener for opened actions.
	 */
	public static InputActionListener<WindowEvent> getOpenedActions() {
		return openedActions;
	}
}
