package com.joe.engine.io;

import java.io.IOException;
import java.util.HashMap;

public abstract class Definition<T extends Data> {

	/**
	 * Generic list of items contained in the definition
	 * 
	 * @Key Definition id
	 * 
	 * @Value Definition data
	 */
	private HashMap<Integer, T> data = new HashMap<Integer, T>();

	/**
	 * Parsing information from the file is handled here.
	 * 
	 * @param line
	 *            The current line the buffered reader is on.
	 */
	public abstract void parse(String line);

	/**
	 * Loads the definition folder
	 * 
	 * @param path
	 *            The path to the definition folder.
	 */
	public abstract void load(String path) throws IOException;

	/**
	 * Gets the generic list of definitions stored in list.
	 * 
	 * @Key - Definition item id
	 * 
	 * @Value - Definition item instance
	 * 
	 * @return list List of items in definition.
	 */
	public HashMap<Integer, T> getData() {
		return data;
	}

	/**
	 * Gets a instance from the definition for id
	 * 
	 * @param id
	 *            Id of definition item.
	 * 
	 * @return data for he id.
	 */
	public T forId(int id) {
		if (id >= size()) {
				System.err.println(super.getClass().getSimpleName() + "(ID: "
						+ id + " out of definition bounds, max ID "
						+ (size() - 1) + ")");
			return data.get(0);
		}
		return data.get(id);
	}

	/**
	 * @return size of definition.
	 */
	public int size() {
		return getData().size();
	}
}
