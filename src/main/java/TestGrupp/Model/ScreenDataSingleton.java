package TestGrupp.Model;

public final class ScreenDataSingleton {
    private static volatile ScreenDataSingleton instance;

    private int width;
    private int height;
    private int bottomBarHeight;



    private ScreenDataSingleton(int width, int height, int bottomBarHeight) {
        this.width = width;
        this.height = height;
        this.bottomBarHeight = bottomBarHeight;

    }

    public static ScreenDataSingleton getInstance(int width, int height, int bottomBarHeight) {

        if (instance == null) {
            synchronized (ScreenDataSingleton.class) {

                if (instance == null) {
                    instance = new ScreenDataSingleton(width, height, bottomBarHeight);
                }
            }
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
}
