package com.swap.entity;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.swap.action.Action;

public class Entity_Skeleton extends Entity {

	private static final Sprite sprite = new Sprite(new Texture(Gdx.files.internal("textures/entity_sprites/skeleton.png")));
	
	public Entity_Skeleton(int team, int position) {
		this.team = team;
		this.position = position;
		this.name = "Skeleton";

		this.maxHealth = 25;
		this.curHealth = maxHealth;
		
		// Set up the actions
		this.actions = new Action[] {Action.ACTION_ATTACK};
		this.swapActions = new Action[] {Action.ACTION_SWAP};

	}

	public Point render(float delta, SpriteBatch batch, Point spritePos) {
		// Are we drawing the enemy team
		if (team == 1) {
			sprite.flip(true, false);
		}

		// Get the position of this sprite
		spritePos.x = (int) (spritePos.x - sprite.getWidth() / 2);

		sprite.setPosition(spritePos.x, spritePos.y);
		sprite.draw(batch);

		// Flip it back if we need to
		if (team == 1) {
			sprite.flip(true, false);
		}

		return spritePos;
	}

}
