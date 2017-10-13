package com.hampton.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.HashMap;
import java.util.Map;


public class ActorUtils {
    private Map<String, Texture> images = new HashMap<String, Texture>();

    public Actor createActorFromImage(String image) {
        Texture texture = getTexture(image);
        Actor actor = new Image(new TextureRegionDrawable(new TextureRegion(texture)));
        actor.setOrigin(actor.getWidth()/2, actor.getHeight()/2);
        return actor;
    }

    public Actor createButtonFromText(String text, Color color) {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        style.fontColor = color;
        Label actor = new Label(text, style);
        actor.setOrigin(actor.getWidth()/2, actor.getHeight()/2);
        return actor;
    }

    private Texture getTexture(String imageName) {
        if (images.containsKey(imageName)) {
            return images.get(imageName);
        }
        Texture texture = new Texture(imageName);
        images.put(imageName, texture);
        return texture;
    }
}
