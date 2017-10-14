package com.hampton.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.hampton.game.demo.BubbleDrop;
import com.hampton.game.demo.LevelPick;
import com.hampton.game.demo.MainMenu;

public class HamptonHack extends ApplicationAdapter {
	ScreenViewport viewport;
	StateManager stateManager;

	@Override
	public void create () {
		viewport = new ScreenViewport();
		stateManager = new StateManager(viewport);
		addGameScreensHere();
	}

	public void addGameScreensHere() {
		stateManager.setGameScreen("Menu", new MainMenu("Level"));
		stateManager.setGameScreen("Game", new BubbleDrop("backGround.png", "gradeCap.png", "kanyeBear2.png", "homeComing.mp3"));
		stateManager.setGameScreen("Humble", new BubbleDrop("bricks.jpeg","DAMN.png","k-dot.png" ,"HUMBLE.mp3"));
		stateManager.setGameScreen("awakenLove", new BubbleDrop("starySky.jpg","stars.png", "childish.png","Redbone.mp3"));
		stateManager.setGameScreen("Level", new LevelPick("Game"));
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
		Color color = stateManager.getCurrentGameScreen().getBackgroundColor();
		Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
		stateManager.getCurrentGameScreen().calledDuringRender();
		Stage stage = stateManager.getCurrentGameScreen().getStage();
		stage.act(1.0f/60.0f);
		stage.draw();
	}
	
	@Override
	public void dispose () {
		stateManager.dispose();
	}
}
