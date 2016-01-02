package com.swap.state;

import com.swap.action.Action;
import com.swap.entity.Entity;

public class Battle_State {

	// Current actions
	private Action playerAction;
	private Action cpuAction;
	
	// Current action stats
	private int actionCursorPos;

	// Currently selected entity
	private Entity selectedEntity;
	private boolean isSelectedFront;

	// Position of the cursor
	private int cursorPos;

	// All of the entities in the battle
	private Entity [][] teams;

	public Battle_State() {
		this.teams = new Entity[2][4];
		cursorPos = 0;
		actionCursorPos = 0;
		isSelectedFront = false;
	}

	public Entity [] getPlayerTeam() {
		return teams[0];
	}

	public Entity [] getCPUTeam() {
		return teams[1];
	}

	public Entity [] getMyTeam(int team) {
		return teams[team];
	}

	public Entity [] getOtherTeam(int team) {
		return teams[(team + 1) % 2];
	}

	public int getTeamSize(int team) {
		// Count until we find null
		// That's how big the team size is
		for (int i = 0; i < 4; i++) {
			if (teams[team][i] == null) {
				return i;
			}
		}

		return 4;
	}

	public void selectEntity(int entityToSelect) {
		selectedEntity = getPlayerTeam()[entityToSelect];
		isSelectedFront = (entityToSelect == 0);
	}

	public void deselect() {
		// Reset everything!
		selectedEntity = null;
		isSelectedFront = false;
		cursorPos = 0;
	}

	public Action [] getActions() {
		if (isSelectedFront) {
			return selectedEntity.getActions();
		} else {
			return selectedEntity.getSwapActions();
		}
	}

	public void setCursorPosition(int position) {
		this.cursorPos = position;
	}

	public int getRightCursorBoundary() {
		return getTeamSize(1);
	}

	public void incrementCursor() {
		cursorPos++;
	}

	public int getLeftCursorBoundary() {
		return (getTeamSize(0) - 1) * -1;
	}

	public void decrementCursor() {
		cursorPos--;
	}

	public int getCursorPos() {
		return cursorPos;
	}

	public Entity getSelectedEntity() {
		return this.selectedEntity;
	}

	public boolean isSelectedFront() {
		return isSelectedFront;
	}

	public int getActionCursorPos() {
		return actionCursorPos;
	}

	public void incrementActionCursorPos() {
		actionCursorPos++;
	}

	public void decrementActionCursorPos() {
		actionCursorPos--;
	}
	
	public void setPlayerAction(Action action) {
		this.playerAction = action;
		chooseCPUAction();
		executeActions();
	}

	private void executeActions() {
		// TODO Auto-generated method stub
		
	}

	private void chooseCPUAction() {
		// TODO Auto-generated method stub
		
	}

	public int getEntityIndex(Entity myEntity) {
		// Get my team
		Entity [] entities = getMyTeam(myEntity.getTeam());
		
		// Loop through my entities and find me!
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] == myEntity) {
				return i;
			}
		}
		return -1;
	}

	public void swapWithFront(Entity myEntity) {
		// Get my index
		int myIndex = this.getEntityIndex(myEntity);
		
		// Store the temp entity
		Entity [] entities = getMyTeam(myEntity.getTeam());
		Entity temp = entities[myIndex];
		
		// Perform the swap
		entities[myIndex] = entities[0];
		entities[0] = temp;
		entities[myIndex].setPosition(myIndex);
		entities[0].setPosition(0);
	}

	public void handlePlayerAction(Action selectedAction, Entity selectedEntity2) {
		// The player has selected an action
		// We need to select a computer action in return
		// Only the first cpu unit can act, so ask it for its action
		Entity cpuEntity = getCPUTeam()[0];
		Action cpuAction = cpuEntity.getCPUAction();
		
		// Calculate speeds...
		int playerSpeed = selectedAction.getSpeed();
		int cpuSpeed = cpuAction.getSpeed();
		
		// Compare the speeds of both actions to see who goes first
		if (playerSpeed < cpuSpeed) {
			selectedAction.act(this, selectedEntity);
			cpuAction.act(this, cpuEntity);
		} else {
			cpuAction.act(this, cpuEntity);
			selectedAction.act(this, selectedEntity);
		}
		
		cleanUp();
	}

	private void cleanUp() {
		// Any dead units need to be cleaned up
		// For now, we are just going to remove them!
		cleanDeadEntities(getPlayerTeam());
		cleanDeadEntities(getCPUTeam());
	}
	
	private void cleanDeadEntities(Entity [] entities) {
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] != null && entities[i].isDead()) {
				int index = i + 1;
				while (index < entities.length) {
					entities[index - 1] = entities[index];
					index++;
				}
			}
		}
	}

}
