package com.hampton.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by turnerd on 10/13/17.
 */

public class StateManager {
    private Map<String, GameScreen> gameScreens = new HashMap<String, GameScreen>();
    private GameScreen currentScreen;
    private ScreenViewport viewport;

    public StateManager(ScreenViewport viewport) {
        this.viewport = viewport;
    }

    GameScreen getCurrentGameScreen() {
        return currentScreen;
    }

    /**
     * @param screenName MUST BE UNIQUE
     * @param screen
     */
    public void setGameScreen(String screenName, GameScreen screen) {
        gameScreens.put(screenName, screen);
        Stage stage = new Stage(viewport);
        screen.setData(stage, this);
        screen.createActors();
        screen.setInputForActors();
        screen.setActionsForActors();
    }

    public void goToScreen(String screenName) {
        if (! gameScreens.containsKey(screenName)) {
            throw new NullPointerException("Screen name does not exist please add a screen with the name: " + screenName);
        }
        currentScreen = gameScreens.get(screenName);
        Gdx.input.setInputProcessor(currentScreen.getStage());
        currentScreen.initialize();
    }

    public void dispose() {
        for(GameScreen gameScreen: gameScreens.values()) {
            gameScreen.getStage().dispose();
        }
    }
}
