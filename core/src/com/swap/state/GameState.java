package com.swap.state;

import com.swap.entity.Entity;

public class GameState {

	private Entity [][] teams;
	
	public GameState() {
		this.teams = new Entity[2][4];
	}
	
	public Entity [] getPlayerTeam() {
		return teams[0];
	}
	
	public Entity [] getEnemyTeam() {
		return teams[1];
	}
	
	public Entity [] getMyTeam(int team) {
		return teams[team];
	}
	
	public Entity [] getOtherTeam(int team) {
		return teams[(team + 1) % 2];
	}
	
}
