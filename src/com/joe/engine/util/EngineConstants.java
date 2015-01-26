package com.joe.engine.util;

import com.joe.engine.model.Direction;

public class EngineConstants {
	/*
	 * Engine stuff
	 */
	
	/**
	 * The amount of buffer strategy layers to use for page flipping.
	 */
	public static final byte BUFFER_STRATEGY_LAYERS = 2;
	
	/**
	 * FPS Set to -1 does not limit frame rate.
	 */
	public static final byte FRAME_RATE_UNLIMITED = -1;
	
	/*
	 * Directions
	 */
	
	/**
	 * Array of directions that contains the immediate tiles touching you.
	 * N, S, E, W...
	 */
	public static final Direction[] IMMEDIATE_DIRECTIONS = { Direction.NORTH,
			Direction.EAST, Direction.SOUTH, Direction.WEST };

	/**
	 * Array of directions that contains the diagonals.
	 */
	public static final Direction[] DIAGONAL_DIRECTIONS = {
			Direction.NORTH_WEST, Direction.NORTH_EAST, Direction.SOUTH_EAST,
			Direction.SOUTH_WEST };

	/**
	 * Array of directions containing all major directions.
	 * Immediate and diagonal.
	 */
	public static final Direction[] ADJACENT_DIRECTIONS = {
			Direction.NORTH_WEST, Direction.NORTH, Direction.NORTH_EAST,
			Direction.EAST, Direction.SOUTH_EAST, Direction.SOUTH,
			Direction.SOUTH_WEST, Direction.WEST };
	
	/**
	 * Default constructor to prevent instantiation.
	 */
	private EngineConstants() {
		
	}
}
