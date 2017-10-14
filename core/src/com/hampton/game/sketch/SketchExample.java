package com.hampton.game.sketch;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.hampton.game.GameScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by turnerd on 10/14/17.
 */

public class SketchExample extends GameScreen {

    private SketchActor sketchActor;
    List<Point> pointList;

    @Override
    public void initialize() {
        pointList = new ArrayList<Point>();
        sketchActor.setPoints(pointList);
    }

    @Override
    public void createActors() {
        sketchActor = new SketchActor();
    }

    @Override
    public void setInputForActors() {
        sketchActor.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Point point = new Point();
                point.x = x;
                point.y = y;
                pointList.add(point);
            }
        });
    }

    @Override
    public void setActionsForActors() {

    }

    @Override
    protected void calledEveryFrame() {

    }
}
