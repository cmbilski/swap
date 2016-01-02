package com.swap.input;

import com.badlogic.gdx.InputProcessor;
import com.swap.state.Battle_State;
import com.swap.utility.InputManager;
import com.swap.utility.KeyManager;

public class IP_Battle_Select_Unit implements InputProcessor {

	private Battle_State state;
	private IP_Battle_Select_Action actionInput;

	public IP_Battle_Select_Unit(Battle_State state) {
		this.state = state;
		this.actionInput = new IP_Battle_Select_Action(state);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		System.out.println(keycode);
		if (keycode == KeyManager.KEY_RIGHT_ARROW) {
			// Can we move our cursor any further right?
			if (state.getCursorPos() < state.getRightCursorBoundary()) {
				state.incrementCursor();
			}
			return true;
		} else if (keycode == KeyManager.KEY_LEFT_ARROW) {
			// Can we move our cursor any further left?
			// Since we count 0 as the first unit of the player team, we need to shift them by one
			if (state.getCursorPos() > state.getLeftCursorBoundary()) {
				state.decrementCursor();
			}
			return true;
		} else if (keycode == KeyManager.KEY_SPACE) {
			select();
			return true;
		}
		return false;
	}

	private void select() {
		// If we can select, do it
		// That is, we are on one of our units
		if (state.getCursorPos() >= state.getLeftCursorBoundary() && 
				state.getCursorPos() <= 0) {
			state.selectEntity(state.getCursorPos() * -1);
			
			// Swap in the new input manager
			InputManager.addInputProcessor(actionInput);
		}
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
