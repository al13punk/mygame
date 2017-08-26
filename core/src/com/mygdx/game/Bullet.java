package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private Vector2 position;
    private float speed;
    private boolean active;

    public Vector2 getPosition() {
        return position;
    }

    public boolean isActive() {
        return active;
    }

    public Bullet() {
        position = new Vector2(0.0f, 0.0f);
        speed = 6.0f;
        active = false;
    }

    public void destroy() {
        active = false;
    }

    public void setup(float x, float y, float speed) {
        position.x = x;
        position.y = y;
        this.speed = speed;
        active = true;
    }

    public void update() {
        position.x += speed;
        if (position.x > 1280) {
            destroy();
        }
    }
}
