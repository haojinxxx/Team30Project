package TestGrupp.Model;

import java.awt.*;

public final class ScreenDataSingleton {
    private static volatile ScreenDataSingleton instance;
    private final Rectangle mapArea;

    private int width;
    private int height;
    private int bottomBarHeight;

    private ScreenDataSingleton(int width, int height, int bottomBarHeight) {
        this.width = width;
        this.height = height;
        this.bottomBarHeight = bottomBarHeight;
        this.mapArea = new Rectangle(0, 0, width, height - bottomBarHeight);
    }

    public static void initialize(int width, int height, int bottomBarHeight) {
        if (instance == null) {
            synchronized (ScreenDataSingleton.class) {
                if (instance == null) {
                    instance = new ScreenDataSingleton(width, height, bottomBarHeight);
                }
            }
        }
    }

    public static ScreenDataSingleton getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ScreenDataSingleton is not initialized. Call initialize() first.");
        }
        return instance;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBottomBarHeight() {
        return bottomBarHeight;
    }

    public Rectangle getMapArea() {
        return mapArea;
    }
}