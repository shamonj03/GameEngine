package com.joe.engine.graphics.color;

import com.joe.engine.graphics.Color;

public class HSL implements Color {
	/**
	 * Converts the components of HSL into an RGB integer.
	 * 
	 * @param hue
	 *            Hue component 0-360.
	 * 
	 * @param saturation
	 *            Saturation component 0-1f.
	 * 
	 * @param lighting
	 *            Lighting component 0-1f.
	 */
	public static int packHSL(float hue, float saturation, float lighting) {
		if (lighting == 0) {
			return RGB16BitInteger.BLACK;
		} else if (lighting == 1) {
			return RGB16BitInteger.WHITE;
		} else {
			float chroma = (1 - Math.abs(2.f * lighting - 1.f)) * saturation;
			float quadrant = hue / 60.f;

			int quad_mod = (int) quadrant & 1;

			float x = chroma * (1 - Math.abs((quad_mod - 1)));
			float m = lighting - (chroma * 0.5f);

			float red_pos = 0, green_pos = 0, blue_pos = 0;

			if (quadrant < 1) {
				red_pos = chroma;
				green_pos = x;
				blue_pos = 0;
			} else if (quadrant < 2) {
				red_pos = x;
				green_pos = chroma;
				blue_pos = 0;
			} else if (quadrant < 3) {
				red_pos = 0;
				green_pos = chroma;
				blue_pos = x;
			} else if (quadrant < 4) {
				red_pos = 0;
				green_pos = x;
				blue_pos = chroma;
			} else if (quadrant < 5) {
				red_pos = x;
				green_pos = 0;
				blue_pos = chroma;
			} else if (quadrant < 6) {
				red_pos = chroma;
				green_pos = 0;
				blue_pos = x;
			}

			short r = (short) ((red_pos + m) * 255f);
			short g = (short) ((green_pos + m) * 255f);
			short b = (short) ((blue_pos + m) * 255f);
			return RGB16BitInteger.packRGB(r, g, b);
		}
	}

	/**
	 * Converts an RGB integer into individual HSL components.
	 * 
	 * @param RGB
	 *            Inputed RGB to convert to HSL.
	 * 
	 * @return hslArray Index: 0 - Hue 1 - Saturation 2 - Lighting
	 */
	public static float[] unpackHSL(int RGB) {
		float[] hslArray = new float[3];

		if (RGB == RGB16BitInteger.BLACK) {
			hslArray[0] = 0;
			hslArray[1] = 0;
			hslArray[2] = 0;
			return hslArray;
		} else if (RGB == RGB16BitInteger.WHITE) {
			hslArray[0] = 0;
			hslArray[1] = 0;
			hslArray[2] = 1;
			return hslArray;
		}

		int[] rgbArray = RGB16BitInteger.unpackRGB(RGB);

		float r_pos = rgbArray[0] / 255f;
		float g_pos = rgbArray[1] / 255f;
		float b_pos = rgbArray[2] / 255f;

		float c_max = Math.max(r_pos, Math.max(g_pos, b_pos));
		float c_min = Math.min(r_pos, Math.min(g_pos, b_pos));

		float delta = c_max - c_min;

		float hue = 0, saturation = 0, lighting;

		lighting = (c_max + c_min) / 2.0f;
		if (delta > 0) {
			if (c_max == r_pos) {
				hue = (((g_pos - b_pos) / delta) /* % 6 */);
			} else if (c_max == g_pos) {
				hue = ((b_pos - r_pos) / delta) + 2f;
			} else if (c_max == b_pos) {
				hue = ((r_pos - g_pos) / delta) + 4f;
			} else if (c_max == c_min) {
				hue = 0;
			}

			hue = hue * 60f;

			if (hue < 0) {
				hue = hue + 360;
			}

			if (delta == 0) {
				saturation = 0;
			} else {
				saturation = delta / (1 - Math.abs(2.f * lighting - 1.f));
			} //.......
		}

		hslArray[0] = hue;
		hslArray[1] = saturation;
		hslArray[2] = lighting;

		return hslArray;
	}

	/**
	 * Converts and RGB to HSL, offsets individual HSL Components, then finally
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
	 * @param lightingOffset
	 *            Offset to change lighting component.
	 */
	public static int offsetHSL(int RGB, float hueOffset,
			float saturationOffset, float lightingOffset) {
		if (RGB != RGB16BitInteger.BLACK) {
			float[] hsl = unpackHSL(RGB);
			/*
			 * Offset lighting.
			 */
			float original_lighting = hsl[2];
			float new_lighting = original_lighting + lightingOffset;

			if (new_lighting >= 1) {
				return RGB16BitInteger.WHITE;
			} else if (new_lighting <= 0) {
				return 0;
			}
			
			/*
			 * Offset hue.
			 */
			float original_hue = hsl[0];
			float new_hue = original_hue + hueOffset;

			if (new_hue > 360) {
				new_hue = 360;
			} else if (new_hue < 0) {
				new_hue = 0;
			}

			/*
			 * Offset saturation.
			 */
			float original_saturation = hsl[1];
			float new_saturation = original_saturation + saturationOffset;

			if (new_saturation > 1) {
				new_saturation = 1;
			} else if (new_saturation < 0) {
				new_saturation = 0;
			}

			return packHSL(new_hue, new_saturation, new_lighting);
		}
		return RGB;
	}

	/**
	 * Returns the Hue component from a RGB integer.
	 */
	public static float getHue(int RGB) {
		return unpackHSL(RGB)[0];
	}

	/**
	 * Returns the Saturation component from a RGB integer.
	 */
	public static float getSaturation(int RGB) {
		return unpackHSL(RGB)[1];
	}

	/**
	 * Returns the Lighting component from a RGB integer.
	 */
	public static float getLighting(int RGB) {
		return unpackHSL(RGB)[2];
	}
}
