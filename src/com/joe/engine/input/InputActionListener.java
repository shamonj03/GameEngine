package com.joe.engine.input;

import java.awt.event.InputEvent;
import java.util.HashMap;

public class InputActionListener<T extends InputEvent> {

	/**
	 * The actions registered to this listener.
	 */
	private HashMap<String, InputAction<T>> actions = new HashMap<>();

	/**
	 * The name of the input action listener.
	 */
	private final String name;

	/**
	 * Creates a new input action listener.
	 * 
	 * @param name
	 *            The name of the input action listener.
	 */
	public InputActionListener(String name) {
		this.name = name;
	}

	/**
	 * Processes the key event.
	 * 
	 * @param e
	 *            KeyEvent used by Key Adapter.
	 * 
	 * @param actions
	 *            Actions to be used for the KeyEvent.
	 */
	public void process(T e) {
		HashMap<String, InputAction<T>> temp = new HashMap<>(actions);

		for (InputAction<T> k : temp.values()) {
			k.actionPerformed(e);
		}
	}

	/**
	 * Registers a input action listener.
	 * 
	 * @param hash
	 *            Hash to use as the action key.
	 * 
	 * @param action
	 *            Action used when action fired.
	 */
	public void register(String hash, InputAction<T> action) {
		if (actions.containsKey(hash)) {
			System.out.println(name + " " + hash
					+ " has already been registered.");
		} else {
			actions.put(hash, action);
			System.out.println(name + " " + hash
					+ " has sucessfully been registered.");
		}
	}

	/**
	 * Unregisters a input action listener.
	 * 
	 * @param hash
	 *            The action to unregister.
	 */
	public void unregister(String hash) {
		if (actions.containsKey(hash)) {
			actions.remove(hash);
			System.out.println(name + " " + hash
					+ " has sucessfully unregistered.");
		} else {
			System.out.println("Failed to unregister no " + name + " " + hash
					+ " found.");
		}
	}
	
	/**
	 * Unregister all actions in this listener.
	 */
	public void unregisterAll() {
		actions.clear();
	}
}
