package com.hampton.game.demo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.hampton.game.GameScreen;

/**
 * Created by turnerd on 10/13/17.
 */

public class DemoMenu extends GameScreen {
    Actor buttonFromText;
    private String nextScreenName;

    public DemoMenu(String nextScreenName) {
        this.nextScreenName = nextScreenName;
    }

    @Override
    public void createActors() {
        buttonFromText = utils.createButtonFromText("Click to go to" + nextScreenName,
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
