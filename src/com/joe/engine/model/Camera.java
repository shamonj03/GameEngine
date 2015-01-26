package com.joe.engine.model;

import com.joe.engine.graphics.renderable.Screen;

public class Camera {
	
	/**
	 * Location of the camera.
	 */
	private Location location;
	
	/**
	 * Creates a new camera.
	 *
	 * @param location
	 * 		The location to focus on. The camera
	 * 		will draw everything around location.
	 * 		Location is on the center of the screen.
	 */
	public Camera(Location location) {
		this.location = new Location(location);
	}

	/**
	 * Creates a new camera.
	 * 
	 * @param x
	 * 		X position to focus on.
	 * @param y
	 * 		Y position to focus on.
	 */
	public Camera(int x, int y) {
		this.location = new Location(x, y);
	}

	/**
	 * Gets the X Position on the screen relative to the camera.
	 * 
	 * @param screen
	 * 		The screen to draw to.
	 * @param target
	 * 		Target location.
	 * @param width
	 * 		Width of the target.
	 * @return The position on the screen.
	 */
	public int getPositionXOnScreen(Screen screen, Location target, int width) {
		return (int) (target.getX() - (location.getX() - (screen.getWidth() - width) / 2));
	}
	
	/**
	 * Gets the Y Position on the screen relative to the camera.
	 * 
	 * @param screen
	 * 		The screen to draw to.
	 * @param target
	 * 		Target location.
	 * @param width
	 * 		Width of the target.
	 * @return The position on the screen.
	 */
	public int getPositionYOnScreen(Screen screen, Location other, int height) {
		return (int) (other.getY() - (location.getY() - (screen.getHeight() - height) / 2));
	}
	
	/**
	 * @return The location of the camera in the world.
	 */
	public Location getLocation() {
		return location;
	}
}
