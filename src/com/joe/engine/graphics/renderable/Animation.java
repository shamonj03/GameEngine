package com.joe.engine.graphics.renderable;

import java.io.File;

import com.joe.engine.graphics.Renderable;
import com.joe.engine.graphics.renderable.sprite.SpriteSheet;

public class Animation extends Renderable {

	private int id;

	private SpriteSheet sheet;

	private int[] subFrames;
	
	private int delay;
	private long elapsedTime;
	
	private Sprite currentSprite;
	private int currentIndex = 0;

	/*
	 * Sets the animations default values.
	 * 
	 * @param id
	 * 		The animation id
	 * @param sheetSrc
	 * 		The path to the sprite sheet
	 * @param delay
	 * 		The time it takes to iterate between frames
	 * @param cellWidth
	 * 		The width each cell of the sprite sheet is
	 * @param cellHeight
	 * 		The height each cell  of  the sprite sheet is
	 * @param subFrames
	 * 		The frames that are used in the sprite sheet
	 * 		for the animation
	 */
	public void setAnimation(int id, File source, int delay, int cellWidth, int cellHeight,
			int... subFrames) {
		this.id = id;
		this.delay = delay;
		this.sheet = new SpriteSheet(source, cellWidth, cellHeight);
		this.subFrames = subFrames;
		this.currentSprite = sheet.getSprite(subFrames[0]);

		this.elapsedTime = System.currentTimeMillis();
	}
	
	/*
	 * Resets the current animation to
	 * the first index regardless of
	 * its current delay
	 */
	public void reset() {
		currentIndex = 0;
        elapsedTime = delay;
    }

	/*
	 * Iterate for the frames through the
	 * animation if it is above the set interval.
	 * 
	 * When the current index is above the subFrames
	 * length set the currentIndex to 0 to
	 * repeat the animation.
	 */
    public void itterateFrames() {
        if (currentIndex >= subFrames.length) {
            currentIndex = 0;
        }
        if ((System.currentTimeMillis() - elapsedTime) > delay) {
            currentSprite = sheet.getSprite(subFrames[currentIndex++]);
            elapsedTime = System.currentTimeMillis();
        }
    }

    /*
     * @return id
     * 		The animations ID
     */
	public int getId() {
		return id;
	}

	/*
	 * @return delay
	 * 		The delay between each from
	 */
	public int getDelay() {
		return delay;
	}
	
	/*
	 * @return Length of animation in ms.
	 */
	public long getLength() {
		return delay * subFrames.length;
	}
	
	/*
	 * @return sheet
	 * 		The sprite sheet used for the animation
	 */
	public SpriteSheet getSheet() {
		return sheet;
	}

	/*
	 * Returns the current frame id
	 */
	public int getCurrentFrameId() {
		return subFrames[currentIndex];
	}

	/*
	 * @return currentSprite
	 * 		The current sprite the animation is on
	 */
	public Sprite getCurrentSprite() {
		return currentSprite;
	}
	
	/*
	 * If the current index is 
	 * greater then or equal to the
	 * length of the frames the animation
	 * is done.
	 */
	public boolean isFinished() {
		return currentIndex >= subFrames.length;
	}

	/*
	 * @return elapsedTime
	 * 		The amount of time the animation
	 * 		has currently gone through
	 */
	protected long getElapsedTime() {
		return elapsedTime;
	}

	/*
	 * @return subFrames
	 * 		The collection of frames used in the
	 * 		animation. We can use selected frames
	 * 		from the sprite sheet to save space.
	 */
	public int[] getSubFrames() {
		return subFrames;
	}
}
