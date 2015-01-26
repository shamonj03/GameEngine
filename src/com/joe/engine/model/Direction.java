package com.joe.engine.model;

public enum Direction {

	/*
	 * An enum of offsets to move in a given direction.
	 */
	NORTH(0, -1), SOUTH(0, 1), EAST(1, 0), WEST(-1, 0), NORTH_EAST(1, -1), NORTH_WEST(
			-1, -1), SOUTH_EAST(1, 1), SOUTH_WEST(-1, 1), NONE(0, 0);

	private final int xOffset;
	private final int yOffset;

	/**
	 * Creates a new direction.
	 * 
	 * @param xOffset
	 *            xOffset from origin direction is in.
	 * 
	 * @param yOffset
	 *            yOffset from origin direction is in.
	 */
	Direction(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	/**
	 * @return xOffset of direction away from the origin.
	 */
	public int getXOffset() {
		return xOffset;
	}

	/**
	 * @return YOffset of direction away from the origin.
	 */
	public int getYOffset() {
		return yOffset;
	}

	@Override
	public String toString() {
		return "Direction(xOffset: " + getXOffset() + ", yOffset: "
				+ getYOffset() + ")";
	}
}
