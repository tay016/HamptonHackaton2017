package com.hampton.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.Random;

public class ActorUtils {
    private static Random randomNumberGenerator = new Random();

    // We make private constructors for util classes so no one can declare an instance of the class
    private ActorUtils(){}
    public static Actor createActorFromImage(String imageName) {
        return createActorFromImage(new Texture(imageName));
    }

    public static Actor createActorFromImage(Texture texture) {
        Actor actor = new Image(new TextureRegionDrawable(new TextureRegion(texture)));
        actor.setOrigin(actor.getWidth()/2, actor.getHeight()/2);
        actor.setSize(actor.getWidth()*3, actor.getHeight()*3);
        return actor;
    }

    public static Actor createButtonFromText(String text, Color color) {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        style.fontColor = color;
        style.font.getData().setScale(4);
        Label actor = new Label(text, style);
        actor.setOrigin(actor.getWidth()/2, actor.getHeight()/2);
        return actor;
    }

    public static boolean actorsCollided(Actor a, Actor b) {
        Rectangle aBounds = new Rectangle(a.getX(), a.getY(), a.getWidth(), a.getHeight());
        Rectangle bBounds = new Rectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight());
        return aBounds.overlaps(bBounds);
    }

    public static float getRandomFloat(float low, float high) {
        return randomNumberGenerator.nextFloat() * (high - low) + low;
    }

    /**
     * Creates a rectangle.
     *
     * @param filled If the rectangle should be filled in or not.
     */
    public static Actor createRectangleActor(final boolean filled) {
        final ShapeRenderer shapeRenderer = new ShapeRenderer();
        return new Actor() {
            @Override
            public void draw (Batch batch, float parentAlpha) {
                batch.end();
                shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
                if (filled) {
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                } else {
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                }
                shapeRenderer.setColor(this.getColor());
                shapeRenderer.rect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
                shapeRenderer.end();
                batch.begin();

            }
        };
    }

    /**
     * Creates a circle using the width as the diameter.
     *
     * @param filled If the circle should be filled in or not.
     */
    public static Actor createCircleActor(final boolean filled) {
        final ShapeRenderer shapeRenderer = new ShapeRenderer();
        return new Actor() {
            @Override
            public void draw (Batch batch, float parentAlpha) {
                batch.end();
                shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
                if (filled) {
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                } else {
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                }
                shapeRenderer.setColor(this.getColor());
                shapeRenderer.circle(this.getX(), this.getY(), this.getWidth() / 2.0f);
                shapeRenderer.end();
                batch.begin();

            }
        };
    }
}
