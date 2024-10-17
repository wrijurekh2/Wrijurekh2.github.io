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
        game.batch.draw(header, 75, 700);

        if (Gdx.input.getX() < 735 && Gdx.input.getX() > 245 && Gdx.input.getY() < 412 && Gdx.input.getY() > 312) {
            game.batch.draw(playbuttonactive, 325, 457);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new MainGameScreen(game));
            }
        } else {
            game.batch.draw(playbuttoninactive, 325, 457);
        }

        if (Gdx.input.getX() > 125 && Gdx.input.getX() < 950 && Gdx.input.getY() < 525 && Gdx.input.getY() > 425) {
            game.batch.draw(howbuttonactive, 125, 330);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new InstructionScreen(game));
            }
        } else {
            game.batch.draw(howbuttoninactive, 125, 330);
        }

        if (Gdx.input.getX() < 685 && Gdx.input.getX() > 325 && Gdx.input.getY() > 532 && Gdx.input.getY() < 632) {
            game.batch.draw(exitbuttonactive, 325, 232);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exitbuttoninactive, 325, 232);
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
