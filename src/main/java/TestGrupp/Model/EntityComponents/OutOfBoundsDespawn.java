package TestGrupp.Model.EntityComponents;

import TestGrupp.Model.GameObject;
import TestGrupp.Model.ScreenDataSingleton;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class OutOfBoundsDespawn {
    private final ScreenDataSingleton screenDataSingleton;

    public OutOfBoundsDespawn() {
        this.screenDataSingleton = ScreenDataSingleton.getInstance();
    }

    public void update(GameObject gameObject) {
        if (!isOnMap(gameObject)) {
            gameObject.setActive(false);
        }
    }

    private boolean isOnMap(GameObject gameObject) {
        Rectangle mapArea = screenDataSingleton.getMapArea();
        Rectangle2D.Float boundingBox = gameObject.getTransform().getBoundingBox();
        return mapArea.intersects(boundingBox);
    }
}