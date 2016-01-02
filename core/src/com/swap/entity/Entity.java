package com.swap.entity;

import java.awt.Point;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.swap.action.Action;

public abstract class Entity {

	// State related info
	protected int position;
	protected int team;
	
	// Unit stats
	protected int maxHealth;
	protected int curHealth;
	
	protected String name;
	
	protected Action [] actions;
	protected Action [] swapActions;
	
	public abstract Point render(float delta, SpriteBatch batch, Point spritePos);
	
	public int damage(int damage) {
		// Damage should NEVER be negative
		if (damage < 0) {
			damage = 0;
		}
		
		// For now, damage directly reduces our health
		curHealth -= damage;
		if (curHealth < 0) {
			curHealth = 0;
		}
		
		// Return the health left
		// This may be interesting to the action for some reason (shrug)
		return curHealth;
	}
	
	public Action getCPUAction() {
		return actions[0];
	}
	
	public Action [] getActions() {
		return actions;
	}
	
	public Action [] getSwapActions() {
		return swapActions;
	}
	
	public int getTeam() {
		return team;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isDead() {
		return this.curHealth <= 0;
	}
	
}
