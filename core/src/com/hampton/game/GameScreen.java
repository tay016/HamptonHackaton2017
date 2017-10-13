package com.hampton.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hampton.game.utils.ActorUtils;

public abstract class GameScreen {
    protected Stage stage;
    protected ActorUtils utils;
    private StateManager stateManager;
    private long numFrames;
    protected Color backgroundColor = new Color();
    private long customFrameAmount;

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

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Called before actors act!
     */
    public final void calledDuringRender() {
        calledEveryFrame();
        numFrames++;
        if (numFrames % 60 == 0) {
            calledAboutEverySecond();
        }
        if (numFrames % customFrameAmount == 0) {
            calledCustomAmount();
        }
    }

    protected final void setCustomFrameTime(long timeInMillis) {
        customFrameAmount = timeInMillis * 10 / 60;
    }

    /**
     * Called before actors act!
     */
    public abstract void calledAboutEverySecond();

    /**
     * Called before actors act!
     */
    public abstract void calledEveryFrame();

    /**
     * Called before actors act!
     */
    public abstract void calledCustomAmount();
}
