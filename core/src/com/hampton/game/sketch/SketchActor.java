package com.hampton.game.sketch;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.List;

/**
 * Created by turnerd on 10/14/17.
 */

public class SketchActor extends Actor {
    private float[] points = new float[0];
    final ShapeRenderer shapeRenderer = new ShapeRenderer();

    public void addPoint(Point point) {
        float[] oldPoints = points;
        points = new float[points.length + 2];
        System.arraycopy(oldPoints, 0, points, 0, oldPoints.length);
        points[points.length - 2] = point.x;
        points[points.length -1] = point.y;
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.end();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 0, 0, 0);
        shapeRenderer.rect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        if (points.length >= 4) {
            shapeRenderer.polyline(points);
        }
        shapeRenderer.end();
        batch.begin();

    }
}
