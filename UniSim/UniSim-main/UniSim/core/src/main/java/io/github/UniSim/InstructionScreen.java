package io.github.UniSim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class InstructionScreen implements Screen {

    Main game;

    Texture InBackground;
    Texture header;
    Texture PauseButtonInactive;
    Texture PauseButtonActive;
    String Instructions;
    BitmapFont Font;

    //Instruction screen initialisation
    public InstructionScreen(Main game) {
        this.game = game;
        PauseButtonInactive = new Texture("PAUSE_BUTTON_INACTIVE.png");
        PauseButtonActive = new Texture("PAUSE_BUTTON_ACTIVE.png");
        header = new Texture("HEADER.png");
        InBackground = new Texture("BACKGROUND.jpg");
        Font = new BitmapFont(Gdx.files.internal("blackFnt.fnt"));
        //Will replace with a single png image of the game instructions
        Instructions = " -The game is based around \n   building " +
                        "your own\n   university\n -Reach 100% student\n   satisfaction" +
                                                        " before timer\n   runs out\n -When you run out of time\n   you lose\n -Place buildings using the\n" +
                                                                                                                        "   on-screen buttons\n -Random events will occur\n   " +
                                                                                                                                     "that will impact your game";
        //Increase the font size / text size
        Font.getData().setScale(1f);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 0); 
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);//Clears the background
        game.batch.begin();
        game.batch.draw(InBackground, 0, 0); //Creates background
        game.batch.draw(header, 45, 580); //Creates a title
        //Return button - new png needed
        if (Gdx.input.getX() > 850 && Gdx.input.getX() < 980 && Gdx.input.getY() > 820 && Gdx.input.getY() < 900) {
            game.batch.draw(PauseButtonActive, 850, 0);
            if (Gdx.input.justTouched()) {
                this.dispose();
                game.setScreen(new MainMenuScreen(game)); //Returns back to the main menu
            }
        }
        else{
            game.batch.draw(PauseButtonInactive, 850, 0);
        }

        //Creates a layout and then using the layout to create a visible label with instructions
        GlyphLayout TimeLayout = new GlyphLayout(Font, Instructions);
        Font.draw(game.batch, TimeLayout, Gdx.graphics.getWidth() / 2 - TimeLayout.width / 2,
                Gdx.graphics.getHeight() - TimeLayout.height + 100);

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
