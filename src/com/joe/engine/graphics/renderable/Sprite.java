package com.joe.engine.graphics.renderable;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.joe.engine.graphics.Renderable;

public class Sprite extends Renderable {

	private BufferedImage image;

	/**
	 * Create a new sprite from a file.
	 * 
	 * @param file
	 * 		Location to sprite.
	 */
	public Sprite(File file) {
		try {
			if (file.exists()) {
				setImage(ImageIO.read(file));
				initialize(getImage().getWidth(), getImage().getHeight());
				getImage().getRGB(0, 0, getWidth(), getHeight(), pixels, 0,
						getWidth());
			} else {
				System.out.println("Sprite not found: " + file.getPath());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new blank sprite.
	 * 
	 * @param width
	 * 		Width of sprite.
	 * 
	 * @param height
	 * 		height of sprite.
	 */
	public Sprite(int width, int height) {
		initialize(width, height);
	}

	/**
	 * Sets the buffered image of the
	 * sprite.
	 * 
	 * @param image
	 * 		Buffered image to be used
	 * 		for sprite.
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}


	/**
	 * @return image
	 * 		Returns buffered image
	 * 		of sprite.
	 */
	public BufferedImage getImage() {
		return image;
	}
}
