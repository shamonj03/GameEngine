package com.joe.engine.input;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends WindowAdapter {

	/*
	 * Create a default close operation.
	 * 
	 * (non-Javadoc)
	 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		//TODO: Add saving before closing.
		
		System.exit(1);
	}

}
