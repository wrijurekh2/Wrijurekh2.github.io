package io.github.UniSim;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;

    private Stage stage;
    private Label outputLabel;

    private int screenHeight;
    private int screenWidth;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("preview.png");

        button();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(image, 0, 0, screenWidth, screenHeight);
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

    public void button() {
        screenHeight = Gdx.graphics.getHeight();
        screenWidth = Gdx.graphics.getWidth();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;
        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        Button button2 = new TextButton("Text Button", mySkin);
        button2.setSize(col_width * 4, row_height);
        button2.setPosition(col_width * 7, Gdx.graphics.getHeight() - row_height * 3);
        button2.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Press a Button");
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Pressed Text Button");
                return true;
            }
        });
        stage.addActor(button2);

        outputLabel = new Label("Press a Button", mySkin);
        outputLabel.setSize(Gdx.graphics.getWidth(), row_height);
        outputLabel.setPosition(0, row_height);
        outputLabel.setAlignment(Align.center);
        stage.addActor(outputLabel);
    }
}
