package TestGrupp.Controller;

import TestGrupp.Model.GameModel;
import TestGrupp.View.View;

import java.awt.event.KeyEvent;

public class GameLoop implements Runnable {
    private final GameModel gameModel;
    private final View view;
    private final InputHandler inputHandler;
    private boolean running;
    private final int targetFPS = 60;       // Target frames per second
    private final int updatesPerSecond = 120; // Target updates per second (logic ticks)
    private final double updateInterval = 1000.0 / updatesPerSecond; // Time per update in milliseconds

    public GameLoop(GameModel gameModel, View view, InputHandler inputHandler) {
        this.gameModel = gameModel;
        this.view = view;
        this.inputHandler = inputHandler;
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
     * Updates the game state and handles user input.
     */
    private void update(double deltaTime) {
        handleInput(); // Handle user input
        gameModel.update(deltaTime); // Update the game logic
        // No need to directly notify the view; the observer pattern takes care of it
    }

    /**
     * Handles user input via the InputHandler.
     */
    private void handleInput() {
        // Rotation input (A/D keys for left/right rotation)
        if (inputHandler.isKeyPressed(KeyEvent.VK_A)) {
            gameModel.getPlayerShip().rotate(-Math.PI / 30); // Rotate left (counterclockwise)
        }
        if (inputHandler.isKeyPressed(KeyEvent.VK_D)) {
            gameModel.getPlayerShip().rotate(Math.PI / 30); // Rotate right (clockwise)
        }

        // Forward movement input (W key for forward)
        if (inputHandler.isKeyPressed(KeyEvent.VK_W)) {
            gameModel.getPlayerShip().setMovingForward(true);  // Continuously move forward while key is held
        } else {
            gameModel.getPlayerShip().setMovingForward(false); // Stop moving forward when key is released
        }

        // Backward movement input (S key for backward)
        if (inputHandler.isKeyPressed(KeyEvent.VK_S)) {
            gameModel.getPlayerShip().setMovingBackward(true);  // Continuously move backward while key is held
        } else {
            gameModel.getPlayerShip().setMovingBackward(false); // Stop moving backward when key is released
        }
    }


    /**
     * Renders the game state by explicitly triggering the View to redraw.
     */
    private void render() {
        view.render(); // Explicitly triggers the View to redraw
    }
}
