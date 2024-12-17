package TestGrupp.Controller;

import TestGrupp.Model.GameModel;
import TestGrupp.View.View;

import java.awt.event.KeyEvent;

public class GameLoop implements Runnable {
    private final GameModel gameModel;
    private final View view;
    private final InputProcessor inputProcessor;
    private boolean running;
    private final int targetFPS = 60;       // Target frames per second
    private final int updatesPerSecond = 120; // Target updates per second (logic ticks)
    private final double updateInterval = 1000.0 / updatesPerSecond; // Time per update in milliseconds


    public GameLoop(GameModel gameModel, View view, InputHandler inputHandler, InputProcessor inputProcessor) {
        this.gameModel = gameModel;
        this.view = view;
        this.inputProcessor = inputProcessor;
        this.running = false;
    }

    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        long lastUpdateTime = System.currentTimeMillis();
        long lastRenderTime = System.currentTimeMillis();
        double timeSinceLastUpdate = 0;

        while (running) {
            long currentTime = System.currentTimeMillis();
            timeSinceLastUpdate += currentTime - lastUpdateTime;
            lastUpdateTime = currentTime;

            // Process updates as needed
            while (timeSinceLastUpdate >= updateInterval) {
                update(updateInterval / 1000.0); // Update game logic
                timeSinceLastUpdate -= updateInterval;
            }

            // Render at the target FPS
            if (currentTime - lastRenderTime >= 1000.0 / targetFPS) {
                render();
                lastRenderTime = currentTime;
            }

            // Sleep to reduce CPU usage
            try {
                Thread.sleep(1); // Small sleep to allow other threads to run
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates the game state based on the time passed since the last update.
     *
     * @param deltaTime Time passed since the last update in seconds
     */
    private void update(double deltaTime) {
        inputProcessor.processInput(); // Process input before updating game model
        gameModel.update(deltaTime);
    }



    /**
     * Renders the game state by explicitly triggering the View to redraw.
     */
    private void render() {
        view.render(); // Explicitly triggers the View to redraw
    }
}
