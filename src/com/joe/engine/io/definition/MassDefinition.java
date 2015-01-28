package com.joe.engine.io.definition;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.joe.engine.io.Data;
import com.joe.engine.io.Definition;

public abstract class MassDefinition<K, V extends Data> extends Definition<K, V> {
	/**
	 * The folder the definition is located.
	 */
	private String definitionFolder;
	
	/**
	 * The extension files in this definition use.
	 */
	private String fileExtension;

	/**
	 * Creates a new definition where the key in the data map is the numeric
	 * value of the file name. This type of definition loads all the data
	 * in it on start up.
	 * 
	 * @param definitionFolder
	 *            The folder the definition is located.
	 * @param fileExtension
	 *            The extension files in this definition use.
	 */
	public MassDefinition(String definitionFolder, String fileExtension) {
		this.definitionFolder = definitionFolder;
		this.fileExtension = fileExtension;
	}

	/**
	 * Create a new instance of the data to store in the cache.
	 * 
	 * @param id
	 *            Id of the data.
	 * 
	 * @return the cached data.
	 */
	public abstract V createData(K id);

	/**
	 * Parse the information for the data.
	 * 
	 * @param data
	 *            The instance to store the data in.
	 * 
	 * @param line
	 *            The current line we are parsing.
	 */
	public abstract void parse(V data, String line);

	/**
	 * Load a new block of data.
	 * 
	 * @param id
	 *            The id of the data in the definition.
	 * 
	 * @throws IOException
	 */
	public void load(K id) throws IOException {
		File file = new File(definitionFolder + "/" + id + fileExtension);

		if (!file.exists()) {
			throw new FileNotFoundException("No "
					+ this.getClass().getSimpleName() + " found at " + id);
		}

		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line = "";

		V data = createData(id);

		while ((line = reader.readLine()) != null) {
			if (line.startsWith("//")) {
				continue;
			}
			parse(data, line);
		}
		set(id, data);
		reader.close();
	}

}
