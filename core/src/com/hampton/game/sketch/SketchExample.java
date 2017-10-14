package com.hampton.game.sketch;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
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
        backgroundColor = new Color(1, 1, 1, 1);
        pointList = new ArrayList<Point>();
        sketchActor.setPosition(0, 0);
        sketchActor.setSize(500, 500);
    }

    @Override
    public void createActors() {
        sketchActor = new SketchActor();
        stage.addActor(sketchActor);
    }

    @Override
    public void setInputForActors() {
        sketchActor.addListener(new EventListener() {
            @Override
            public boolean handle(Event e) {
                if (!(e instanceof InputEvent)) return false;
                InputEvent event = (InputEvent)e;
                switch (event.getType()) {
                    case touchDown:
                    case touchDragged:
                        createPoint(event.getStageX(), event.getStageY());
                        return true;
                }
                return false;
            }

            public void  createPoint(float x, float y) {
                Point point = new Point();
                point.x = x;
                point.y = y;
                pointList.add(point);
                sketchActor.addPoint(point);
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
