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

public class MainGameScreen implements Screen {
    public static final float speed = 120;
    Texture MAPV2;
    OrthographicCamera camera;
    OrthographicCamera hudcamera;
    public boolean ispaused;
    private boolean add = false;
    private int start = 0;

    Main game;
    private DragAndDropManager dragAndDropManager;

    BitmapFont WFont;
    BitmapFont BFont;
    TimerClass timer;
    MoneyClass money;
    ReputationClass reputation;
    Texture accomodationicon;
    Texture libraryicon;
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
    private Rectangle pausebounds;
    private int accomodationcount = 3;
    private int librarycount = 3;
    private int cafeteriacount = 3;
    private int recreationalhubcount = 3;

    private int accPlaced = 0;
    private int libPlaced = 0;
    private int cafePlaced = 0;
    private int recPlaced = 0;

    Rectangle trashCanBounds;

    //Initialises the main game
    public MainGameScreen(Main game) {
        this.game = game;
        WFont = new BitmapFont(Gdx.files.internal("score.fnt"));
        BFont = new BitmapFont(Gdx.files.internal("blackFnt.fnt"));
    }

    @Override
    public void show() {
        //Sets all the textures
        timer = new TimerClass(5); //Starts the timer (5 minutes)
        money = new MoneyClass(10000);
        reputation = new ReputationClass(90);

        MAPV2 = new Texture("MAPV2.gif");
        inventory = new Texture("INVENTORY.png");
        PauseButtonActive = new Texture("PAUSE_BUTTON_ACTIVE.png");
        PauseButtonInactive = new Texture("PAUSE_BUTTON_INACTIVE.png");
        BottomBar = new Texture("BOTTOM_BAR.png");
        cafeteriaicon = new Texture("BUILDING1.png");
        libraryicon = new Texture("BUILDING2.png");
        recreationalhubicon = new Texture("BUILDING3.png");
        accomodationicon = new Texture("BUILDING4.png");
        trashCanIdle = new Texture("TRASH_CAN_IDLE.png");
        trashCanHover = new Texture("TRASH_CAN_HOVER.png");

        // Define icon bounds
        accomodationbounds = new Rectangle(35, 10, 128, 128);
        librarybounds = new Rectangle(270, 10, 128, 128);
        cafeteriabounds = new Rectangle(520, 10, 128, 128);
        recreationalhubbounds = new Rectangle(765, 10, 128, 128);
        pausebounds = new Rectangle(850, 810, 137, 37);
        trashCanBounds = new Rectangle(900, 150, 64, 64);

        // Initialise cameras
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

        //Records the mouse location
        float touchX = Gdx.input.getX();
        float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

        //If the pause button is clicked it will pause/unpause the game
        if (Gdx.input.justTouched()){
            if(pausebounds.contains(touchX,touchY)){
                ispaused = !ispaused;
            }
        }

        if (!ispaused) { //Game resumes
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

            //Handle all mouse inputs for the buildings
            if (Gdx.input.isTouched()) {
                if (!dragAndDropManager.isDragging()) {
                    // Check if we're selecting an existing building to reposition
                    if (!dragAndDropManager.selectPlacedBuilding(touchX, touchY)) {
                        // Check for icons in bottom bar to start dragging a new building
                        if (accomodationbounds.contains(touchX, touchY) && accomodationcount > 0 && money.getMoney() >= 1000) {
                            dragAndDropManager.startDrag("accomodation", accomodationicon, touchX, touchY);
                            accomodationcount--;
                            accPlaced++;
                            money.remMoney(1000);
                            reputation.addRep(5);
                        } else if (librarybounds.contains(touchX, touchY) && librarycount > 0 && money.getMoney() >= 1500) {
                            dragAndDropManager.startDrag("library", libraryicon, touchX, touchY);
                            librarycount--;
                            libPlaced++;
                            money.remMoney(1500);
                            reputation.addRep(10);
                        } else if (cafeteriabounds.contains(touchX, touchY) && cafeteriacount > 0 && money.getMoney() >= 2500) {
                            dragAndDropManager.startDrag("cafeteria", cafeteriaicon, touchX, touchY);
                            cafeteriacount--;
                            cafePlaced++;
                            money.remMoney(2500);
                            reputation.addRep(10);
                        } else if (recreationalhubbounds.contains(touchX, touchY) && recreationalhubcount > 0 && money.getMoney() >= 3000) {
                            dragAndDropManager.startDrag("recreationalhub", recreationalhubicon, touchX, touchY);
                            recreationalhubcount--;
                            recPlaced++;
                            money.remMoney(3000);
                            reputation.addRep(15);
                        }
                    }
                }else {
                    // Update drag position
                    dragAndDropManager.updateDragPosition(touchX, touchY);

                    // Check if the dragged building is hovering over the trash can

                }
            } else if (dragAndDropManager.isDragging()) {

                // Check if released over the trash can
                if (dragAndDropManager.isHoveringOverTrash(touchX, touchY, trashCanBounds)) {
                    if(dragAndDropManager.selectedTexture == accomodationicon){
                        accPlaced--;
                        accomodationcount++;
                        money.addMoney(200);
                        reputation.remRep(10);
                        System.out.println("acc");
                    }
                    else if (dragAndDropManager.selectedTexture == libraryicon){
                        libPlaced--;
                        librarycount++;
                        money.addMoney(375);
                        reputation.remRep(15);
                        System.out.println("lib");
                    }
                    else if (dragAndDropManager.selectedTexture == cafeteriaicon){
                        cafePlaced--;
                        cafeteriacount++;
                        money.addMoney(625);
                        reputation.remRep(15);
                        System.out.println("caf");
                    }
                    else if (dragAndDropManager.selectedTexture == recreationalhubicon){
                        recPlaced--;
                        recreationalhubcount++;
                        money.addMoney(750);
                        reputation.remRep(20);
                        System.out.println("rec");
                    }
                    
                    dragAndDropManager.canceldrag(); // Discard the building
                } else {
                    dragAndDropManager.stopDrag(); // Place the building on the map
                }
            }

        } else {
            timer.pause(); //Pause the timer
        }

        int sec = timer.getSecRem();

        //Decrease reputation every 15 seconds
        if(sec % 15 == 0 && add == false && sec != 0){
            reputation.remRep(10);
             //Add money based on amount of buildings and their type every 30 seconds 
            if(sec % 30 == 0 && add == false){
                money.addMoney((500 * accPlaced) + (600 * libPlaced) + (750 * cafePlaced) + (900 * recPlaced));
                reputation.addRep((2 * accPlaced) + (3 * libPlaced) + (4 * cafePlaced) + (5 * recPlaced));
                //Add constant amount of money every year
                if(sec % 60 == 0 && add == false){
                    money.addMoney(2000);
                }
            }
            add = true;
        }
        //Makes sure that the increase/decrease isn't performed multiple times but only once
        if (sec % 15 != 0){
            add = false;
        }


        // Render the main game world using the main camera
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(MAPV2, 0, 0); // Draw the map

        dragAndDropManager.drawPlacedBuildings(game.batch);
        game.batch.end();

        // Render HUD elements using the HUD camera
        hudcamera.update();
        game.batch.setProjectionMatrix(hudcamera.combined);
        game.batch.begin();

        //UI implementation
        if (dragAndDropManager.isDragging() && dragAndDropManager.isHoveringOverTrash(touchX, touchY, trashCanBounds)) {
            game.batch.draw(trashCanHover, trashCanBounds.x, trashCanBounds.y, trashCanBounds.width,
                    trashCanBounds.height);
        } else {
            game.batch.draw(trashCanIdle, trashCanBounds.x, trashCanBounds.y, trashCanBounds.width,
                    trashCanBounds.height);
        }
        game.batch.draw(BottomBar, 0, 0, Gdx.graphics.getWidth(), 150);

        game.batch.draw(PauseButtonInactive, pausebounds.x, pausebounds.y, pausebounds.getWidth(), pausebounds.getHeight());

        game.batch.draw(accomodationicon, accomodationbounds.x, accomodationbounds.y, accomodationbounds.getWidth(),
                accomodationbounds.getHeight());
        BFont.draw(game.batch, "x" + accomodationcount, accomodationbounds.x + 135, accomodationbounds.y + 30);

        game.batch.draw(libraryicon, librarybounds.x, librarybounds.y, librarybounds.getWidth(),
                librarybounds.getHeight());
        BFont.draw(game.batch, "x" + librarycount, librarybounds.x + 135, librarybounds.y + 30);

        game.batch.draw(cafeteriaicon, cafeteriabounds.x, cafeteriabounds.y, cafeteriabounds.getWidth(),
                cafeteriabounds.getHeight());
        BFont.draw(game.batch, "x" + cafeteriacount, cafeteriabounds.x + 135, cafeteriabounds.y + 30);

        game.batch.draw(recreationalhubicon, recreationalhubbounds.x, recreationalhubbounds.y,
                recreationalhubbounds.getWidth(), recreationalhubbounds.getHeight());
        BFont.draw(game.batch, "x" + recreationalhubcount, recreationalhubbounds.x + 135,
                recreationalhubbounds.y + 30);

        //UI of the amount of each buidlings placed so far

        game.batch.draw(accomodationicon, 10, 160, accomodationbounds.getWidth() - 50,
                accomodationbounds.getHeight() - 50);
        WFont.draw(game.batch, "x" + accPlaced, 110, 200);

        game.batch.draw(libraryicon, 10, 260, librarybounds.getWidth() - 50,
                librarybounds.getHeight() - 50);
        WFont.draw(game.batch, "x" + libPlaced, 110, 300);

        game.batch.draw(cafeteriaicon, 10, 360, cafeteriabounds.getWidth() - 50,
                cafeteriabounds.getHeight() - 50);
        WFont.draw(game.batch, "x" + cafePlaced, 110, 400);

        game.batch.draw(recreationalhubicon, 10, 460, recreationalhubbounds.getWidth() - 50,
                recreationalhubbounds.getHeight() - 50);
        WFont.draw(game.batch, "x" + recPlaced, 110, 500);

        // Draw the dragged building if dragging
        dragAndDropManager.drawDraggedBuilding(game.batch);

        // Display timer and other HUD elements
        GlyphLayout RealTimeLayout = new GlyphLayout(WFont, timer.updateRealTime());
        WFont.draw(game.batch, RealTimeLayout, Gdx.graphics.getWidth() / 2 - RealTimeLayout.width / 2 - 120,
                Gdx.graphics.getHeight() - RealTimeLayout.height - 2);

        GlyphLayout GameTimeLayout = new GlyphLayout(WFont,
                "Y:" + timer.getGameYear() + " M:" + timer.getGameMonth());
        WFont.draw(game.batch, GameTimeLayout, Gdx.graphics.getWidth() / 2 - GameTimeLayout.width / 2 + 120,
                Gdx.graphics.getHeight() - GameTimeLayout.height - 2);

        GlyphLayout MRLabelLayout = new GlyphLayout(WFont, "Money:\nRep:");
        WFont.draw(game.batch, MRLabelLayout, Gdx.graphics.getWidth() / 2 - MRLabelLayout.width / 2 - 380,
        Gdx.graphics.getHeight() - MRLabelLayout.height - 2 - 50);

        GlyphLayout MoneyLayout = new GlyphLayout(WFont, "" + money.getMoney());
        WFont.draw(game.batch, MoneyLayout, Gdx.graphics.getWidth() / 2 - MoneyLayout.width / 2 - 200,
        Gdx.graphics.getHeight() - MoneyLayout.height - 2 - 83);

        GlyphLayout RepLayout = new GlyphLayout(WFont, "" + reputation.getRep());
        WFont.draw(game.batch, RepLayout, Gdx.graphics.getWidth() / 2 - RepLayout.width / 2 - 295,
        Gdx.graphics.getHeight() - RepLayout.height - 2 - 118);

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
            pauseFont.getData().setScale(2f);
            GlyphLayout pauseLayout = new GlyphLayout(pauseFont, "Paused");
            pauseFont.draw(game.batch, pauseLayout, Gdx.graphics.getWidth() / 2 - pauseLayout.width / 2,
                    Gdx.graphics.getHeight() / 2 + pauseLayout.height / 2);
            game.batch.draw(PauseButtonActive, pausebounds.x, pausebounds.y, pausebounds.getWidth(), pausebounds.getHeight());
            game.batch.end();
        }

        //Pause or unpause the game when escape button is pressed
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            ispaused = !ispaused;
        }

        //Starts the game in the paused state
        if(start == 0){
            start++;
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
