package com.joe.engine.util;

import com.joe.engine.model.Direction;

public class EngineConstants {
	/*
	 * Engine stuff
	 */
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
	 * Valid characters that can be used for input.
	 */
	public static final char[] VALID_CHARACTERS = { 'A', 'B', 'C', 'D', 'E',
			'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '\'', '-', ':', '!', '?',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ',', '.', ' ' };
	
	/**
	 * Character used to replace unknown characters.
	 */
	public static final char UNKOWN_CHARACTER = ' ';

	
	/**
	 * Default constructor to prevent instantiation.
	 */
	private EngineConstants() {
		
	}
}
