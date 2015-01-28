package com.joe.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	/**
	 * Create a listener for key type actions.
	 */
	private static InputActionListener<KeyEvent> typedActions = new InputActionListener<>("Keyboard Typed Action");

	/**
	 * Create a listener for key pressed actions.
	 */
	private static InputActionListener<KeyEvent> pressedActions = new InputActionListener<>("Keyboard Pressed Action");
	
	/**
	 * Create a listener for key released actions.
	 */
	private static InputActionListener<KeyEvent> releasedActions = new InputActionListener<>("Keyboard Released Action");

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyAdapter#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		typedActions.process(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		pressedActions.process(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyAdapter#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		releasedActions.process(e);
	}

	/**
	 * @return the input listener for pressed actions.
	 */
	public static InputActionListener<KeyEvent> getPressedActions() {
		return pressedActions;
	}

	/**
	 * @return the input listener for released actions.
	 */
	public static InputActionListener<KeyEvent> getReleasedActions() {
		return releasedActions;
	}

	/**
	 * @return the input listener for typed actions.
	 */
	public static InputActionListener<KeyEvent> getTypedActions() {
		return typedActions;
	}
}
