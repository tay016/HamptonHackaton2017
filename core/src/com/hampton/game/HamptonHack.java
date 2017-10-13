package com.hampton.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.hampton.game.demo.DemoGame;
import com.hampton.game.demo.DemoMenu;
import com.hampton.game.utils.ActorUtils;

public class HamptonHack extends ApplicationAdapter {
	ActorUtils utils;
	ScreenViewport viewport;
	StateManager stateManager;

	@Override
	public void create () {
		viewport = new ScreenViewport();
		utils = new ActorUtils();
		stateManager = new StateManager(viewport, utils);
		addGameScreensHere();
	}

	public void addGameScreensHere() {
		stateManager.setGameScreen("Menu", new DemoMenu("Game"));
		stateManager.setGameScreen("Game", new DemoGame());
		stateManager.goToScreen("Menu");
	}

	@Override
	public void resize (int width, int height) {
		Stage stage = stateManager.getCurrentGameScreen().getStage();
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Stage stage = stateManager.getCurrentGameScreen().getStage();
		stage.act(1.0f/60.0f);
		stage.draw();
	}
	
	@Override
	public void dispose () {
		stateManager.dispose();
	}
}
