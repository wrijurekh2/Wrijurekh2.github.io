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
    Texture ReturnButtonInactive;
    Texture ReturnButtonActive;
    Texture PrevPageActive;
    Texture PrevPageInactive;
    Texture NextPageActive;
    Texture NextPageInactive;
    Texture screenshot;
    String Instructions;
    BitmapFont Font;
    int pageNo = 0;

    //Instruction screen initialisation
    public InstructionScreen(Main game) {
        this.game = game;
        ReturnButtonInactive = new Texture("RETURN_BUTTON_INACTIVE.png");
        ReturnButtonActive = new Texture("RETURN_BUTTON_ACTIVE.png");
        PrevPageActive = new Texture("PREV_PAGE_ACTIVE.png");
        PrevPageInactive = new Texture("PREV_PAGE_INACTIVE.png");
        NextPageActive = new Texture("NEXT_PAGE_ACTIVE.png");
        NextPageInactive = new Texture("NEXT_PAGE_INACTIVE.png");
        header = new Texture("HEADER.png");
        screenshot = new Texture("ScreenshotFinal.png");
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
        
        if (pageNo == 0){
            game.batch.draw(header, 45, 580); //Creates a title

            if (Gdx.input.getX() > 450 && Gdx.input.getX() < 580 && Gdx.input.getY() > 800 && Gdx.input.getY() < 900) {
                game.batch.draw(NextPageActive, 450, 20);
                if (Gdx.input.justTouched()) {
                    pageNo++;
                }
            }
            else{
                game.batch.draw(NextPageInactive, 450, 20);
            }

            //Creates a layout and then using the layout to create a visible label with instructions
            GlyphLayout TimeLayout = new GlyphLayout(Font, Instructions);
            Font.draw(game.batch, TimeLayout, Gdx.graphics.getWidth() / 2 - TimeLayout.width / 2,
            Gdx.graphics.getHeight() - TimeLayout.height + 100);

        }
        else if(pageNo == 1){
            game.batch.draw(screenshot, 0, 0);

            if (Gdx.input.getX() > 450 && Gdx.input.getX() < 580 && Gdx.input.getY() > 800 && Gdx.input.getY() < 900) {
                game.batch.draw(PrevPageActive, 450, 20);
                if (Gdx.input.justTouched()) {
                    pageNo--;
                }
            }
            else{
                game.batch.draw(PrevPageInactive, 450, 20);
            }
        }

        //Return button
        if (Gdx.input.getX() > 850 && Gdx.input.getX() < 980 && Gdx.input.getY() > 800 && Gdx.input.getY() < 900) {
            game.batch.draw(ReturnButtonActive, 850, 20);
            if (Gdx.input.justTouched()) {
                this.dispose();
                game.setScreen(new MainMenuScreen(game)); //Returns back to the main menu
            }
        }
        else{
            game.batch.draw(ReturnButtonInactive, 850, 20);
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
