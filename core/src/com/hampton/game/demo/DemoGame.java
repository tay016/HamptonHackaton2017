package com.hampton.game.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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

    private Sound dropSound;
    private Music rainMusic;

    @Override
    public void createActors() {
        backgroundColor = new Color(0, 0, .2f, 1);
        bucket = utils.createActorFromImage("bucket.png");
        bucket.setPosition(20, 20);
        scoreStyle = new Label.LabelStyle(new BitmapFont(), new Color(0,0,0,1));
        score = new Label("0", scoreStyle);
        stage.addActor(bucket);
        stage.addActor(score);

        // load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        // start the playback of the background music immediately
        rainMusic.setLooping(true);
        rainMusic.play();
    }

    @Override
    public void setInputForActors() {
    }

    @Override
    public void setActionsForActors() {
    }

    @Override
    protected void calledEveryFrame() {
        if (numFrames % newDropInterval == 0) {
            Actor drop = utils.createActorFromImage("droplet.png");
            drop.setPosition(
                    randomNumberGenerator.nextInt(stage.getViewport().getScreenWidth() - 64),
                    stage.getViewport().getScreenHeight());
            drop.setName("drop");
            stage.addActor(drop);
        }
        if (numFrames % pauseTime == 0) {
            // move the raindrops, remove any that are beneath the bottom edge of
            // the screen or that hit the bucket. In the later case we play back
            // a sound effect as well.
            for (Actor raindrop : stage.getActors()) {
                if (raindrop.getName() != null && raindrop.getName().equals("drop")) {
                    raindrop.setPosition(raindrop.getX(), raindrop.getY() - dropSpeed);
                }
                if (raindrop.getY() + 64 < 0) {
                    raindrop.remove();
                }
                //if(raindrop.overlaps(bucket)) {
                //    dropSound.play();
                //    iter.remove();
                //}
            }
            score.setText(Integer.toString(stage.getActors().size));
        }
        // process user input
        if (Gdx.input.isTouched()) {
            bucket.setX(Gdx.input.getX() - 64 / 2);
        }
    }
}
