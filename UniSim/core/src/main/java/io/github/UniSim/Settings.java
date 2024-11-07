package io.github.UniSim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class Settings implements Screen {
    Main game;
    Texture InBackground;
    Texture CheckInactive;
    Texture CheckActive;
    Texture ReturnButtonInactive;
    boolean checked = false;
    Texture header;
    BitmapFont BFont;

    // Initialisation of Settings Page
    public Settings(Main game) {
        this.game = game;
        InBackground = new Texture("BACKGROUND.jpg");
        CheckInactive = new Texture("CHECK_INACTIVE.png");
        CheckActive = new Texture("CHECK_ACTIVE.png");
        ReturnButtonInactive = new Texture("RETURN_BUTTON_INACTIVE.png");
        header = new Texture("HEADER.png");
        BFont = new BitmapFont(Gdx.files.internal("blackFnt.fnt"));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        // Draw functions to create the UI
        game.batch.begin();
        game.batch.draw(InBackground, 0, 0);
        game.batch.draw(header, 45, 580);
        game.batch.draw(ReturnButtonInactive, 850, 20);
        // Capture mouse input
        if (Gdx.input.getX() > 850 && Gdx.input.getX() < 980 && Gdx.input.getY() > 800 && Gdx.input.getY() < 900) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        }
        if (Gdx.input.getX() > 440 && Gdx.input.getX() < 490 && Gdx.input.getY() > 550 && Gdx.input.getY() < 600) {
            game.batch.draw(CheckActive, 400, 270);
            if (Gdx.input.justTouched()) {
                checked = !checked;
            }
        }
        // Add implementation of muting music
        if (!checked) {
            game.batch.draw(CheckInactive, 400, 270);
        } else {
            game.batch.draw(CheckActive, 400, 270);
        }

        // Label
        GlyphLayout RealTimeLayout = new GlyphLayout(BFont, "MUTE");
        BFont.draw(game.batch, RealTimeLayout, 500, 300);

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
