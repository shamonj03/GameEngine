package com.joe.engine.util;

import com.joe.engine.model.Direction;

public class EngineConstants {
	/*
	 * Engine stuff
	 */
	public static final byte BUFFER_STRATEGY_LAYERS = 2;
	
	public static final byte FRAME_RATE_UNLIMITED = -1;
	
	/*
	 * Directions
	 */
	public static final Direction[] IMMEDIATE_DIRECTIONS = { Direction.NORTH,
			Direction.EAST, Direction.SOUTH, Direction.WEST };

	public static final Direction[] ADJACENT_DIRECTIONS = {
			Direction.NORTH_WEST, Direction.NORTH, Direction.NORTH_EAST,
			Direction.EAST, Direction.SOUTH_EAST, Direction.SOUTH,
			Direction.SOUTH_WEST, Direction.WEST };

	public static final Direction[] DIAGONAL_DIRECTIONS = {
			Direction.NORTH_WEST, Direction.NORTH_EAST, Direction.SOUTH_EAST,
			Direction.SOUTH_WEST };
	
	/**
	 * Default constructor to prevent instantiation.
	 */
	private EngineConstants() {
		
	}
}
