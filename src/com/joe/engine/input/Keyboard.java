package com.joe.engine.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Keyboard extends KeyAdapter {
	
	private static ArrayList<InputAction<KeyEvent>> releasedActions = new ArrayList<>();
	private static ArrayList<InputAction<KeyEvent>> pressedActions = new ArrayList<>();
	private static ArrayList<InputAction<KeyEvent>> typedActions = new ArrayList<>();

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyAdapter#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		processAction(e, typedActions);
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		processAction(e, pressedActions);
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyAdapter#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		processAction(e, releasedActions);
	}
	
	/**
	 * Processes the key event.
	 * 
	 * @param e
	 * 		KeyEvent used by Key Adapter.
	 * 
	 * @param actions
	 * 		Actions to be used for the
	 * 		KeyEvent.
	 */
	private void processAction(KeyEvent e, ArrayList<InputAction<KeyEvent>> actions) {

		ArrayList<InputAction<KeyEvent>> temp = new ArrayList<>(actions);
		
		for(InputAction<KeyEvent> k : temp) {
			k.actionPerformed(e);
		}
	}
	
	/**
	 * Registers a key released action.
	 * 
	 * @param action
	 * 		Action used when key released.
	 */
	public static void addReleasedAction(InputAction<KeyEvent> action) {
		releasedActions.add(action);
	}

	/**
	 * Registers a key pressed action.
	 * 
	 * @param action
	 * 		Action used when key pressed.
	 */
	public static void addPressedAction(InputAction<KeyEvent> action) {
		pressedActions.add(action);
	}

	/**
	 * Registers a key typed action.
	 * 
	 * @param action
	 * 		Action used when key typed.
	 */
	public static void addTypedAction(InputAction<KeyEvent> action) {
		typedActions.add(action);
	}
}
