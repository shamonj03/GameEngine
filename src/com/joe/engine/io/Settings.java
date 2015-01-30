package com.joe.engine.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class Settings {
	/**
	 * The path to the properties file.
	 */
	private final String SETTINGS_PATH = "./data/settings/";
	
	/**
	 * The path to the properties file.
	 */
	private final String SETTINGS_FILE = SETTINGS_PATH + this.getClass().getSimpleName() + ".txt";
	
	/**
	 * Set the values of the settings here.
	 * 
	 * @param properties
	 */
	public abstract void setProperties(Properties properties);

	/**
	 * Set what the default properties should be her.
	 * 
	 * @param properties
	 */
	public abstract void setDefaultProperties(Properties properties);

	/**
	 * Load the settings file.
	 */
	public void load() {
		try {
			File file = new File(SETTINGS_PATH);
			
			if (!file.exists()) {
				System.err.println(this.getClass().getSimpleName()
						+ ": No settings found @ " + file.getPath());
				System.err.println(this.getClass().getSimpleName()
						+ ": creating default settings file");
			
				file.mkdirs();
				
				createDefaultSettingsFile();
			}
			
			loadSettingsFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load the settings file and set the properties.
	 * 
	 * @throws IOException
	 */
	private void loadSettingsFile() throws IOException {
		Properties properties = new Properties();

		FileInputStream inputStream = new FileInputStream(SETTINGS_FILE);

		properties.load(inputStream);

		setProperties(properties);
		
		inputStream.close();
	}

	/**
	 * Create a default settings file if none exists.
	 * 
	 * @throws IOException
	 */
	private void createDefaultSettingsFile() throws IOException {
		Properties properties = new Properties();

		FileOutputStream output = new FileOutputStream(SETTINGS_FILE);

		setDefaultProperties(properties);

		properties.store(output, null);
		
		output.close();
	}
}
