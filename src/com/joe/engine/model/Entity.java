package com.joe.engine.model;

import com.joe.engine.graphics.renderable.DrawingArea;
import com.joe.engine.util.Util;

public abstract class Entity {

	private Location location;

	private String name;

	private int width;
	private int height;

	/**
	 * Draws the entity.
	 * 
	 * @param screen
	 *            Screen to draw to.
	 */
	public abstract void draw(DrawingArea screen);

	/**
	 * Sets the entities location.
	 * 
	 * @param location
	 *            Location in area.
	 */
	public void setLocation(Location location) {
		this.location = new Location(location);
	}

	/**
	 * Sets the entities location.
	 * 
	 * @param location
	 *            Location in area.
	 */
	public void setLocation(float x, float y) {
		if (this.location == null) {
			this.location = new Location(x, y);
		} else {
			this.location.setLocation(x, y);
		}
	}

	/**
	 * Sets the entities name.
	 * 
	 * @param name
	 *            Name of entity.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets how big the entity is.
	 * 
	 * @param width
	 *            Width of entity.
	 * 
	 * @param height
	 *            Height of entity.
	 */
	public void setDimensions(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	/**
	 * Sets the entities width.
	 * 
	 * @param width
	 *            Width of entity.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Sets the entities width.
	 * 
	 * @param height
	 *            Height of entity.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Checks if current entity intersects another entity.
	 * 
	 * @param target
	 *            Entity to check against.
	 * 
	 * @return true/false.
	 */
	public boolean intersects(Entity target) {
		return Util.inBounds((int) getLocation().getX(), (int) getLocation()
				.getY(), getWidth(), getHeight(), 0, 0, (int) target
				.getLocation().getX(), (int) target.getLocation().getY(),
				target.getWidth(), target.getHeight(), 0, 0);
	}

	/**
	 * @return entities location.
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @return entities name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return entities height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return entities width.
	 */
	public int getWidth() {
		return width;
	}

	@Override
	public String toString() {
		return "Entity(Name: " + getName() + ", " + location.toString()
				+ ", Width: " + getWidth() + ", Height: " + getHeight() + ")";
	}
}
