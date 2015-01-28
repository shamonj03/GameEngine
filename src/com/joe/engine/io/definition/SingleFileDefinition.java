package com.joe.engine.io.definition;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
			load(path);
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
	public void load(String path) throws IOException {
		File file = new File(path);

		if (!file.exists()) {
			throw new FileNotFoundException("No " + this.getClass().getSimpleName() + " found at "
					+ path);
		}

		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line = "";

		while ((line = reader.readLine()) != null) {
			if (line.startsWith("//")) {
				continue;
			}

			parse(line);
		}
		reader.close();
	}
}
