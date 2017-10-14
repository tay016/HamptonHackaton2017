package com.hampton.game.demo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.hampton.game.GameScreen;
import com.hampton.game.utils.ActorUtils;

import java.util.Random;

/**
 * Created by turnerd on 10/13/17.
 */
public class SnakeGame extends GameScreen {

    private static final int UP = 0;
    private static final int LEFT = 1;
    private static final int DOWN = 2;
    private static final int RIGHT = 3;
    private static final int AMOUNT_TO_MOVE = 12;
    private static final float MIN_LOCATION = 100;
    private static final float MAX_LOCATION = 400;
    private static final long TIMER = 10;

    private Actor upArrow;
    private Actor leftArrow;
    private Actor downArrow;
    private Actor rightArrow;

    private Actor snakeHead;
    private Actor[] snakeBody = new Actor[0];
    private int snakeDirection = 0;

    private Actor buttonFromText;
    private Actor apple;
    private int score;

    @Override
    public void initialize() {
        reset();
        for (Actor actor: snakeBody) {
            actor.remove();
        }
        snakeBody = new Actor[0];
        if (apple != null) {
            apple.remove();
        }
        createApple();
        stage.addActor(apple);
    }

    public void createApple() {
        apple = ActorUtils.createActorFromImage("apple.png");
        apple.setName("Apple");
        apple.setPosition(ActorUtils.getRandomFloat(MIN_LOCATION, MAX_LOCATION),
                ActorUtils.getRandomFloat(MIN_LOCATION, MAX_LOCATION));
    }

    @Override
    public void createActors() {
        backgroundColor = new Color(1, 1, 1, 1);
        snakeHead = ActorUtils.createActorFromImage("snake_head.png");
        snakeHead.setName("Head");
        buttonFromText = ActorUtils.createButtonFromText("Back to menu", new Color(1, 1, 1, 1));
        buttonFromText.setName("Back");
        stage.addActor(snakeHead);
        stage.addActor(buttonFromText);

        Actor rectangleActor = ActorUtils.createRectangleActor(false);
        rectangleActor.setName("Outline");
        rectangleActor.setPosition(MIN_LOCATION, MIN_LOCATION);
        rectangleActor.setSize(MAX_LOCATION - MIN_LOCATION + 30, MAX_LOCATION - MIN_LOCATION + 30);
        rectangleActor.setColor(0, 0, 0, 1);
        stage.addActor(rectangleActor);

        putArrowsInPlace();

    }

    public void putArrowsInPlace() {

        upArrow = ActorUtils.createActorFromImage("arrow_upward_black_24x24.png");
        leftArrow = ActorUtils.createActorFromImage("chevron_left_black_24x24.png");
        downArrow = ActorUtils.createActorFromImage("arrow_downward_black_24x24.png");
        rightArrow = ActorUtils.createActorFromImage("chevron_right_black_24x24.png");

        upArrow.setPosition(70, 70);
        leftArrow.setPosition(50, 50);
        rightArrow.setPosition(90, 50);
        downArrow.setPosition(70, 50);

        stage.addActor(upArrow);
        stage.addActor(leftArrow);
        stage.addActor(downArrow);
        stage.addActor(rightArrow);
    }

    @Override
    public void setInputForActors() {
        buttonFromText.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gotoScreen("Menu");
            }
        });

        setArrowListener(upArrow, UP);
        setArrowListener(leftArrow, LEFT);
        setArrowListener(rightArrow, RIGHT);
        setArrowListener(downArrow, DOWN);
    }

    public void setArrowListener(Actor button, final int newSnakeDirection) {
        button.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                changeSnakeDirection(newSnakeDirection);
            }
        });
    }

    private void changeSnakeDirection(int newSnakeDirection) {
        if (snakeDirection == newSnakeDirection) {
            return;
        }
        // This is checking if both directions are odd or if both are even
        if (snakeDirection % 2 == newSnakeDirection % 2) {
            snakeHead.rotateBy(180);
        } else if ((snakeDirection == UP && newSnakeDirection == LEFT)
                || (snakeDirection == LEFT && newSnakeDirection == DOWN)
                || (snakeDirection == DOWN && newSnakeDirection == RIGHT)
                || (snakeDirection == RIGHT && newSnakeDirection == UP)) {
            snakeHead.rotateBy(90);
        } else {
            snakeHead.rotateBy(-90);
        }
        snakeDirection = newSnakeDirection;
    }

    @Override
    public void setActionsForActors() {
        snakeHead.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                if (numFrames % TIMER == 0) {
                    if (snakeDirection == UP) {
                        snakeHead.moveBy(0, AMOUNT_TO_MOVE);
                    }
                    if (snakeDirection == LEFT) {
                        snakeHead.moveBy(-AMOUNT_TO_MOVE, 0);
                    }
                    if (snakeDirection == DOWN) {
                        snakeHead.moveBy(0, -AMOUNT_TO_MOVE);
                    }
                    if (snakeDirection == RIGHT) {
                        snakeHead.moveBy(AMOUNT_TO_MOVE, 0);
                    }
                }
                return false;
            }
        });
    }

    private void reset() {
        snakeHead.setPosition(MIN_LOCATION, MIN_LOCATION);
        snakeDirection = UP;
        snakeHead.setRotation(0);
    }

    @Override
    protected void calledEveryFrame() {
        if (snakeHead.getX() < MIN_LOCATION || snakeHead.getY() < MIN_LOCATION
                || snakeHead.getX() > MAX_LOCATION || snakeHead.getY() > MAX_LOCATION
                || (numFrames % TIMER == 0 && checkBodyCollision())) {
            reset();
            gotoScreen("Menu");
        }
        if (ActorUtils.actorsCollided(snakeHead, apple)) {
            moveAppleAndAddBody();
        }
        if (numFrames % TIMER == 0) {
            updateBodyPositions();
        }
    }

    private boolean checkBodyCollision() {
        for (int i = 4; i < snakeBody.length; i++) {
            if (ActorUtils.actorsCollided(snakeHead, snakeBody[i])) {
                return true;
            }
        }
        return false;
    }

    private void updateBodyPositions() {
        if (snakeBody.length == 0) {
            return;
        }
        for (int i = snakeBody.length - 1; i > 0; i--) {
            Actor newPosition = snakeBody[i -1];
            Actor beingChanged = snakeBody[i];
            beingChanged.setPosition(newPosition.getX(), newPosition.getY());
            beingChanged.setRotation(newPosition.getRotation());
        }
        Actor beingChanged = snakeBody[0];
        beingChanged.remove();
        beingChanged.setPosition(snakeHead.getX(), snakeHead.getY());
        beingChanged.setRotation(snakeHead.getRotation());
    }

    private void moveAppleAndAddBody() {
        score++;
        apple.setPosition(ActorUtils.getRandomFloat(MIN_LOCATION, MAX_LOCATION),
                ActorUtils.getRandomFloat(MIN_LOCATION, MAX_LOCATION));
        Actor[] old = snakeBody;
        snakeBody = new Actor[snakeBody.length + 1];
        // This puts all the elements in old into snakebody
        System.arraycopy(old, 0, snakeBody, 0, old.length);
        Actor newBody = ActorUtils.createActorFromImage("snake_body.png");
        newBody.setName("Body");
        stage.addActor(newBody);
        snakeBody[snakeBody.length - 1] = newBody;
    }
}
