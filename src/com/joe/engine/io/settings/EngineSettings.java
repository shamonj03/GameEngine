package com.joe.engine.io.settings;

import java.util.Properties;

import com.joe.engine.io.Settings;

public class EngineSettings extends Settings {

	/**
	 * The target FPS for the game to run at.
	 */
	private int targetFPS;

	/**
	 * The amount of strategy layers for page flipping.
	 */
	private int bufferStrategyLayers;

	/**
	 * Show the FPS and updates true/false.
	 */
	private boolean showFPS;

	/**
	 * Enable/disable debugging mode.
	 */
	private boolean debugging;

	@Override
	public void setProperties(Properties properties) {
		targetFPS = Integer.parseInt(properties.getProperty("targetFPS"));
		bufferStrategyLayers = Integer.parseInt(properties.getProperty("bufferStrategyLayers"));
		showFPS = Boolean.parseBoolean(properties.getProperty("showFPS"));
		debugging = Boolean.parseBoolean(properties.getProperty("debugging"));
	}

	@Override
	public void setDefaultProperties(Properties properties) {
		properties.setProperty("targetFPS", "60");
		properties.setProperty("bufferStrategyLayers", "2");
		properties.setProperty("showFPS", "false");
		properties.setProperty("debugging", "false");
	}

	/**
	 * @return the target fps of the game.
	 */
	public int getTargetFPS() {
		return targetFPS;
	}

	/**
	 * @return the amount of strategy layers.
	 */
	public int getBufferStrategyLayers() {
		return bufferStrategyLayers;
	}

	/**
	 * @return is debugging mode on.
	 */
	public boolean isDebugging() {
		return debugging;
	}

	/**
	 * @return is the FPS being displayed.
	 */
	public boolean isShowingFPS() {
		return showFPS;
	}
}
