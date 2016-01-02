package com.swap.screen;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.swap.action.Action;
import com.swap.entity.Entity;
import com.swap.entity.Entity_Knight;
import com.swap.entity.Entity_Skeleton;
import com.swap.input.IP_Battle_Select_Unit;
import com.swap.state.Battle_State;
import com.swap.utility.InputManager;

public class Screen_Battle implements Screen {

	private Battle_State state;
	private SpriteBatch batch;

	private static Sprite cursor = new Sprite(new Texture(Gdx.files.internal("textures/ui/cursor.png")));
	private static Sprite background = new Sprite(new Texture(Gdx.files.internal("textures/backgrounds/plains.png")));

	// Cursor info
	private boolean shouldDrawCursor;

	private OrthographicCamera camera;

	public Screen_Battle() {
		this.state = new Battle_State();
		this.batch = new SpriteBatch();

		// Set up the camera
		this.camera = new OrthographicCamera(1600, 1000);
		camera.translate(new Vector2(800, 500));
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		// Set up the input manager
		InputManager.addInputProcessor(new IP_Battle_Select_Unit(state));

		// Set up the cursor
		shouldDrawCursor = true;

		// Set up some initial teams
		state.getPlayerTeam()[0] = new Entity_Knight(0, 0);
		state.getPlayerTeam()[1] = new Entity_Skeleton(0, 1);
		state.getPlayerTeam()[2] = new Entity_Knight(0, 2);
		//		state.getPlayerTeam()[3] = new Knight(0, 3);
		state.getCPUTeam()[0] = new Entity_Skeleton(1, 0);
		state.getCPUTeam()[1] = new Entity_Skeleton(1, 1);
		state.getCPUTeam()[2] = new Entity_Skeleton(1, 2);
		//		state.getEnemyTeam()[3] = new Skeleton(1, 3);
	}

	public void render(float delta) {
		// Start the batch
		batch.begin();

		// Draw the background
		background.draw(batch);

		// Draw both teams
		drawTeams(delta);

		// Draw the actions
		drawActions();

		// End the batch
		batch.end();
	}

	private void drawTeams(float delta) {
		// Draw the player team
		Entity [] team = state.getPlayerTeam();

		for (int i = 0; i < 4; i++) {
			// Draw each entity that exists
			if (team[i] != null) {
				Point spritePos = getEntityPosition(i, 0);
				team[i].render(delta, batch, spritePos);

				drawCursor(i * -1, spritePos);
			}
		}

		// Draw the enemy team
		team = state.getCPUTeam();

		for (int i = 0; i < 4; i++) {
			if (team[i] != null) {
				Point spritePos = getEntityPosition(i, 1);
				team[i].render(delta, batch, spritePos);

				// If the cursor is on this entity, draw it
				drawCursor(i + 1, spritePos);
			}
		}
	}

	private void drawActions() {
		// We only want to draw if we have a unit selected
		if (state.getSelectedEntity() == null) {
			return;
		}

		Action [] actions = state.isSelectedFront() ? state.getSelectedEntity().getActions() : state.getSelectedEntity().getSwapActions();
		for (int i = 0; i < actions.length; i++) {
			actions[i].render(batch, 500 + 25 * i, 800);

			// Do we draw the cursor here?
			if (state.getActionCursorPos() == i) {
				cursor.setPosition(500 + 25 * i, 900);
				cursor.draw(batch);
			}
		}
	}

	private void drawCursor(int position, Point spritePos) {
		// If we don't want to draw the cursor, return
		if (state.getSelectedEntity() != null || position != state.getCursorPos()) {
			return;
		}

		cursor.setPosition(spritePos.x, 400);
		cursor.draw(batch);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	private Point getEntityPosition(int position, int team) {
		// Entities are offset from the center of the screen
		int xOffset = position * (200);
		int xPos;
		if (team == 0) {
			xPos = 800 - 100 - xOffset;
		} else {
			xPos = 800 + 100 + xOffset;
		}

		return new Point(xPos, 100);
	}

}
