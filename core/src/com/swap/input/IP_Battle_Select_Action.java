package com.swap.input;

import com.badlogic.gdx.InputProcessor;
import com.swap.action.Action;
import com.swap.entity.Entity;
import com.swap.state.Battle_State;
import com.swap.utility.InputManager;
import com.swap.utility.KeyManager;

public class IP_Battle_Select_Action implements InputProcessor {

	private Battle_State state;
	
	public IP_Battle_Select_Action(Battle_State state) {
		this.state = state;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == KeyManager.KEY_ESC) {
			state.deselect();
			// Pop ourselves off the stack
			InputManager.removeInputProcessor();
		} else if (keycode == KeyManager.KEY_SPACE) {
			select();
		}
		return false;
	}
	
	private void select() {
		// An action was selected
		Entity selectedEntity = state.getSelectedEntity();
		Action selectedAction;
		// If we are front, we want the regular actions
		if (state.isSelectedFront()) {
			selectedAction = selectedEntity.getActions()[state.getActionCursorPos()];
		} else {
			// Otherwise, we just want the swap action
			selectedAction = selectedEntity.getSwapActions()[state.getActionCursorPos()];
		}
		// If the action needs to cancel out for any reason, do that here
		if (!selectedAction.select(state)) {
			return;
		}
		
		state.handlePlayerAction(selectedAction, selectedEntity);
		// Throw back to the input processor
		state.deselect();
		InputManager.removeInputProcessor();
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
