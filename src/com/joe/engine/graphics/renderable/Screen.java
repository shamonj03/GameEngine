package com.joe.engine.graphics.renderable;

import com.joe.engine.graphics.EngineFont;
import com.joe.engine.graphics.Renderable;

public class Screen extends Renderable {
	
	/**
	 * Creates a new screen to draw to.
	 * 
	 * @param width
	 * 		
	 * @param height
	 */
	public Screen(int width, int height) {
		this.initialize(width, height);
	}

	/**
	 * Draws a horizontal line.
	 * 
	 * @param RGB
	 *            RGB Integer color.
	 * @param x
	 *            X Position on renderable.
	 * @param y
	 *            Y Position on renderable.
	 * @param length
	 *            Length of line.
	 */
	public void drawHorizontalLine(int RGB, int x, int y, int length) {
		for (int i = 0; i < length; i++) {
			drawPixel(RGB, x + i, y);
		}
	}

	/**
	 * Draws a vertical line.
	 * 
	 * @param RGB
	 *            RGB Integer color.
	 * @param x
	 *            X Position on renderable.
	 * @param y
	 *            Y Position on renderable.
	 * @param length
	 *            Length of line.
	 */
	public void drawVerticalLine(int RGB, int x, int y, int length) {
		for (int i = 0; i < length; i++) {
			drawPixel(RGB, x, y + i);
		}
	}

	/**
	 * @Author Tech-Algorithm.com
	 *         http://tech-algorithm.com/articles/drawing-line
	 *         -using-bresenham-algorithm
	 * 
	 * @param RGB
	 *            RGB Integer of line.
	 * @param x
	 *            Starting x position on renderable.
	 * @param y
	 *            Starting y position on renderable.
	 * @param x2
	 *            Ending x position on renderable.
	 * @param y2
	 *            Ending y position on renderable.
	 */
	public void drawLine(int RGB, int x, int y, int x2, int y2) {
		int w = x2 - x;
		int h = y2 - y;
		int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
		if (w < 0)
			dx1 = -1;
		else if (w > 0)
			dx1 = 1;
		if (h < 0)
			dy1 = -1;
		else if (h > 0)
			dy1 = 1;
		if (w < 0)
			dx2 = -1;
		else if (w > 0)
			dx2 = 1;
		int longest = Math.abs(w);
		int shortest = Math.abs(h);
		if (!(longest > shortest)) {
			longest = Math.abs(h);
			shortest = Math.abs(w);
			if (h < 0)
				dy2 = -1;
			else if (h > 0)
				dy2 = 1;
			dx2 = 0;
		}
		int numerator = longest >> 1;
		for (int i = 0; i <= longest; i++) {
			drawPixel(RGB, x, y);
			numerator += shortest;
			if (!(numerator < longest)) {
				numerator -= longest;
				x += dx1;
				y += dy1;
			} else {
				x += dx2;
				y += dy2;
			}
		}
	}

	/**
	 * Draws a filled rectangle.
	 * 
	 * @param RGB
	 *            RGB Integer to color circle.
	 * 
	 * @param startX
	 *            X position on renderable.
	 * 
	 * @param startY
	 *            Y position on renderable.
	 * 
	 * @param width
	 *            Width of rectangle.
	 * 
	 * @param height
	 *            height of rectangle.
	 */
	public void fillRectangle(int RGB, int startX, int startY, int width,
			int height) {
		for (int y = startY; y < (startY + height); y++) {
			drawHorizontalLine(RGB, startX, y, width);
		}
	}

	/**
	 * Draws a hollow rectangle.
	 * 
	 * @param RGB
	 *            RGB Integer to color circle.
	 * 
	 * @param startX
	 *            X position on renderable.
	 * 
	 * @param startY
	 *            Y position on renderable.
	 * 
	 * @param width
	 *            Width of rectangle.
	 * 
	 * @param height
	 *            height of rectangle.
	 */
	public void drawRectangle(int RGB, int startX, int startY, int width,
			int height) {

		for (int x = startX; x < (startX + width + 1); x++) {
			drawPixel(RGB, x, startY);
			drawPixel(RGB, x, startY + height);
		}
		for (int y = startY; y < (startY + height + 1); y++) {
			drawPixel(RGB, startX, y);
			drawPixel(RGB, startX + width, y);
		}
	}

