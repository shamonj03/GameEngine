package com.joe.engine.io.definition;

import java.io.IOException;

import com.joe.engine.io.Data;
import com.joe.engine.io.Definition;

public abstract class OnDemandDefinition<K, V extends Data> extends Definition<K, V> {
	/**
	 * The folder the definition is located.
	 */
	protected String definitionFolder;

	/**
	 * Creates a new definition where the key in the data map is the numeric
	 * value of the file name. This type of definition loads data only when
	 * it is called and it is not cached.
	 * 
	 * @param definitionFolder
	 *            The folder the definition is located.
	 * @param fileExtension
	 *            The extension files in this definition use.
	 */
	public OnDemandDefinition(String definitionFolder) {
		this.definitionFolder = definitionFolder;
	}


	@Override
	public V retrive(K hash) {
		if (!getData().containsKey(hash)) {
			System.out.println(super.getClass().getSimpleName() + ": " + hash + " not loaded attempting to retrive.");
			try {
				put(hash, readData(hash));
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(super.getClass().getSimpleName() + ": Successfully cached " + hash + ".");
		} else {
			System.out.println(super.getClass().getSimpleName() + ": Successfully retrived " + hash + ".");
		}
		return super.retrive(hash);
	}
}
