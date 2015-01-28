package com.joe.engine.io;

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
	 * Set the value for id.
	 * 
	 * @param hash
	 *            Hash used to retrieve from definition.
	 * @param value
	 *            Any information pertaining to that id.
	 */
	public void set(K hash, V value) {
		data.put(hash, value);
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
			System.err.println(super.getClass().getSimpleName() + "(Hash: "
					+ hash + "not found " + (size() - 1) + ")");
			return data.get(0);
		}
		return data.get(hash);
	}

	/**
	 * @return size of definition.
	 */
	public int size() {
		return getData().size();
	}
}
