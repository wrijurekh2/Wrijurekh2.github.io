package io.github.UniSim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameOverScreen implements Screen {
    private Main game;
    private BitmapFont BFont;
    Texture background;

    public GameOverScreen(Main game) {
        this.game = game;
        BFont = new BitmapFont(Gdx.files.internal("blackFnt.fnt"));
        background = new Texture("BACKGROUND.jpg");
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(background, 0, 0);
        BFont.getData().setScale(2);
        BFont.setColor(0, 0, 1, 1);
        BFont.draw(game.batch, "GAME OVER", Gdx.graphics.getWidth() / 2 - 300, Gdx.graphics.getHeight() / 2 + 180);
        // BFont.setColor(Color.BLACK);
        BFont.draw(game.batch, "RETRY", Gdx.graphics.getWidth() / 2 - 175,
                Gdx.graphics.getHeight() / 2 + 30);
        BFont.draw(game.batch, "EXIT", Gdx.graphics.getWidth() / 2 - 140, Gdx.graphics.getHeight() / 2 - 120);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            game.setScreen(new MainGameScreen(game)); // Restart game
        } else if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            Gdx.app.exit(); // Quit game
        }
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
        BFont.dispose();
    }
}
