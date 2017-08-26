package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Asteroid {
    private Vector2 position;
    private float speed;
    private float angle;
    private static Texture texture;
    private Rectangle rectangle;
    private int hp;

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Asteroid() {
        position = new Vector2(1280 + (float) Math.random() * 1280, (float) Math.random() * 720);
        speed = 5.0f + (float) Math.random() * 7.0f;
        angle = (float) Math.random() * 360;
        if (texture == null)
            texture = new Texture("asteroid60.tga");
        rectangle = new Rectangle(position.x, position.y, 60, 60);
        hp = 3 + (int) (Math.random() * 5);
    }

    public void recreate() {
        position.x = 1280 + (float) Math.random() * 1280;
        position.y = (float) Math.random() * 720;
        speed = 5.0f + (float) Math.random() * 7.0f;
        angle = (float) Math.random() * 360;
        hp = 3 + (int) (Math.random() * 5);
    }

    public void getDamage(int dmg) {
        hp -= dmg;
        if (hp <= 0) recreate();
    }

    public void render(SpriteBatch batch) {
        float scl = hp / 5.0f;
        batch.draw(texture, position.x, position.y, 30, 30, 60, 60, scl, scl, angle, 0, 0, 60, 60, false, false);
    }

    public void update() {
        position.x -= speed;
        rectangle.x = position.x;
        rectangle.y = position.y;
        angle += speed / 2;
        if (position.x < -60) {
            recreate();
        }
    }
}
