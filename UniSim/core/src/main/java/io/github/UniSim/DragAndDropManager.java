package io.github.UniSim;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Rectangle;

public class DragAndDropManager {
    private boolean isDragging = false;
    private float dragX, dragY;
    private Texture selectedTexture = null;
    private OrthographicCamera camera;
    private ArrayList<PlacedBuilding> placedBuildings = new ArrayList<PlacedBuilding>();

    public DragAndDropManager(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void startDrag(String buildingType, Texture buildingTexture, float touchX, float touchY) {
        isDragging = true;
        selectedTexture = buildingTexture;

        Vector3 worldPos = new Vector3(touchX, touchY, 0);
        camera.unproject(worldPos); // Convert to world coordinates
        dragX = touchX;
        dragY = touchY;

        //System.out.println("Dragging started for " + buildingType);
    }

    public void updateDragPosition(float touchX, float touchY) {
        if (isDragging) {
            Vector3 worldPos = new Vector3(touchX, touchY, 0);
            camera.unproject(worldPos);
            dragX = (worldPos.x) - (camera.position.x - camera.viewportWidth / 2);
            dragY = (Gdx.graphics.getHeight() - worldPos.y) + camera.position.y - camera.viewportHeight / 2;
        }
    }

    public boolean selectPlacedBuilding(float touchX, float touchY) {
        Vector3 worldpos = new Vector3(touchX, Gdx.graphics.getHeight() - touchY, 0);
        camera.unproject(worldpos);

        float worldtouchX = worldpos.x;
        float worldtouchY = worldpos.y;

        for (int i = 0; i < placedBuildings.size(); i++) {
            PlacedBuilding building = placedBuildings.get(i);
            float buildingX = building.x - building.texture.getWidth() / 2;
            float buildingY = building.y - building.texture.getHeight() / 2;
            float buildingWidth = building.texture.getWidth();
            float buildingHeight = building.texture.getHeight();

            if (worldtouchX >= buildingX && worldtouchX <= buildingX + buildingWidth && worldtouchY >= buildingY
                    && worldtouchY <= buildingY + buildingHeight) {
                isDragging = true;
                selectedTexture = building.texture;
                dragX = building.x;
                dragY = building.y;

                placedBuildings.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean isHoveringOverTrash(float touchX, float touchY, Rectangle trashCanBounds) {
        // Check if the unprojected position overlaps with the trash can bounds
        return trashCanBounds.contains(touchX, touchY);
    }

    public void stopDrag() {
        if (isDragging && selectedTexture != null) {
            float touchX = dragX;
            float touchY = Gdx.graphics.getHeight() - dragY;
            Vector3 finalworldpos = new Vector3(touchX, touchY, 0);
            camera.unproject(finalworldpos);
            // Add the building's final position to the list of placed buildings
            placedBuildings.add(new PlacedBuilding(selectedTexture, finalworldpos.x, finalworldpos.y));
            System.out.println(finalworldpos.x + "," + finalworldpos.y);
        }
        isDragging = false;
        selectedTexture = null;

    }

    public java.util.List<PlacedBuilding> getplacedBuildings() {
        return Collections.unmodifiableList(placedBuildings);
    }

    public void canceldrag() {
        isDragging = false;
        selectedTexture = null;
    }

    public boolean isDragging() {
        return isDragging;
    }

    public void drawPlacedBuildings(SpriteBatch batch) {
        for (PlacedBuilding building : placedBuildings) {
            batch.draw(building.texture, building.x - building.texture.getWidth() / 2,
                    building.y - building.texture.getHeight() / 2);
        }
    }

    public void drawDraggedBuilding(SpriteBatch batch) {
        if (isDragging && selectedTexture != null) {
            batch.setColor(1, 1, 1, 0.5f); // Set transparency to 50%
            batch.draw(selectedTexture, dragX - selectedTexture.getWidth() / 2,
                    dragY - selectedTexture.getHeight() / 2); // Adjust for center
            batch.setColor(1, 1, 1, 1); // Reset to full opacity
        }
    }

    public static class PlacedBuilding {
        Texture texture;
        float x, y;
        Rectangle bounds;

        public PlacedBuilding(Texture texture, float x, float y) {
            this.texture = texture;
            this.x = x;
            this.y = y;
            this.bounds = new Rectangle(x - texture.getWidth() / 2, y - texture.getHeight() / 2, texture.getWidth(),
                    texture.getHeight());
        }
    }

    public float getDragX() {
        Vector3 worldpos = new Vector3(dragX, Gdx.graphics.getHeight() - dragY, 0);
        camera.unproject(worldpos);
        return dragX;
    }

    public float getDragY() {
        Vector3 worldpos = new Vector3(dragX, Gdx.graphics.getHeight() - dragY, 0);
        camera.unproject(worldpos);
        return dragY;
    }

    public Texture getSelectedTexture() {
        return selectedTexture;
    }
}
