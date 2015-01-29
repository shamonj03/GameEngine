package com.joe.engine.io.definition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.joe.engine.io.Data;
import com.joe.engine.io.Definition;

public abstract class SingleFileDefinition<K, V extends Data> extends Definition<K, V> {
	/**
	 * Create a new definition that is contained entirely in one file.
	 * 
	 * @param path
	 * 		Path to definition file.
	 */
	public SingleFileDefinition(String path) {
		try {
			readData(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Parsing information from the file is handled here.
	 * 
	 * @param line
	 *            The current line the buffered reader is on.
	 */
	public abstract void parse(String line);
	
	/**
	 * Loads the definition file.
	 * 
	 * @param path
	 *            The path to the definition file.
	 *            
	 * @throws IOException 
	 */
	public void readData(String path) throws IOException {
		File file = new File(path);

		if (!file.exists()) {
			throw new FileNotFoundException(this.getClass().getSimpleName() + " could not find "
					+ path);
		}

		ArrayList<String> lines = readFile(file);
		
		for(String line : lines) {
			parse(line);
		}
	}
}
