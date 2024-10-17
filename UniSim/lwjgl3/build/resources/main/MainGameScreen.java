package io.github.UniSim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MainGameScreen implements Screen {
    public static final float speed = 120;
    Texture image;
    float x;
    float y;

    Main game;

    public MainGameScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        image = new Texture("m1.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            y += speed * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            y -= speed * Gdx.graphics.getDeltaTime();

        }
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            x -= speed * Gdx.graphics.getDeltaTime();

        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            x += speed * Gdx.graphics.getDeltaTime();

        }

        game.batch.begin();
        game.batch.draw(image, x, y);
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
