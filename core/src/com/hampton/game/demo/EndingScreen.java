package com.hampton.game.demo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.hampton.game.GameScreen;
import com.hampton.game.utils.ActorUtils;

/**
 * Created by turnerd on 10/13/17.
 */

public class EndingScreen extends GameScreen {
    Actor buttonFromText;
    Actor youLose;
    private String nextScreenName;

    public EndingScreen(String nextScreenName) {
        this.nextScreenName = nextScreenName;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void createActors() {
        youLose = ActorUtils.createButtonFromText("YOU LOSE", new Color(0, 1, 0, 1));
        youLose.setPosition(300, 300);
        stage.addActor(youLose);
        buttonFromText = ActorUtils.createButtonFromText("Click to go to " + nextScreenName,
                new Color(1, 1, 1, 1));
        stage.addActor(buttonFromText);
    }

    @Override
    public void setInputForActors() {

    }

    @Override
    public void setActionsForActors() {
        buttonFromText.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gotoScreen(nextScreenName);
            }
        });
    }

    @Override
    protected void calledEveryFrame() {

    }
}
