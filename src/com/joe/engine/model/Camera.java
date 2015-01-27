package com.joe.engine.model;

import com.joe.engine.graphics.renderable.Screen;

public class Camera {

	/**
	 * Location of the camera.
	 */
	private Location location;

	/**
	 * The amount to offset the camera X by to get it center.
	 */
	private int offsetX;

	/**
	 * The amount to offset the camera Y by to get it center.
	 */
	private int offsetY;
	

	/**
	 * Creates a new camera.
	 * 
	 * @param location
	 * 		Location for camera to focus on.
	 * @param offsetX
	 * 		The amount the camera X is offset by.
	 * @param offsetY
	 * 		The amount the camera Y is offset by.
	 */
	public Camera(Location location, int offsetX, int offsetY) {
		this.setLocation(location);
		this.setOffsetX(offsetX);
		this.setOffsetY(offsetY);
	}

	/**
	 * Set the offset of the camera X.
	 * 
	 * @param offsetX
	 *            The amount to offset by.
	 */
	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	/**
	 * Set the offset of the camera Y.
	 * 
	 * @param offsetY
	 *            The amount to offset by.
	 */
	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}
	
	/**
	 * Set the location of the camera.
	 * 
	 * @param other
	 * 	The location to set the current location to.
	 */
	public void setLocation(Location other) {
		if(this.location == null) {
			this.location = new Location(other);
		} else {
			this.location.setLocation(other);
		}
	}

	/**
	 * Gets the X Position on the screen relative to the camera.
	 * 
	 * @param screen
	 *            The screen to draw to.
	 * @param target
	 *            Target location.
	 * @param xOffset
	 *            Width of the target.
	 * @return The position on the screen.
	 */
	public int getPositionXOnScreen(Screen screen, Location target) {
		return (int) (target.getX() - (location.getX() - (screen.getWidth() - getOffsetX()) / 2));
	}

	/**
	 * Gets the Y Position on the screen relative to the camera.
	 * 
	 * @param screen
	 *            The screen to draw to.
	 * @param target
	 *            Target location.
	 * @param width
	 *            Width of the target.
	 * @return The position on the screen.
	 */
	public int getPositionYOnScreen(Screen screen, Location other) {
		return (int) (other.getY() - (location.getY() - (screen.getHeight() - getOffsetY()) / 2));
	}

	/**
	 * Gets the X Position on the screen relative to the camera.
	 * 
	 * @param screen
	 *            The screen to draw to.
	 * @param target
	 *            Target location.
	 * @param xOffset
	 *            Width of the target.
	 * @return The position on the screen.
	 */
	public int getPositionXOnScreen(Screen screen, int target) {
		return (int) (target - (location.getX() - (screen.getWidth() - getOffsetX()) / 2));
	}

	/**
	 * Gets the Y Position on the screen relative to the camera.
	 * 
	 * @param screen
	 *            The screen to draw to.
	 * @param target
	 *            Target location.
	 * @param width
	 *            Width of the target.
	 * @return The position on the screen.
	 */
	public int getPositionYOnScreen(Screen screen, int target) {
		return (int) (target - (location.getY() - (screen.getHeight() - getOffsetY()) / 2));
	}

	/**
	 * @return The location of the camera in the world.
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * 
	 * @return the amount camera X is offset by.
	 */
	public int getOffsetX() {
		return offsetX;
	}

	/**
	 * 
	 * @return the amount camera Y is offset by.
	 */
	public int getOffsetY() {
		return offsetY;
	}
}
