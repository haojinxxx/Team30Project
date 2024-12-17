package TestGrupp.Controller;

import TestGrupp.Model.GameModel;
import TestGrupp.View.View;

import javax.vecmath.Point2d;

public class Controller {
    private final InputProcessor inputProcessor;
    private GameModel gm;
    private InputHandler ih;
    private GameLoop loop;
    private View view;

    private Point2d screenCenter;

    public Controller(GameModel gm, View view) {
        this.gm = gm;
        this.view = view;

        this.ih = new InputHandler();
        this.inputProcessor = new InputProcessor(
                this.gm,
                this.ih,
                SoundManager.getInstance(),  // Assuming you make SoundManager a singleton
                500  // Fire cooldown in milliseconds
        );

        this.loop = new GameLoop(this.gm, this.view, this.ih, this.inputProcessor);


        // Set up the panel to listen for key events
        this.view.setFocusable(true);
        this.view.requestFocusInWindow();
        this.view.addKeyListener(this.ih);

        // Register the panel as an observer
        this.gm.addObserver(this.view);
    }

    public void initializeModelWithScreenCenter() {
        int screenWidth = view.getScreenWidth(); // Add a getter in the View
        int screenHeight = view.getScreenHeight(); // Add a getter in the View

        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;
        Point2d screenCenter = new Point2d(centerX, centerY);

        gm.setScreenCenter(screenCenter); // Add this method in GameModel
    }

    // Method to start the game loop
    public void startGame() {
        initializeModelWithScreenCenter();
        SoundManager.initialize();
        loop.start();  // This calls the start() method on the GameLoop instance
    }

    // Method to stop the game loop (if needed)
    public void stopGame() {
        loop.stop();
    }
}