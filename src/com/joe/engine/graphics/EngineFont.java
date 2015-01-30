package com.joe.engine.graphics;

import java.util.HashMap;

import com.joe.engine.graphics.renderable.Sprite;
import com.joe.engine.graphics.renderable.sprite.SpriteSheet;
import com.joe.engine.util.EngineConstants;

public abstract class EngineFont {
	
	/*
	 * Sprite sheet used for the font.
	 */
	public SpriteSheet fontSheet;

	/*
	 * Map containing the sprite for 
	 * respective character.
	 */
	private HashMap<Character, Sprite> characterMap = new HashMap<>();
	/**
	 * Creates a new font.
	 * 
	 * @param fonSheet
	 */
	public EngineFont(SpriteSheet fonSheet) {
		this.fontSheet = fonSheet;
		this.initializeCharacters();
	}
	
	/**
	 * Map all the characters out here so that we
	 * do not need to make a new sprite everytime
	 * we grab one.
	 */
	public abstract void initializeCharacters();
	
	/**
	 * Set the index of the character that is
	 * the same as in the tile sheet.
	 * 
	 * @param c
	 * 		Inputed character.
	 * @param index
	 * 		Index in tile sheet.
	 */
	public void mapCharacterToFontSheet(char c, int index) {
		characterMap.put(c, fontSheet.getSprite(index));
	}

	/**
	 * Get the sprite for the inputed
	 * character from the sprite sheet.
	 * 
	 * @param c
	 * 		Character being inputed.
	 * 
	 * @return
	 * 		Sprite from sprite sheet.
	 */
	public Sprite getCharacter(char c) {
		if(characterMap.containsKey(c)) {
			return characterMap.get(c);
		}
		return characterMap.get(EngineConstants.UNKOWN_CHARACTER);
	}
	
	/**
	 * @return the font sheet.
	 */
	public SpriteSheet getFonSheet() {
		return fontSheet;
	}
}
