package com.hampton.game.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.hampton.game.GameScreen;
import com.hampton.game.utils.ActorUtils;

import java.util.Random;

/**
 * Created by turnerd on 10/13/17.
 */
public class BubbleDrop extends GameScreen {

    private Random randomNumberGenerator = new Random();
    private Actor backGround;
    private Actor kanyeBear;
    private Label scoreLabel;
    private Label.LabelStyle scoreStyle;
    private int score = 0;
    private int dropSpeed = 2;
    private int pauseTime = 1;
    private int newDropInterval = 60;
    private boolean gameOn = false;

    private Music kanyeMusic;

    private String backGroundFile;
    private String droppers;
    private String catcher;
    private String musicFile;


    public BubbleDrop(String backGroundFile, String droppers, String catcher, String musicFile){

        this.backGroundFile = backGroundFile;
        this.droppers = droppers;
        this.catcher = catcher;
        this.musicFile = musicFile;
    }

    @Override
    public void initialize() {

        backGround = ActorUtils.createActorFromImage(backGroundFile);
        stage.addActor(backGround);
        backGround.toBack();

        // load the drop sound effect and the rain background "music"

        kanyeMusic = Gdx.audio.newMusic(Gdx.files.internal(musicFile));

        // start the playback of the background music immediately
        kanyeMusic.setLooping(true);
        kanyeMusic.play();
        gameOn = true;
        score = 0;
        dropSpeed = 1;
        newDropInterval = 60;
        numFrames = 0;
        // Clear any raindrops from previous games
        for (Actor raindrop : stage.getActors()) {
            if (raindrop.getName() != null && raindrop.getName().equals("drop")) {
                raindrop.remove();
            }
        }
        scoreStyle = new Label.LabelStyle(new BitmapFont(), new Color(1,1,1,1));
        scoreStyle.font.getData().setScale(4);
        scoreLabel = new Label("0", scoreStyle);
        scoreLabel.setPosition(0, stage.getViewport().getScreenHeight() - scoreLabel.getHeight());
        stage.addActor(scoreLabel);
    }

    @Override
    public void createActors() {
        backgroundColor = new Color(0, 0, .2f, 1);
        kanyeBear = ActorUtils.createActorFromImage(catcher);
        kanyeBear.toFront();
        kanyeBear.setPosition(20, 20);
        stage.addActor(kanyeBear);
    }

    @Override
    public void setInputForActors() {
    }

    @Override
    public void setActionsForActors() {
    }

    @Override
    protected void calledEveryFrame() {
        // process user input
        if (Gdx.input.isTouched()) {
            kanyeBear.setX(Gdx.input.getX() - 64 / 2);
        }
        if (gameOn && numFrames % newDropInterval == 0) {
            Actor gradCap = ActorUtils.createActorFromImage(droppers);
            gradCap.setPosition(
                    randomNumberGenerator.nextInt(stage.getViewport().getScreenWidth() - (int)gradCap.getWidth()),
                    stage.getViewport().getScreenHeight());
            gradCap.setName("drop");
            stage.addActor(gradCap);
        }
        if (gameOn && numFrames % pauseTime == 0) {
            // move the raindrops, remove any that are beneath the bottom edge of
            // the screen or that hit the bucket. In the later case we play back
            // a sound effect as well.
            for (Actor raindrop : stage.getActors()) {
                if (raindrop.getName() != null && raindrop.getName().equals("drop")) {
                    raindrop.setPosition(raindrop.getX(), raindrop.getY() - dropSpeed*3);

                    if (raindrop.getY() + 64 < 0) {
                        gameOn = false;
                        break;
                    }
                    if (ActorUtils.actorsCollided(raindrop, kanyeBear)) {
                        raindrop.remove();

                        score++;
                        if (score % 10 == 0) {
                            nextLevel();
                        }
                    }
                }
            }
            scoreLabel.setText("Score: " + score + " Level: " + (dropSpeed-1));
            if (!gameOn) {
                loseGame();
            }
        }
    }

    private void nextLevel() {
        dropSpeed++;
        newDropInterval = 180 / dropSpeed;
    }

    private void loseGame() {
        kanyeMusic.stop();
        for (Actor raindrop : stage.getActors()) {
            if (raindrop.getName() != null && raindrop.getName().equals("drop")) {
                raindrop.remove();
            }
        }
        scoreLabel.remove();
        final Actor backButton = ActorUtils.createButtonFromText(
                "Final score: " + score + " Click to go to back to menu",
                new Color(1, 1, 1, 1));
        backButton.setPosition(0, stage.getViewport().getScreenHeight() - backButton.getHeight());
        backButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                backButton.remove();
                gotoScreen("Menu");
            }
        });
        stage.addActor(backButton);
    }
}
