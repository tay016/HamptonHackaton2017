package com.hampton.game.demo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.hampton.game.GameScreen;
import com.hampton.game.utils.ActorUtils;

/**
 * Created by iamtay016 on 10/13/17.
 */

public class LevelPick extends GameScreen {
    Actor Graduation;
    Actor Humble;
    Actor AwakenLove;
    Actor moonMan;
    private String nextScreenName;

    public LevelPick(String nextScreenName) {
        this.nextScreenName = nextScreenName;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void createActors() {
        Graduation = ActorUtils.createButtonFromText(
                "Graduation",
                new Color(1, 1, 1, 1));
        Graduation.setPosition(
                stage.getViewport().getScreenWidth()/2 - Graduation.getWidth()/2,
                stage.getViewport().getScreenHeight()/4 - Graduation.getHeight()/4);
        stage.addActor(Graduation);

        Humble = ActorUtils.createButtonFromText(
                "Humble",
                new Color(1, 1, 1, 1));
        Humble.setPosition(
                stage.getViewport().getScreenWidth()/2 - Humble.getWidth()/2,
                stage.getViewport().getScreenHeight()/2 - Humble.getHeight()/2);
        stage.addActor(Humble);


        AwakenLove = ActorUtils.createButtonFromText(
                "Awaken My Love",
                new Color(1, 1, 1, 1));
        AwakenLove.setPosition(
                stage.getViewport().getScreenWidth()/2 - AwakenLove.getWidth()/2,
                stage.getViewport().getScreenHeight()/3 - AwakenLove.getHeight()/3);
        stage.addActor(AwakenLove);

        moonMan = ActorUtils.createButtonFromText(
                "Man on the Moon: The End of Day",
                new Color(1, 1, 1, 1));
        moonMan.setPosition(
                stage.getViewport().getScreenWidth()/2 - moonMan.getWidth()/2,
                stage.getViewport().getScreenHeight()/1 - moonMan.getHeight()/1);
        stage.addActor(moonMan);
    }

    @Override
    public void setInputForActors() {

    }

    @Override
    public void setActionsForActors() {
        Graduation.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gotoScreen("Game");
            }
        });

        Humble.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gotoScreen("Humble");
            }
        });

        AwakenLove.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gotoScreen("awakenLove");
            }
        });

        moonMan.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gotoScreen("moonMan");
            }
        });
    }

    @Override
    protected void calledEveryFrame() {

    }
}
