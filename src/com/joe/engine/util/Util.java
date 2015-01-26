package com.joe.engine.util;

import java.util.Random;

public class Util {

	private static final Random random = new Random();

	/**
	 * Rolls a random number between min and max
	 * 
	 * @param min
	 * @param max
	 * @return random number.
	 */
	public static int getRandomInRange(int min, int max) {
		return random.nextInt(((max - min) + 1) + min);
	}

	/**
	 * @return instance of java.util.Random.
	 */
	public static Random getRandom() {
		return random;
	}

	/**
	 * Check if a location is in bounds of another
	 * 
	 * @param y
	 * @param height
	 * @param offsetY
	 * @param y2
	 * @param height2
	 * @param offsetY2
	 * @return
	 */
	public static boolean inBounds(int x, int y, int width, int height,
			int offsetX, int offsetY, int x2, int y2, int width2, int height2,
			int offsetX2, int offsetY2) {
		return inXBounds(x, width, offsetX, x2, width2, offsetX2)
				&& inYBounds(y, height, offsetY, y2, height2, offsetY2);
	}

	/**
	 * Check if a x location is in bounds of another
	 * 
	 * @param y
	 * @param height
	 * @param offsetY
	 * @param y2
	 * @param height2
	 * @param offsetY2
	 * @return
	 */
	public static boolean inXBounds(int x, int width, int offsetX, int x2,
			int width2, int offsetX2) {
		return (x + width - 1 + offsetX) >= x2 + offsetX2
				&& x + offsetX <= (x2 + width2 - 1 + offsetX2);
	}

	/**
	 * Check if a y location is in bounds of another
	 * 
	 * @param y
	 * @param height
	 * @param offsetY
	 * @param y2
	 * @param height2
	 * @param offsetY2
	 * @return
	 */
	public static boolean inYBounds(int y, int height, int offsetY, int y2,
			int height2, int offsetY2) {
		return (y + height - 1 + offsetY) >= y2 + offsetY2
				&& y + offsetY <= (y2 + height2 - 1 + offsetY2);
	}

	/**
	 * <3 generics.
	 * 
	 * @param t
	 *            Array being taken in.
	 * @return Random element from array.
	 */
	public static <T> T getRandomElement(T[] t) {
		return t[random.nextInt(t.length)];
	}
}
