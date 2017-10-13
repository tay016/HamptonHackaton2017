package com.hampton.game.demo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.hampton.game.GameScreen;

/**
 * Created by turnerd on 10/13/17.
 */
public class DemoGame extends GameScreen {

    private Actor snakeHead;
    private Actor buttonFromText;
    private int snakeDirection = 0;
    private static final int UP = 0;
    private static final int LEFT = 1;
    private static final int DOWN = 2;
    private static final int RIGHT = 3;
    private static final int AMOUNT_TO_MOVE = 2;
    private Actor upArrow;
    private Actor leftArrow;
    private Actor downArrow;
    private Actor rightArrow;

    @Override
    public void createActors() {
        backgroundColor = new Color(1, 1, 1, 1);
        snakeHead = utils.createActorFromImage("snake_head.png");
        snakeHead.setPosition(70, 70);
        buttonFromText = utils.createButtonFromText("Go back to menu", new Color(1, 1, 1, 1));

        stage.addActor(snakeHead);
        stage.addActor(buttonFromText);

        putArrowsInPlace();
    }

    public void putArrowsInPlace() {

        upArrow = utils.createActorFromImage("arrow_upward_black_24x24.png");
        leftArrow = utils.createActorFromImage("chevron_left_black_24x24.png");
        downArrow = utils.createActorFromImage("arrow_downward_black_24x24.png");
        rightArrow = utils.createActorFromImage("chevron_right_black_24x24.png");

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
                return false;
            }
        });
    }
}
