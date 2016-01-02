package com.swap.action;

import com.swap.entity.Entity;
import com.swap.state.Battle_State;
import com.swap.utility.SpriteManager;

public class Action_Swap extends Action {

	public Action_Swap() {
		this.sprite = SpriteManager.getSprite("actions/action_swap");
	}
	
	@Override
	public void act(Battle_State state, Entity myEntity) {
		// Swap with the front
		state.swapWithFront(myEntity);
	}

	@Override
	public int getSpeed() {
		return 0;
	}

	

}
