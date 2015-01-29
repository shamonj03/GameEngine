package com.joe.engine.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Definition<K, V extends Data> {
	/**
	 * Generic list of items contained in the definition
	 * 
	 * @Key Definition hash
	 * 
	 * @Value Definition data
	 */
	private HashMap<K, V> data = new HashMap<K, V>();
	
	/**
	 * Read the contents of the file line by line and
	 * stores each line in an array list.
	 * 
	 * @param file
	 * 		The file to read.
	 * 
	 * @return The lines contained in the file.
	 * 
	 * @throws IOException
	 */
	public ArrayList<String> readFile(File file) throws IOException {
		ArrayList<String> lines = new ArrayList<>();
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		String line = "";
		
		while((line = reader.readLine()) != null) {
			if(line.startsWith("//")) {
				continue;
			}
			lines.add(line);
		}
		reader.close();
		return lines;
	}

	/**
	 * Gets a element from the definition for its hash
	 * 
	 * @param hash
	 *            Hash of definition item.
	 * 
	 * @return data for the hash.
	 */
	public V retrive(K hash) {
		if (!data.containsKey(hash)) {
			System.err.println(super.getClass().getSimpleName() + ": could not find item " + hash + " in definition.");
			return data.get(0);
		}
		return data.get(hash);
	}

	/**
	 * Set the value for id.
	 * 
	 * @param hash
	 *            Hash used to retrieve from definition.
	 * @param value
	 *            Any information pertaining to that id.
	 */
	public void create(K hash, V value) {
		data.put(hash, value);
	}
	
	/**
	 * Gets the generic list of definitions stored in list.
	 * 
	 * @Key - Definition hash
	 * 
	 * @Value - Definition instance
	 * 
	 * @return list List of elements in definition.
	 */
	public HashMap<K, V> getData() {
		return data;
	}

	/**
	 * @return size of definition.
	 */
	public int size() {
		return getData().size();
	}
}
