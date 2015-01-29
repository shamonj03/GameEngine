package com.joe.engine.io.definition;

import java.io.File;
import java.io.IOException;

import com.joe.engine.io.Data;
import com.joe.engine.io.Definition;

public abstract class MassDefinition<K, V extends Data> extends Definition<K, V> {

	/**
	 * The folder the definition is located.
	 */
	protected String definitionFolder;
	
	/**
	 * The extension files in this definition use.
	 */
	protected String fileExtension;

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
		
		try {
			loadAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public abstract void readData(String name) throws IOException;
	
	
	private void loadAll() throws IOException {
		File[] files = new File(definitionFolder).listFiles();
		
		for(File file : files) {
			
			if(file.getName().endsWith(fileExtension)) {
				readData(file.getName());
			}
		}
	}
}
