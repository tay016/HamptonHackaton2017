package com.hampton.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hampton.game.utils.ActorUtils;

public abstract class GameScreen {
    protected Stage stage;
    protected ActorUtils utils;
    private StateManager stateManager;

    public void setData(Stage stage, ActorUtils utils, StateManager stateManager) {
        this.stage = stage;
        this.utils = utils;
        this.stateManager = stateManager;
    }

    public abstract void createActors();

    public abstract void setInputForActors();

    public abstract void setActionsForActors();

    public final void gotoScreen(String screenName) {
        stateManager.goToScreen(screenName);
    }

    public Stage getStage() {
        return stage;
    }
}
