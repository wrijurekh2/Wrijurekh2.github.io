package io.github.UniSim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MainGameScreen implements Screen {
    public static final float speed = 120;
    Texture MAPV2;
    OrthographicCamera camera;
    OrthographicCamera hudcamera;
    public boolean ispaused = false;

    Main game;
    private DragAndDropManager dragAndDropManager;

    BitmapFont timeFont;
    TimerClass timer;
    Texture accomodationicon;
    Texture Libraryicon;
    Texture cafeteriaicon;
    Texture recreationalhubicon;
    Texture inventory;
    Texture PauseButtonActive;
    Texture PauseButtonInactive;
    Texture BottomBar;
    Texture trashCanIdle;
    Texture trashCanHover;

    float cameraX;
    float cameraY;
    private Rectangle accomodationbounds;
    private Rectangle librarybounds;
    private Rectangle cafeteriabounds;
    private Rectangle recreationalhubbounds;
    Rectangle trashCanBounds;

    public MainGameScreen(Main game) {
        this.game = game;
        timeFont = new BitmapFont(Gdx.files.internal("score.fnt"));
    }

    @Override
    public void show() {
        timer = new TimerClass(5);
        MAPV2 = new Texture("MAPV2.gif");
        inventory = new Texture("INVENTORY.png");
        PauseButtonActive = new Texture("PAUSE_BUTTON_ACTIVE.png");
        PauseButtonInactive = new Texture("PAUSE_BUTTON_INACTIVE.png");
        BottomBar = new Texture("BOTTOM_BAR.png");
        cafeteriaicon = new Texture("BUILDING1.png");
        Libraryicon = new Texture("BUILDING2.png");
        recreationalhubicon = new Texture("BUILDING3.png");
        accomodationicon = new Texture("BUILDING4.png");
        trashCanIdle = new Texture("TRASH_CAN_IDLE.png");
        trashCanHover = new Texture("TRASH_CAN_HOVER.png");

        // Define icon bounds
        accomodationbounds = new Rectangle(50, 10, 128, 128);
        librarybounds = new Rectangle(300, 10, 128, 128);
        cafeteriabounds = new Rectangle(550, 10, 128, 128);
        recreationalhubbounds = new Rectangle(800, 10, 128, 128);
        trashCanBounds = new Rectangle(10, Gdx.graphics.getHeight() - 54, 64, 64);

        // Initialize cameras
        camera = new OrthographicCamera(980, 864);
        camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2);
        hudcamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        hudcamera.position.set(hudcamera.viewportWidth / 2, hudcamera.viewportHeight / 2, 0);
        hudcamera.update();

        dragAndDropManager = new DragAndDropManager(camera);
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float touchX = Gdx.input.getX();
        float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

        if (!ispaused) {
            camera.update();
            timer.resume();

            // Handle camera movement
            if (Gdx.input.isKeyPressed(Keys.UP))
                camera.translate(0, 1f);
            if (Gdx.input.isKeyPressed(Keys.DOWN))
                camera.translate(0, -1f);
            if (Gdx.input.isKeyPressed(Keys.LEFT))
                camera.translate(-1f, 0);
            if (Gdx.input.isKeyPressed(Keys.RIGHT))
                camera.translate(1f, 0);

            if (Gdx.input.isTouched()) {

                if (!dragAndDropManager.isDragging()) {
                    // Check if we're selecting an existing building to reposition
                    if (!dragAndDropManager.selectPlacedBuilding(touchX, touchY)) {
                        // Check for icons in bottom bar to start dragging a new building
                        if (accomodationbounds.contains(touchX, touchY)) {
                            dragAndDropManager.startDrag("accomodation", accomodationicon, touchX, touchY);
                        } else if (librarybounds.contains(touchX, touchY)) {
                            dragAndDropManager.startDrag("library", Libraryicon, touchX, touchY);
                        } else if (cafeteriabounds.contains(touchX, touchY)) {
                            dragAndDropManager.startDrag("cafeteria", cafeteriaicon, touchX, touchY);
                        } else if (recreationalhubbounds.contains(touchX, touchY)) {
                            dragAndDropManager.startDrag("recreationalhub", recreationalhubicon, touchX, touchY);
                        }
                    }
                } else {
                    // Update drag position
                    dragAndDropManager.updateDragPosition(touchX, touchY);

                    // Check if the dragged building is hovering over the trash can

                }
            } else if (dragAndDropManager.isDragging()) {

                // Check if released over the trash can
                if (dragAndDropManager.isHoveringOverTrash(touchX, touchY, trashCanBounds)) {
                    dragAndDropManager.canceldrag(); // Discard the building
                } else {
                    dragAndDropManager.stopDrag(); // Place the building on the map
                }
            }

        } else {
            timer.pause();
        }

        // Render the main game world using the main camera
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(MAPV2, 0, 0); // Draw background or map
        dragAndDropManager.drawPlacedBuildings(game.batch);
        game.batch.end();

        // Render HUD elements using the HUD camera
        hudcamera.update();
        game.batch.setProjectionMatrix(hudcamera.combined);
        game.batch.begin();
        if (dragAndDropManager.isDragging() && dragAndDropManager.isHoveringOverTrash(touchX, touchY, trashCanBounds)) {
            game.batch.draw(trashCanHover, trashCanBounds.x, trashCanBounds.y, trashCanBounds.width,
                    trashCanBounds.height);
        } else {
            game.batch.draw(trashCanIdle, trashCanBounds.x, trashCanBounds.y, trashCanBounds.width,
                    trashCanBounds.height);
        }
        game.batch.draw(BottomBar, 0, 0, Gdx.graphics.getWidth(), 150);
        game.batch.draw(accomodationicon, accomodationbounds.x, accomodationbounds.y, accomodationbounds.getWidth(),
                accomodationbounds.getHeight());
        game.batch.draw(Libraryicon, librarybounds.x, librarybounds.y, librarybounds.getWidth(),
                librarybounds.getHeight());
        game.batch.draw(cafeteriaicon, cafeteriabounds.x, cafeteriabounds.y, cafeteriabounds.getWidth(),
                cafeteriabounds.getHeight());
        game.batch.draw(recreationalhubicon, recreationalhubbounds.x, recreationalhubbounds.y,
                recreationalhubbounds.getWidth(), recreationalhubbounds.getHeight());

        // Draw the dragged building if dragging
        dragAndDropManager.drawDraggedBuilding(game.batch);

        // Display timer and other HUD elements
        GlyphLayout RealTimeLayout = new GlyphLayout(timeFont, timer.updateRealTime());
        timeFont.draw(game.batch, RealTimeLayout, Gdx.graphics.getWidth() / 2 - RealTimeLayout.width / 2 - 120,
                Gdx.graphics.getHeight() - RealTimeLayout.height - 2);

        GlyphLayout GameTimeLayout = new GlyphLayout(timeFont,
                "Y:" + timer.getGameYear() + " M:" + timer.getGameMonth());
        timeFont.draw(game.batch, GameTimeLayout, Gdx.graphics.getWidth() / 2 - GameTimeLayout.width / 2 + 120,
                Gdx.graphics.getHeight() - GameTimeLayout.height - 2);

        game.batch.end();

        // Draw pause overlay if paused
        if (ispaused) {
            ShapeRenderer shapeRenderer = new ShapeRenderer();
            shapeRenderer.setProjectionMatrix(hudcamera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.setColor(0, 0, 0, 0.5f); // Semi-transparent overlay
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
            shapeRenderer.dispose();

            // Optionally draw a "Paused" label
            game.batch.begin();
            BitmapFont pauseFont = new BitmapFont();
            GlyphLayout pauseLayout = new GlyphLayout(pauseFont, "Paused");
            pauseFont.draw(game.batch, pauseLayout, Gdx.graphics.getWidth() / 2 - pauseLayout.width / 2,
                    Gdx.graphics.getHeight() / 2 + pauseLayout.height / 2);
            game.batch.end();
        }

        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            ispaused = !ispaused;
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
    }
}
