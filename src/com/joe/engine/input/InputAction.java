package com.joe.engine.input;

import java.awt.event.ComponentEvent;

public abstract class InputAction<T extends ComponentEvent> {

	/**
	 * Perform the action created by this object.
	 * 
	 * @param event
	 * 		Type of input event.
	 */
	public abstract void actionPerformed(T event);

}
