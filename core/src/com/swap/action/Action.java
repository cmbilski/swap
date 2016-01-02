package com.swap.action;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.swap.entity.Entity;
import com.swap.state.Battle_State;

public abstract class Action {

	protected Sprite sprite;

	public static final Action ACTION_ATTACK = new Action_Attack();
	public static final Action ACTION_SWAP = new Action_Swap();

	public abstract void act(Battle_State state, Entity myEntity);

	public void render(SpriteBatch batch, float x, float y) {
		sprite.setPosition(x, y);
		sprite.draw(batch);
	}
	
	public boolean select(Battle_State state) {
		// Not all actions are necessarily selected
		// Some may be cancelled!
		return true;
	}

	public abstract int getSpeed();

}
