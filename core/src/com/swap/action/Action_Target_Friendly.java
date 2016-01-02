package com.swap.action;

import com.swap.entity.Entity;
import com.swap.input.IP_Battle_Select_Target;
import com.swap.state.Battle_State;
import com.swap.utility.InputManager;

public abstract class Action_Target_Friendly extends Action {

	// Eventually, when this action acts, it will need a target
	protected Entity target;
	
	public boolean select(Battle_State state) {
		// When this action is selected, if we have a valid target, return true
		// Otherwise, we override the input manager and return false
		if (hasValidTarget()) {
			// If we have a target, we will return true
			return true;
		}
		
		IP_Battle_Select_Target targetInput = new IP_Battle_Select_Target(state, this);
		InputManager.addInputProcessor(targetInput);
		
		return false;
	}
	
	@Override
	public int getSpeed() {
		return 0;
	}
	
	public void setTarget(Entity target) {
		this.target = target;
	}

	public void clearTarget() {
		this.target = null;
	}
	
	protected boolean hasValidTarget() {
		return target != null;
	}
	
}
