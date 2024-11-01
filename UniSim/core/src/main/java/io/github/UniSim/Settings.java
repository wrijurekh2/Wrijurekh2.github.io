package io.github.UniSim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class Settings implements Screen{
    Main game;
    Texture InBackground;
    Texture PauseButtonInactive;
    Texture PauseButtonActive;
    boolean checked = false;
    Texture header;

    //Initialisation of Settings Page
    public Settings(Main game){
        this.game = game;
        InBackground = new Texture("BACKGROUND.jpg");
        PauseButtonInactive = new Texture("PAUSE_BUTTON_INACTIVE.png");
        PauseButtonActive = new Texture("PAUSE_BUTTON_ACTIVE.png");
        header = new Texture("HEADER.png");
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        //Draw functions to create the UI
        game.batch.begin();
        game.batch.draw(InBackground, 0, 0);
        game.batch.draw(header, 45, 580);
        game.batch.draw(PauseButtonInactive, 850, 0);
        //Capture mouse input
        if (Gdx.input.getX() > 850 && Gdx.input.getX() < 980 && Gdx.input.getY() > 820 && Gdx.input.getY() < 900) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        }
        if (Gdx.input.getX() > 400 && Gdx.input.getX() < 540 && Gdx.input.getY() > 550 && Gdx.input.getY() < 600) {
            game.batch.draw(PauseButtonActive, 400, 270);
            if (Gdx.input.justTouched()) {
                checked = !checked;
            }
        }
        //Add implementation of muting music
        if (!checked){
            game.batch.draw(PauseButtonInactive, 400, 270);
        }
        else{
            game.batch.draw(PauseButtonActive, 400, 270);
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
