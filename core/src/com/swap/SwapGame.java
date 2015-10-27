package com.swap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.swap.screen.Screen_Battle;

public class SwapGame extends ApplicationAdapter {
	
	private Screen screen;
	
	@Override
	public void create () {
		this.screen = new Screen_Battle();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		screen.render(Gdx.graphics.getDeltaTime());
	}
}
