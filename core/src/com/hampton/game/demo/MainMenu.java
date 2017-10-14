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

public class MainMenu extends GameScreen {
    private Actor backGround;
    Actor buttonFromText;
    private String nextScreenName;

    public MainMenu(String nextScreenName) {
        this.nextScreenName = nextScreenName;
    }

    @Override
    public void initialize() {

        backGround = ActorUtils.createActorFromImage("MEME.jpeg");
        stage.addActor(backGround);
        backGround.toBack();
    }

    @Override
    public void createActors() {
        buttonFromText = ActorUtils.createButtonFromText(
                "Start new game",
                new Color(1, 1, 1, 1));
        buttonFromText.setPosition(
                stage.getViewport().getScreenWidth()/2 - buttonFromText.getWidth()/2,
                stage.getViewport().getScreenHeight()/4 - buttonFromText.getHeight()/4);
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
