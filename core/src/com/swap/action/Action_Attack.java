package com.swap.action;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.swap.entity.Entity;
import com.swap.state.Battle_State;
import com.swap.utility.SpriteManager;

public class Action_Attack extends Action {

	private static final int DAMAGE = 10;
	
	public Action_Attack() {
		this.sprite = SpriteManager.getSprite("actions/action_attack");
	}
	
	public void act(Battle_State state, Entity myEntity) {
		// Get the opposing entity
		Entity enemyEntity = myEntity.getTeam() == 0 ? state.getCPUTeam()[0] : state.getPlayerTeam()[0];
		int hpRemaining = enemyEntity.damage(DAMAGE);
		
		System.out.printf("Enemy (%s) has %d hp left!\n", enemyEntity.getName(), hpRemaining);
	}

	@Override
	public int getSpeed() {
		return 2;
	}

}
