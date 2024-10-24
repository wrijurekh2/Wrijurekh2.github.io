package io.github.UniSim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class MainGameScreen implements Screen {
    public static final float speed = 120;
    Texture MAPV2;
    float x;
    float y;
    OrthographicCamera camera;

    Main game;

    BitmapFont timeFont;
    TimerClass timer;
    Texture inventory;
    Texture PauseButtonActive;
    Texture PauseButtonInactive;
    float cameraX;
    float cameraY;
    float newposx;
    float newposy;

    public MainGameScreen(Main game) {
        this.game = game;
        timeFont = new BitmapFont(Gdx.files.internal("score.fnt"));
    }

    @Override
    public void show() {
        MAPV2 = new Texture("MAPV2.gif");
        timer = new TimerClass(5);
        inventory = new Texture("INVENTORY.png");
        PauseButtonActive = new Texture("PAUSE_BUTTON_ACTIVE.png");
        PauseButtonInactive = new Texture("PAUSE_BUTTON_INACTIVE.png");
        camera = new OrthographicCamera(980, 864);
        camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2);
        cameraX = camera.position.x;
        cameraY = camera.position.x - (camera.viewportHeight / 2);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        if (Gdx.input.isKeyPressed(Keys.UP)) {
            camera.translate(0f, 1f);

        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            camera.translate(0f, -1f);

        }

        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            camera.translate(-1f, 0f);

        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            camera.translate(1f, 0f);

        }

        game.batch.begin();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.draw(MAPV2, x, y);
        if (Gdx.input.isKeyPressed(Keys.TAB)) {
            game.batch.draw(inventory, 250, 300);
        }
        game.batch.draw(PauseButtonInactive, 850, 830);
        if (Gdx.input.getX() > 850 && Gdx.input.getX() < 979 && Gdx.input.getY() > 1 && Gdx.input.getY() < 34) {
            game.batch.draw(PauseButtonActive, 850, 830);
            if (Gdx.input.isTouched()) {

            }

        }

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
