package com.swap.utility;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteManager {
	
	public static final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
	
	public static Sprite getSprite(String spriteName) {
		// If we already have it, return it
		Sprite returnSprite = sprites.get(spriteName);
		if (returnSprite != null) {
			return returnSprite;
		}
		// Otherwise, load it
		returnSprite = new Sprite(new Texture(Gdx.files.internal("textures/" + spriteName + ".png")));
		sprites.put(spriteName, returnSprite);
		
		return returnSprite;
		
	}

}
