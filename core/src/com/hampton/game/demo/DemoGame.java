package com.hampton.game.demo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.hampton.game.GameScreen;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by turnerd on 10/13/17.
 */
public class DemoGame extends GameScreen {

    private Random randomNumberGenerator = new Random();
    private Actor bucket;
    private Label score;
    private Label.LabelStyle scoreStyle;
    private int dropSpeed = 3;
    private int pauseTime = 1;
    private int newDropInterval = 60;

    @Override
    public void createActors() {
        backgroundColor = new Color(1, 1, 1, 1);
        bucket = utils.createActorFromImage("bucket.png");
        bucket.setPosition(70, 70);
        scoreStyle = new Label.LabelStyle(new BitmapFont(), new Color(0,0,0,1));
        score = new Label("0", scoreStyle);
        stage.addActor(bucket);
        stage.addActor(score);
    }

    @Override
    public void setInputForActors() {
    }

    @Override
    public void setActionsForActors() {
        bucket.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                bucket.rotateBy(1);
                return false;
            }
        });
    }

    @Override
    protected void calledEveryFrame() {
        if(numFrames % newDropInterval == 0) {
            Actor drop = utils.createActorFromImage("droplet.png");
            drop.setPosition(
                    randomNumberGenerator.nextInt(stage.getViewport().getScreenWidth()-64),
                    stage.getViewport().getScreenHeight());
            drop.setName("drop");
            stage.addActor(drop);
        }
        if(numFrames % pauseTime == 0) {
            // move the raindrops, remove any that are beneath the bottom edge of
            // the screen or that hit the bucket. In the later case we play back
            // a sound effect as well.
            for(Actor raindrop : stage.getActors()) {
                if (raindrop.getName() != null && raindrop.getName().equals("drop")) {
                    raindrop.setPosition(raindrop.getX(), raindrop.getY() - dropSpeed);
                }
                if(raindrop.getY() + 64 < 0) {
                    raindrop.remove();
                }
                //if(raindrop.overlaps(bucket)) {
                //    dropSound.play();
                //    iter.remove();
                //}
            }
            score.setText(Integer.toString(stage.getActors().size));

        }
    }
}
