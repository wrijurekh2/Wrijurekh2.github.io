package io.github.UniSim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MainMenuScreen implements Screen {

    Main game;

    Texture playbuttonactive;
    Texture playbuttoninactive;
    Texture exitbuttonactive;
    Texture exitbuttoninactive;
    Texture howbuttonactive;
    Texture howbuttoninactive;
    Texture background;
    Texture header;

    public MainMenuScreen(Main game) {
        this.game = game;
        playbuttonactive = new Texture("PLAY_BUTTON_ACTIVE.png");
        playbuttoninactive = new Texture("PLAY_BUTTON_INACTIVE.png");
        exitbuttonactive = new Texture("EXIT_BUTTON_ACTIVE.png");
        exitbuttoninactive = new Texture("EXIT_BUTTON_INACTIVE.png");
        howbuttonactive = new Texture("HOW_BUTTON_ACTIVE.png");
        howbuttoninactive = new Texture("HOW_BUTTON_INACTIVE.png");
        background = new Texture("BACKGROUND.jpg");
        header = new Texture("HEADER.png");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.draw(header, 45, 580);

        if (Gdx.input.getX() > 370 && Gdx.input.getX() < 620 && Gdx.input.getY() < 450 && Gdx.input.getY() > 360) {
            game.batch.draw(playbuttonactive, 370, 400);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new MainGameScreen(game));
            }
        } else {
            game.batch.draw(playbuttoninactive, 370, 400);
        }

        if (Gdx.input.getX() > 180 && Gdx.input.getX() < 800 && Gdx.input.getY() < 590 && Gdx.input.getY() > 460) {
            game.batch.draw(howbuttonactive, 180, 270);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new InstructionScreen(game));
            }
        } else {
            game.batch.draw(howbuttoninactive, 180, 270);
        }

        if (Gdx.input.getX() > 370 && Gdx.input.getX() < 620 && Gdx.input.getY() < 710 && Gdx.input.getY() > 610) {
            game.batch.draw(exitbuttonactive, 370, 150);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exitbuttoninactive, 370, 150);
        }

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
