package io.github.UniSim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class MainGameScreen implements Screen {
    public static final float speed = 120;
    Texture image;
    float x;
    float y;

    Main game;

    BitmapFont timeFont;
    TimerClass timer;

    public MainGameScreen(Main game) {
        this.game = game;
        timeFont = new BitmapFont(Gdx.files.internal("score.fnt"));
    }

    @Override
    public void show() {
        image = new Texture("m1.png");
        timer = new TimerClass(5);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            y -= speed * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            y += speed * Gdx.graphics.getDeltaTime();

        }
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            x += speed * Gdx.graphics.getDeltaTime();

        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            x -= speed * Gdx.graphics.getDeltaTime();

        }

        game.batch.begin();
        game.batch.draw(image, x, y);
        GlyphLayout timeLayout = new GlyphLayout(timeFont, "" + timer.update());
        timeFont.draw(game.batch, timeLayout, Gdx.graphics.getWidth() / 2 - timeLayout.width / 2,
                Gdx.graphics.getHeight() - timeLayout.height - 5);
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
