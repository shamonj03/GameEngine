package com.joe.engine.graphics.color;

import com.joe.engine.graphics.Color;

public class HSV implements Color {
	/**
	 * Converts the components of HSV into an RGB integer.
	 * 
	 * @param hue
	 *            Hue component 0-360.
	 * 
	 * @param saturation
	 *            Saturation component 0-1f.
	 * 
	 * @param value
	 *            Value component 0-1f.
	 */
	public static int packHSV(float hue, float saturation, float value) {
		float chroma = saturation * value;

		float quadrant = hue / 60.f;

		int quad_mod = (int) quadrant & 1;
		
		float x = chroma * (1 - Math.abs(quad_mod - 1));
		float m = value - chroma;

		float red_pos = 0, green_pos = 0, blue_pos = 0;

		if (quadrant <= 1) {
			red_pos = chroma;
			green_pos = x;
			blue_pos = 0;
		} else if (quadrant <= 2) {
			red_pos = x;
			green_pos = chroma;
			blue_pos = 0;
		} else if (quadrant <= 3) {
			red_pos = 0;
			green_pos = chroma;
			blue_pos = x;
		} else if (quadrant <= 4) {
			red_pos = 0;
			green_pos = x;
			blue_pos = chroma;
		} else if (quadrant <= 5) {
			red_pos = x;
			green_pos = 0;
			blue_pos = chroma;
		} else if (quadrant <= 6) {
			red_pos = chroma;
			green_pos = 0;
			blue_pos = x;
		}

		short r = (short) ((red_pos + m) * 255.f);
		short g = (short) ((green_pos + m) * 255.f);
		short b = (short) ((blue_pos + m) * 255.f);
		return RGB16BitInteger.packRGB(r, g, b);
	}

	/**
	 * Converts an RGB integer into individual HSV components.
	 * 
	 * @param RGB
	 *            Inputed RGB to convert to HSL.
	 * 
	 * @return hsvArray Index: 0 - Hue 1 - Saturation 2 - Value
	 */
	public static float[] unpackHSV(int RGB) {
		float[] hsvArray = new float[3];
		int[] rgbArray = RGB16BitInteger.unpackRGB(RGB);

		float r_pos = rgbArray[0] / 255f;
		float g_pos = rgbArray[1] / 255f;
		float b_pos = rgbArray[2] / 255f;

		float c_max = Math.max(r_pos, Math.max(g_pos, b_pos));
		float c_min = Math.min(r_pos, Math.min(g_pos, b_pos));

		float delta = c_max - c_min;

		float hue = 0, saturation = 0, value;

		value = c_max;

		if (delta != 0) {
			if (c_max == r_pos) {
				hue = (((g_pos - b_pos) / delta) /* % 6 */);
			} else if (c_max == g_pos) {
				hue = ((b_pos - r_pos) / delta) + 2f;
			} else if (c_max == b_pos) {
				hue = ((r_pos - g_pos) / delta) + 4f;
			} else {
				hue = 0;
			}

			hue = hue * 60.f;
			
			if(hue < 0) {
				hue = hue + 360;
			}

			if (c_max == 0) {
				saturation = 0;
			} else {
				saturation = delta / c_max;
			}
		}
		hsvArray[0] = hue;
		hsvArray[1] = saturation;
		hsvArray[2] = value;
		return hsvArray;
	}

	/**
	 * Converts and RGB to HSV, offsets individual HSV Components, then finally
	 * repacks into RGB integer.
	 * 
	 * @param RGB
	 *            Inputed RGB Integer to offset.
	 * 
	 * @param hueOffset
	 *            Offset to change hue. component.
	 * 
	 * @param saturationOffset
	 *            Offset to change saturation. component.
	 * 
	 * @param valueOffset
	 *            Offset to change lighting component.
	 */
	public static int offsetHSV(int RGB, float hueOffset,
			float saturationOffset, float valueOffset) {
		if (RGB != 0) {
			float[] hsv = unpackHSV(RGB);

			/*
			 * Offset hue.
			 */
			float original_hue = hsv[0];
			float new_hue = original_hue + hueOffset;

			if (new_hue > 360) {
				new_hue = 360;
			} else if (new_hue < 0) {
				new_hue = 0;
			}

			/*
			 * Offset saturation.
			 */
			float original_saturation = hsv[1];
			float new_saturation = original_saturation + saturationOffset;

			if (new_saturation > 1.f) {
				new_saturation = 1.f;
			} else if (new_saturation < 0) {
				new_saturation = 0;
			}

			/*
			 * Offset value.
			 */
			float original_value = hsv[2];
			float new_value = original_value + valueOffset;

			if (new_value > 1.f) {
				new_value = 1.f;
			} else if (new_value < 0) {
				new_value = 0;
			}

			return packHSV(new_hue, new_saturation, new_value);
		}
		return RGB;
	}
	

	/**
	 * Returns the Hue component from a RGB integer.
	 */
	public static float getHue(int RGB) {
		return unpackHSV(RGB)[0];
	}

	/**
	 * Returns the Saturation component from a RGB integer.
	 */
	public static float getSaturation(int RGB) {
		return unpackHSV(RGB)[1];
	}

	/**
	 * Returns the Value component from a RGB integer.
	 */
	public static float getValue(int RGB) {
		return unpackHSV(RGB)[2];
	}
}
