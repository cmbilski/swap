package com.swap.action;

import com.swap.entity.Entity;
import com.swap.state.GameState;

public abstract class Action {

	public abstract void act(GameState state, Entity actor);
	
}
