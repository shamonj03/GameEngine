package com.joe.engine.graphics.renderable.sprite;

import java.io.File;

import com.joe.engine.graphics.renderable.Sprite;

public class SpriteSheet extends Sprite {

	private Sprite[] sprites;
	private int cellWidth;
	private int cellHeight;

	/**
	 * Creates a new sprite sheet from a file.
	 * 
	 * @param file
	 *            Location to sprite sheet.
	 * 
	 * @param cellWidth
	 *            Width of individual sprite's.
	 * 
	 * @param cellHeight
	 *            Height of individual sprite's.
	 */
	public SpriteSheet(File file, int cellWidth, int cellHeight) {
		super(file);
		setCellDimensions(cellWidth, cellHeight);
		initalizeSprites();
		divideSprites();
	}

	/**
	 * Creates a new sprite sheet from a file.
	 * 
	 * @param file
	 *            Location to sprite sheet.
	 * 
	 * @param size
	 *            Size of square individual sprite's.
	 */
	public SpriteSheet(File file, int size) {
		this(file, size, size);
	}

	/**
	 * Divides sprite sheet into Individual sprite's.
	 */
	public void divideSprites() {
		int sprite_index = 0;

		for (int row = 0; row < getHeight(); row += cellHeight) {
			for (int col = 0; col < getWidth(); col += cellWidth) {
				Sprite sub_sprite = new Sprite(cellWidth, cellHeight);

				for (int y = 0; y < cellHeight; y++) {
					for (int x = 0; x < cellWidth; x++) {

						int rgb = getPixels()[(col + x)
								+ ((row + y) * getWidth())];

						sub_sprite.drawPixel(rgb, x, y);
					}
				}
				sprites[sprite_index++] = sub_sprite;
			}
		}
	}

	/**
	 * Sets the dimensions of the individual sprites.
	 * 
	 * @param width
	 *            Width of individual sprite's.
	 * 
	 * @param height
	 *            Height of individual sprite's.
	 */
	public void setCellDimensions(int width, int height) {
		this.cellWidth = width;
		this.cellHeight = height;
	}

	/**
	 * Initialize the sprite array.
	 */
	public void initalizeSprites() {
		sprites = new Sprite[cellWidth * cellHeight];
	}

	/**
	 * Get's a sprite at x and y in sprite sheet.
	 * 
	 * @param x
	 *            X index in sprite sheet.
	 * 
	 * @param y
	 *            Y index in sprite sheet.
	 * 
	 * @return Sprite from the sprite sheet.
	 */
	public Sprite getSprite(int x, int y) {
		return sprites[x + (y * cellWidth)];
	}

	/**
	 * Get's sprite from sprite sheet based on it's index.
	 * 
	 * @param index
	 *            Index in sprite sheet.
	 * 
	 * @return Sprite from the sprite sheet.
	 */
	public Sprite getSprite(int index) {
		return sprites[index];
	}

	/**
	 * @return Height of sprite's.
	 */
	public int getCellHeight() {
		return cellHeight;
	}

	/**
	 * @return Width of sprite's.
	 */
	public int getCellWidth() {
		return cellWidth;
	}

	/**
	 * @return Sprite's array.
	 */
	public Sprite[] getSprites() {
		return sprites;
	}
}
