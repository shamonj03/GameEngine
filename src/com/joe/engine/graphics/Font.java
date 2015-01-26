package com.joe.engine.graphics;

import java.io.File;
import java.util.HashMap;

import com.joe.engine.graphics.renderable.Sprite;
import com.joe.engine.graphics.renderable.sprite.SpriteSheet;

public class Font {

	/*
	 * Character used to replace unknowns.
	 */
	private static final char UNKOWN_CHARACTER = ' ';
	
	/*
	 * Sprite sheet used for the font.
	 */
	public static final SpriteSheet FONT_SHEET = new SpriteSheet(new File(
			"./data/font/minishfont_nontrans.png"), 16);

	/*
	 * Map containing the sprite for 
	 * respective character.
	 */
	private static HashMap<Character, Sprite> characterMap = new HashMap<>();

	/*
	 * Valid characters that can
	 * be inputed.
	 */
	public static final char[] VALID_CHARACTERS = { 'A', 'B', 'C', 'D', 'E',
			'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '\'', '-', ':', '!', '?',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ',', '.', ' ' };

	/*
	 * Initialize the character map
	 * for performance reasons.
	 */
	static {
		for (int i = 0; i < VALID_CHARACTERS.length; i++) {
			char c = VALID_CHARACTERS[i];
			if (i >= 0 && i < 13) {
				characterMap.put(c, FONT_SHEET.getSprite(i));
			} else if (i >= 13 && i < 26) {
				characterMap.put(c, FONT_SHEET.getSprite(i + 3));
			} else if (i >= 26 && i < 39) {
				characterMap.put(c, FONT_SHEET.getSprite(i + 54));
			} else if (i >= 39 && i < 52) {
				characterMap.put(c, FONT_SHEET.getSprite(i + 57));
			} else if (i >= 52 && i < 57) {
				characterMap.put(c, FONT_SHEET.getSprite(i + 4));
			} else if (i >= 57 && i < 70) {
				characterMap.put(c, FONT_SHEET.getSprite(i + 7));
			}
		}
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
	public static Sprite getCharacter(char c) {
		if(characterMap.containsKey(c)) {
			return characterMap.get(c);
		}
		return characterMap.get(UNKOWN_CHARACTER);
	}
}
