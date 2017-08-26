package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    static class KeyControls {
        final int KEY_UP;
        final int KEY_DOWN;
        final int KEY_LEFT;
        final int KEY_RIGHT;
        final int KEY_ACCELERATION;
        final int KEY_FIRE;

        public KeyControls(int KEY_UP, int KEY_DOWN, int KEY_LEFT, int KEY_RIGHT, int KEY_ACCELERATION, int KEY_FIRE) {
            this.KEY_UP = KEY_UP;
            this.KEY_DOWN = KEY_DOWN;
            this.KEY_LEFT = KEY_LEFT;
            this.KEY_RIGHT = KEY_RIGHT;
            this.KEY_ACCELERATION = KEY_ACCELERATION;
            this.KEY_FIRE = KEY_FIRE;
        }
    }

    private Texture texture;
    private Vector2 position;
    private float speed;
    private KeyControls kc;
    private int fireCounter;
    private int fireRate;

    public Hero(int posX, int posY, KeyControls kc) {
        texture = new Texture("ship80x60.tga");
        position = new Vector2(posX, posY);
        speed = 8.0f;
        this.kc = kc;
        fireRate = 6;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void update() {
        if (Gdx.input.isKeyPressed(kc.KEY_UP)) {
            position.y += speed;
            if (position.y > 720) {
                position.y = -60;
            }
        }
        if (Gdx.input.isKeyPressed(kc.KEY_DOWN)) {
            position.y -= speed;
            if (position.y < -60) {
                position.y = 720;
            }
        }
        if (Gdx.input.isKeyPressed(kc.KEY_LEFT)) {
            position.x -= speed;
            if (position.x < 0) {
                position.x = 0;
            }
        }
        if (Gdx.input.isKeyPressed(kc.KEY_RIGHT)) {
            position.x += speed;
            if (position.x > 1200) {
                position.x = 1200;
            }
        }
        if (Gdx.input.isKeyPressed(kc.KEY_FIRE)) {
            fireCounter++;
            if (fireCounter > fireRate) {
                fireCounter = 0;
                bulletActivate(20, 0, 12);
                bulletActivate(26, 12, 12);
            }
        }
    }

    public void bulletActivate(float dx, float dy, float speed) {
        for (int i = 0; i < MyGdxGame.bullets.length; i++) {
            if (!MyGdxGame.bullets[i].isActive()) {
                MyGdxGame.bullets[i].setup(position.x + dx, position.y + dy, speed);
                break;
            }
        }
    }
}
