package com.joe.engine.graphics.color;

import com.joe.engine.graphics.Color;

/**
 * @author Joe
 *
 * Preserves transparency.
 */
public class RGBA24BitInteger implements Color {
	/**
	 * Compresses RGB including alpha into a single RGBA integer.
	 * 
	 * @param red
	 *            Red component 0-255.
	 * 
	 * @param green
	 *            Green component 0-255.
	 * 
	 * @param blue
	 *            Blue component 0-255.
	 * 
	 * @param alpha
	 *            Alpha component 0-255.
	 */
	public static int packRGBA(int red, int green, int blue, int alpha) {
		return (alpha << 24) | RGB16BitInteger.packRGB(red, green, blue);
	}

	/**
	 * Unpacks an RGB integer into 4 separate integers.
	 * 
	 * @param RGBA
	 *            RGB Integer
	 * 
	 * @return rgbArray Index: 0 - Red 1 - Green 2 - Blue 3 - Alpha
	 */
	public static int[] unpackRGBA(int RGBA) {
		int[] rgbArray = new int[4];
		
		int alpha = getAlpha(RGBA);
		
		int[] RGB = RGB16BitInteger.unpackRGB(RGBA);

		rgbArray[0] = RGB[0];
		rgbArray[1] = RGB[1];
		rgbArray[2] = RGB[2];
		rgbArray[3] = alpha;
		return rgbArray;
	}
	

	/**
	 * Offsets individual RGB components and repacks into and RGB integer.
	 * 
	 * @param RGBA
	 *            Inputed RGBA Integer to change.
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
	public static int offsetRGB(int RGBA, int redOffset, int greenOffset,
			int blueOffset, int alphaOffset) {
		if (RGBA != 0) {
			int[] rgbArray = unpackRGBA(RGBA);

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
			
			/*
			 * Offset Alpha
			 */
			int original_alpha = rgbArray[3];
			int new_alpha = (original_alpha + alphaOffset);

			if (new_alpha < 1) {
				new_alpha = 0;
			} else if (new_alpha > 255) {
				new_alpha = 255;
			}
			
			return packRGBA(new_red, new_green, new_blue, new_alpha);
		}
		return RGBA;
	}
	

	/**
	 * Returns the Red component from a RGBA integer.
	 */
	public static int getRed(int RGBA) {
		return (RGBA >> 16) & 0xFF;
	}

	/**
	 * Returns the Green component from a RGBA integer.
	 */
	public static int getGreen(int RGBA) {
		return (RGBA >> 8) & 0xFF;
	}

	/**
	 * Returns the Blue component from a RGBA integer.
	 */
	public static int getBlue(int RGBA) {
		return RGBA & 0xFF;
	}
	
	/**
	 * Returns the alpha component from the RGBA integer.
	 */
	public static int getAlpha(int RGBA) {
		return (RGBA >> 24) & 0xFF;
	}
}
