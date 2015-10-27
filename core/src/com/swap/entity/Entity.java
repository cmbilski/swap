package com.swap.entity;

import java.awt.Point;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.swap.action.Action;

public abstract class Entity {

	// State related info
	protected int position;
	protected int team;
	
	// Unit stats
	protected int maxHealth;
	protected int curHealth;
	
	protected String name;
	
	protected Action [] actions;
	
	public abstract Point render(float delta, SpriteBatch batch);
	
	protected Point getSpritePosition() {
		// Entitie are offset from the center of the screen
		int xOffset = position * (200);
		int xPos;
		if (team == 0) {
			xPos = 800 - 100 - xOffset;
		} else {
			xPos = 800 + 100 + xOffset;
		}
		
		return new Point(xPos, 100);
	}
	
}
