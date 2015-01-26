package com.joe.engine.graphics.color;

import com.joe.engine.graphics.Color;

/*
 * Used this as a learning experience.
 * Spent lots of time on wiki's.
 * 
 * Color formula's derived from
 * 		http://en.wikipedia.org/wiki/HSL_and_HSV
 * 		http://www.rapidtables.com/convert/color/hsl-to-rgb.htm
 * 		http://www.rapidtables.com/convert/color/rgb-to-hsl.htm
 * 		http://www.rapidtables.com/convert/color/hsv-to-rgb.htm
 * 		http://www.rapidtables.com/convert/color/rgb-to-hsv.htm
 */
public class RGB16BitInteger implements Color {
	/*
	 * Common colors.
	 */
	public static final int WHITE = 0xFFFFFF;
	public static final int SILVER = 0xC0C0C0;
	public static final int GRAY = 0x808080;
	public static final int BLACK = 0x000000;
	public static final int BLACK_2 = -16777216;
	public static final int BLACK_3 = 0;
	public static final int RED = 0xFF0000;
	public static final int MAROON = 0x800000;
	public static final int YELLOW = 0xFFFF00;
	public static final int OLIVE = 0x808000;
	public static final int LIME = 0x00FF00;
	public static final int GREEN = 0x008000;
	public static final int AQUA = 0x00FFFF;
	public static final int TEAL = 0x008080;
	public static final int BLUE = 0x0000FF;
	public static final int NAVY = 0x000080;
	public static final int FUCHSIA = 0xFF00FF;
	public static final int PURPLE = 0x800080;

	/**
	 * Compresses individual RGB into an single RGB integer.
	 * 
	 * @param red
	 *            Red component 0-255.
	 * 
	 * @param green
	 *            Green component 0-255.
	 * 
	 * @param blue
	 *            Blue component 0-255.
	 */
	public static int packRGB(int red, int green, int blue) {
		return (red << 16) | (green << 8) | blue;
	}
	/**
	 * Unpacks an RGB integer into 4 separate integers.
	 * 
	 * @param RGB
	 *            RGB Integer
	 * 
	 * @return rgbArray Index: 0 - Red, 1 - Green, 2 - Blue
	 */
	public static int[] unpackRGB(int RGB) {
		int[] rgbArray = new int[3];
		
		int red = ((RGB >> 16) & 0xFF);
		int green = ((RGB >> 8) & 0xFF);
		int blue = (RGB & 0xFF);

		rgbArray[0] = red;
		rgbArray[1] = green;
		rgbArray[2] = blue;
		return rgbArray;
	}

	/**
	 * Offsets individual RGB components and repacks into and RGB integer.
	 * 
	 * @param RGB
	 *            Inputed RGB Integer to change.
	 * 
	 * @param redOffset
	 *            Offset to change red component.
	 * 
	 * @param greenOffset
	 *            Offset to change green component.
	 * 
	 * @param blueOffset
	 *            Offset to change blue component.
	 */
	public static int offsetRGB(int RGB, int redOffset, int greenOffset,
			int blueOffset) {
		if (RGB != BLACK && RGB != BLACK_2 && RGB != BLACK_3) {
			int[] rgbArray = unpackRGB(RGB);

			/*
			 * Offset red.
			 */
			int original_red = rgbArray[0];
			int new_red = (original_red + redOffset);

			if (new_red < 1) {
				new_red = 0;
			} else if (new_red > 255) {
				new_red = 255;
			}

			/*
			 * Offset Green.
			 */
			int original_green = rgbArray[1];
			int new_green = (original_green + greenOffset);

			if (new_green < 1) {
				new_green = 0;
			} else if (new_green > 255) {
				new_green = 255;
			}

			/*
			 * Offset blue.
			 */
			int original_blue = rgbArray[2];
			int new_blue = (original_blue + blueOffset);

			if (new_blue < 1) {
				new_blue = 0;
			} else if (new_blue > 255) {
				new_blue = 255;
			}
			return packRGB((short) new_red, (short) new_green, (short) new_blue);
		}
		return RGB;
	}


	/**
	 * Returns the Red component from a RGB integer.
	 */
	public static int getRed(int RGB) {
		return unpackRGB(RGB)[0];
	}

	/**
	 * Returns the Green component from a RGB integer.
	 */
	public static int getGreen(int RGB) {
		return unpackRGB(RGB)[1];
	}

	/**
	 * Returns the Blue component from a RGB integer.
	 */
	public static int getBlue(int RGB) {
		return unpackRGB(RGB)[2];
	}
}
