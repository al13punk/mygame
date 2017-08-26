package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background background;
	Hero hero1;
	Hero hero2w;
	private final int ASTEROIDS_COUNT = 40;
	Asteroid[] asteroids;
	private final int BULLETS_COUNT = 150;
	public static Bullet[] bullets;
	private Texture textureBullet;
	private BitmapFont fnt;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Background();
		hero1 = new Hero(100, 100, new Hero.KeyControls(Input.Keys.UP, Input.Keys.DOWN, Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.SHIFT_RIGHT, Input.Keys.P));
//		hero2 = new Hero(100, 200, new Hero.KeyControls(Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D, Input.Keys.CONTROL_LEFT, Input.Keys.T));
		asteroids = new Asteroid[ASTEROIDS_COUNT];
		for (int i = 0; i < ASTEROIDS_COUNT; i++) {
			asteroids[i] = new Asteroid();
		}
		bullets = new Bullet[BULLETS_COUNT];
		for (int i = 0; i < BULLETS_COUNT; i++) {
			bullets[i] = new Bullet();
		}
		textureBullet = new Texture("bullet20.png");
		fnt = new BitmapFont();
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1); // R G B A
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.render(batch);
		hero1.render(batch);
//		hero2.render(batch);
		for (int i = 0; i < ASTEROIDS_COUNT; i++) {
			asteroids[i].render(batch);
		}
		for (int i = 0; i < BULLETS_COUNT; i++) {
			if(bullets[i].isActive())
				batch.draw(textureBullet, bullets[i].getPosition().x, bullets[i].getPosition().y);
		}
		fnt.draw(batch, "StarCommVII", 50, 50);
		batch.end();
	}

	public void update() {
		background.update();
		hero1.update();
//		hero2.update();
		for (int i = 0; i < ASTEROIDS_COUNT; i++) {
			asteroids[i].update();
		}
		for (int i = 0; i < BULLETS_COUNT; i++) {
			if(bullets[i].isActive()) {
				bullets[i].update();
				for (int j = 0; j < ASTEROIDS_COUNT; j++) {
					if(asteroids[j].getRectangle().contains(bullets[i].getPosition())) {
						asteroids[j].getDamage(1);
						bullets[i].destroy();
						break;
					}
				}
			}
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
