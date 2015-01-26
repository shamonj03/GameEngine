package com.joe.engine.model;

public class Location {

	private float x;
	private float y;
	private int z;

	/**
	 * Creates a new location.
	 * 
	 * @param x
	 *            X location on grid.
	 * @param y
	 *            Y location on grid.
	 * @param z
	 *            Z location in 2D application it is the layer/height level.
	 */
	public Location(float x, float y, int z) {
		this.setLocation(x, y, z);
	}

	/**
	 * Creates a new location with default height.
	 * 
	 * @param x
	 *            X location on grid.
	 * 
	 * @param y
	 *            Y location on grid.
	 */
	public Location(float x, float y) {
		this(x, y, 0);
	}

	/**
	 * Creates new location based on another location.
	 * 
	 * @param other
	 *            The location to be copied.
	 */
	public Location(Location other) {
		this(other.getX(), other.getY(), other.getZ());
	}

	/**
	 * Sets location based on another location.
	 * 
	 * @param other
	 *            The location to be copied.
	 */
	public void setLocation(Location other) {
		setX(other.getX());
		setY(other.getY());
		setZ(other.getZ());
	}

	/**
	 * Sets the location.
	 * 
	 * @param x
	 *            X location on grid.
	 * @param y
	 *            Y location on grid.
	 * @param z
	 *            Z location in 2D application it is the layer/height level.
	 */
	public void setLocation(float x, float y, int z) {
		setX(x);
		setY(y);
		setZ(z);
	}

	/**
	 * Sets the location with default height.
	 * 
	 * @param x
	 *            X location on grid.
	 * 
	 * @param y
	 *            Y location on grid.
	 */
	public void setLocation(float x, float y) {
		setLocation(x, y, 0);
	}

	/**
	 * Offsets current x location.
	 * 
	 * @param amount
	 *            Amount to offset by.
	 */
	public void offsetX(float amount) {
		this.x += amount;
	}

	/**
	 * Offsets current y location.
	 * 
	 * @param amount
	 *            Amount to offset by.
	 */
	public void offsetY(float amount) {
		this.y += amount;
	}

	/**
	 * Offsets current z location.
	 * 
	 * @param amount
	 *            Amount to offset by.
	 */
	public void offsetZ(int amount) {
		this.z += amount;
	}

	/**
	 * Set the x location.
	 * 
	 * @param x
	 *            X location to change to.
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Set the y location.
	 * 
	 * @param y
	 *            Y location to change to.
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Set the z location.
	 * 
	 * @param z
	 *            Z location to change to.
	 */
	public void setZ(int z) {
		this.z = z;
	}

	/**
	 * X location on grid.
	 * 
	 * @return x
	 */
	public float getX() {
		return x;
	}


	/**
	 * Y location on grid.
	 * 
	 * @return y
	 */
	public float getY() {
		return y;
	}

	/**
	 * Z location on grid.
	 * 
	 * @return z
	 */
	public int getZ() {
		return z;
	}

	/**
	 * Returns the x locations base
	 * 16 position.
	 * 
	 * @return (x / 16) * 16
	 */
	public int getTileX() {
		return ((int) x >> 4) << 4;
	}

	/**
	 * Returns the z locations base
	 * 16 position.
	 * 
	 * @return (z / 16) * 16
	 */
	public int getTileY() {
		return ((int) y >> 4) << 4;
	}

	/**
	 * Checks if location is within
	 * a radius. Current location is
	 * center.
	 * 
	 * @param other
	 * 		Other location to check.
	 * 
	 * @param radius
	 * 		Radius to search out to.
	 * 
	 * @return true/false
	 */
	public boolean inView(Location other, int radius) {
		return (getTileX() >= other.getTileX() - (radius / 2)
				&& getTileX() <= other.getTileX() + (radius / 2)
				&& getY() >= other.getTileY() - (radius / 2) && getTileY() <= other
				.getTileY() + (radius / 2));
	}

	/**
	 * Gets distance between two locations.
	 * Uses TileX and TileY.
	 * 
	 * @param other
	 * 		Other location used distance formula.
	 * 
	 * @return distance between locations.
	 */
	public int getDistance(Location other) {
		return (int) Math.sqrt(Math.pow((other.getTileX() - this.getTileX()), 2)
				+ Math.pow((other.getTileY() - this.getTileY()), 2));
	}
	

	/**
	 * Gets distance between two locations.
	 * 
	 * @param other
	 * 		Other location used distance formula.
	 * 
	 * @return distance between locations.
	 */
	public int getAbsDistance(Location other) {
		return (int) Math.sqrt(Math.pow((other.getX() - this.getX()), 2)
				+ Math.pow((other.getY() - this.getY()), 2));
	}
	/**
	 * Checks if another location is
	 * within range.
	 * 
	 * @param other
	 * 		Other location to check against.
	 * 
	 * @param distance
	 * 		Distance in pixels to check against.
	 * 
	 * @return true/false
	 */
	public boolean withinAbsDistance(Location other, int distance) {
		return Math.abs(getAbsDistance(other)) < distance;
	}

	/**
	 * Checks if another location is
	 * within range.
	 * Uses TileX and TileY.
	 * 
	 * @param other
	 * 		Other location to check against.
	 * 
	 * @param distance
	 * 		Distance in pixels to check against.
	 * 
	 * @return true/false
	 */
	public boolean withinDistance(Location other, int distance) {
		return Math.abs(getDistance(other)) < distance;
	}

	/**
	 * Get direction location is in.
	 * 
	 * @param other
	 * 		Location to check against.
	 * 
	 * @return Direction other is in.
	 */
	public Direction getDirection(Location other) {
		int x = (int) getX();
		int other_x = (int) other.getX();

		int y = (int) getY();
		int other_y = (int) other.getY();

		if (y == other_y) {
			if (x < other_x) {
				return Direction.WEST;
			} else if (x > other_x) {
				return Direction.EAST;
			}
		} else if (x == other_x) {
			if (y < other_y) {
				return Direction.NORTH;
			} else if (y > other_y) {
				return Direction.SOUTH;
			}
		} else if (x > other_x && y > other_y) {
			return Direction.NORTH_WEST;
		} else if (x < other_x && y > other_y) {
			return Direction.NORTH_EAST;
		} else if (x > other_x && y < other_y) {
			return Direction.SOUTH_WEST;
		} else if (x < other_x && y < other_y) {
			return Direction.SOUTH_EAST;
		}
		return Direction.NONE;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Location) {
			Location other = (Location) o;

			return ((int) other.x == (int) x && (int) other.y == (int) y && other.z == z);
		}
		return super.equals(o);
	}

	@Override
	public String toString() {
		return "Loaction(X: " + getX() + ", Y: " + getY() + ", Z: " + getZ()
				+ ")";
	}
}
