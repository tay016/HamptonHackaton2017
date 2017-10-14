package com.hampton.game.sketch;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.List;

/**
 * Created by turnerd on 10/14/17.
 */

public class SketchActor extends Actor {
    private List<Point> points;
    final ShapeRenderer shapeRenderer = new ShapeRenderer();

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.end();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 0, 0, 0);
        shapeRenderer.rect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        shapeRenderer.end();
        batch.begin();

    }
}
