package io.github.UniSim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;

public class GameOverScreen implements Screen {
    private Main game;
    private BitmapFont BFont;
    Texture background;

    private Rectangle accomodationbounds;
    private Rectangle librarybounds;
    private Rectangle cafeteriabounds;
    private Rectangle recreationalhubbounds;

    Texture mainbuttonactive;
    Texture mainbuttoninactive;
    Texture exitbuttonactive;
    Texture exitbuttoninactive;
    Texture retrybuttonactive;
    Texture retrybuttoninactive;
    Texture gameover;
    Texture accomodationicon;
    Texture libraryicon;
    Texture cafeteriaicon;
    Texture recreationalhubicon;

    private int accPlaced = 0;
    private int libPlaced = 0;
    private int cafePlaced = 0;
    private int recPlaced = 0;

    public GameOverScreen(Main game) {
        this.game = game;
        //Set the font
        BFont = new BitmapFont(Gdx.files.internal("blackFnt.fnt"));
        // Sets all the textures
        background = new Texture("BACKGROUND.jpg");
        mainbuttonactive = new Texture("MAIN_BUTTON_ACTIVE.png");
        mainbuttoninactive = new Texture("MAIN_BUTTON_INACTIVE.png");
        exitbuttonactive = new Texture("EXIT_BUTTON_ACTIVE2.png");
        exitbuttoninactive = new Texture("EXIT_BUTTON_INACTIVE.png");
        retrybuttonactive = new Texture("RETRY_BUTTON_ACTIVE.png");
        retrybuttoninactive = new Texture("RETRY_BUTTON_INACTIVE.png");
        gameover = new Texture("GAME_OVER.png");
        cafeteriaicon = new Texture("BUILDING1.png");
        libraryicon = new Texture("BUILDING2.png");
        recreationalhubicon = new Texture("BUILDING3.png");
        accomodationicon = new Texture("BUILDING4.png");

        //Set the icon bounds
        accomodationbounds = new Rectangle(35, 10, 128, 128);
        librarybounds = new Rectangle(270, 10, 128, 128);
        cafeteriabounds = new Rectangle(520, 10, 128, 128);
        recreationalhubbounds = new Rectangle(765, 10, 128, 128);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        // Clears background
        Gdx.gl.glClearColor(1, 1, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Creates the UI - background, title, buttons
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.draw(gameover, 140, 550, 700, 300);

        // If the mouse is clicked withing those coordinates it will trigger it
        if (Gdx.input.getX() > 230 && Gdx.input.getX() < 750 && Gdx.input.getY() < 460 && Gdx.input.getY() > 360) {
            game.batch.draw(mainbuttonactive, 230, 400);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        } else {
            game.batch.draw(mainbuttoninactive, 230, 400);
        }

        if (Gdx.input.getX() > 320 && Gdx.input.getX() < 660 && Gdx.input.getY() < 590 && Gdx.input.getY() > 460) {
            game.batch.draw(retrybuttonactive, 320, 270);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new MainGameScreen(game));
            }
        } else {
            game.batch.draw(retrybuttoninactive, 320, 270);
        }

        if (Gdx.input.getX() > 370 && Gdx.input.getX() < 600 && Gdx.input.getY() < 710 && Gdx.input.getY() > 610) {
            game.batch.draw(exitbuttonactive, 370, 150);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit(); // Exits the game
            }
        } else {
            game.batch.draw(exitbuttoninactive, 370, 150);
        }

        // UI of the amount of each buidlings placed so far

        game.batch.draw(accomodationicon, 10, 160, accomodationbounds.getWidth() - 50,
                accomodationbounds.getHeight() - 50);
        BFont.draw(game.batch, "x" + accPlaced, 110, 200);

        game.batch.draw(libraryicon, 10, 260, librarybounds.getWidth() - 50,
                librarybounds.getHeight() - 50);
        BFont.draw(game.batch, "x" + libPlaced, 110, 300);

        game.batch.draw(cafeteriaicon, 10, 360, cafeteriabounds.getWidth() - 50,
                cafeteriabounds.getHeight() - 50);
        BFont.draw(game.batch, "x" + cafePlaced, 110, 400);

        game.batch.draw(recreationalhubicon, 10, 460, recreationalhubbounds.getWidth() - 50,
                recreationalhubbounds.getHeight() - 50);
        BFont.draw(game.batch, "x" + recPlaced, 110, 500);

        BFont.draw(game.batch, "Total buildings placed:" + (accPlaced + recPlaced + cafePlaced + libPlaced), 50, 100);

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
        BFont.dispose();
    }

    //Retrieves the data from this screen
    //Used to have current stats on game over screen
    public void retrieveData(int accPlaced, int libPlaced, int cafePlaced, int recPlaced) {
        this.accPlaced = accPlaced;
        this.libPlaced = libPlaced;
        this.cafePlaced = cafePlaced;
        this.recPlaced = recPlaced;
    }
}