	/**
	 * Draws a hollow circle.
	 * 
	 * @param RGB
	 *            RGB Integer to color circle.
	 * 
	 * @param startX
	 *            X position on renderable.
	 * 
	 * @param startY
	 *            Y position on renderable.
	 * 
	 * @param radius
	 *            Radius of circle.
	 */
	public void drawCircle(int RGB, int startX, int startY, int radius) {
		for (int i = 0; i < 360; i++) {
			float x_off = (radius / 2) * (float) Math.cos(i * Math.PI / 180);
			float y_off = (radius / 2) * (float) Math.sin(i * Math.PI / 180);
			drawPixel(RGB, (int) (startX + (radius / 2) + x_off), (int) (startY
					+ (radius / 2) + y_off));
		}
	}

	/**
	 * Draws a filled circle.
	 * 
	 * @param RGB
	 *            RGB Integer to color circle.
	 * 
	 * @param startX
	 *            X position on renderable.
	 * 
	 * @param startY
	 *            Y position on renderable.
	 * 
	 * @param radius
	 *            Radius of circle.
	 */
	public void fillCircle(int RGB, int startX, int startY, int radius) {
		// Kinda slow
		for (int r = 0; r <= radius; r += 2) {
			drawCircle(RGB, startX + (r / 2), startY + (r / 2), radius - r);
		}
	}

	/**
	 * Draws a sprite.
	 * 
	 * @param sprite
	 *            Sprite to be drawn.
	 * 
	 * @param x
	 *            X position on renderable.
	 * 
	 * @param y
	 *            Y position on renderable.
	 */
	public void drawSprite(Sprite sprite, int x, int y) {
		// Kinda slow
		for (short y_off = 0; y_off < sprite.getHeight(); y_off++) {
			for (short x_off = 0; x_off < sprite.getWidth(); x_off++) {
				int rgb = sprite.getPixels()[x_off 
						+ (y_off * sprite.getWidth())];
				if (isTransparentColor(rgb))
					continue;
				drawPixel(rgb, (x + x_off), (y + y_off));
			}
		}
	}

	/**
	 * Draws a sprite with a forced RGB.
	 * 
	 * @param sprite
	 *            Sprite to be drawn.
	 * 
	 * @param RGB
	 *            RGB to se the sprite to.
	 * 
	 * @param x
	 *            X position on renderable.
	 * 
	 * @param y
	 *            Y position on renderable.
	 */
	public void drawSprite(Sprite sprite, int RGB, int x, int y) {
		for (short y_off = 0; y_off < sprite.getHeight(); y_off++) {
			for (short x_off = 0; x_off < sprite.getWidth(); x_off++) {
				int color = sprite.getPixels()[x_off
						+ (y_off * sprite.getWidth())];
				if (isTransparentColor(color))
					continue;
				drawPixel(RGB, x + x_off, y + y_off);
			}
		}
	}

	/**
	 * Draws a string on the renderable object at (x, y) with a set RGB.
	 * 
	 * @param text
	 *            Text to draw.
	 * 
	 * @param RGB
	 *            RGB Integer to set text to.
	 * 
	 * @param x
	 *            X Position on renderable.
	 * 
	 * @param y
	 *            Y Position on renderable.
	 */
	public void drawString(String text, EngineFont font, int RGB, int x, int y) {
		char[] charArray = text.toCharArray();

		int x_off = 0;
		int y_off = 0;

		
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			Sprite char_sprite = font.getCharacter(c);

			/*
			 * New Line padding.
			 */
			if (c == '\n') {
				y_off += 16;
				x_off = 0;
			}

			if (char_sprite == null)
				continue;

			/*
			 * Padding right.
			 */
			if (c == '!' || c == '?') {
				x_off += 2;
			}

			drawSprite(char_sprite, RGB, x + x_off, y + y_off);

			/*
			 * Padding left.
			 */

			int next_char_index = i + 1;

			if (next_char_index < charArray.length) {
				char next_char = charArray[next_char_index];

				if (next_char == '!' || next_char == '.') {
					x_off += 4;
				} else if (next_char == (next_char | 32)) {
					x_off += 6;
				} else {
					x_off += 7;
				}
			}
		}
	}
}
