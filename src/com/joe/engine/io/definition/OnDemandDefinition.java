package com.joe.engine.io.definition;

import java.io.IOException;

import com.joe.engine.io.Data;

public abstract class OnDemandDefinition<K, V extends Data> extends MassDefinition<K, V> {
	
	/**
	 * Creates a new definition where the key in the data map is the numeric
	 * value of the file name. This type of definition only loads data
	 * when it is called and then caches it for later.
	 * 
	 * @param definitionFolder
	 *            The folder the definition is located.
	 * @param fileExtension
	 *            The extension files in this definition use.
	 */
	public OnDemandDefinition(String definitionFolder,
			String fileExtension) {
		super(definitionFolder, fileExtension);
	}

	@Override
	public V retrive(K id) {
		if (!getData().containsKey(id)) {
			System.out.println(super.getClass().getSimpleName() + ": " + id + " not loaded attempting to retrive.");
			try {
				load(id);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(super.getClass().getSimpleName() + ": Successfully cached " + id + ".");
		} else {
			System.out.println(super.getClass().getSimpleName() + ": Successfully retrived " + id + ".");
		}
		return super.retrive(id);
	}
}
