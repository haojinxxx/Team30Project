package TestGrupp.Controller;

import TestGrupp.Model.GameModel;
import TestGrupp.View.Panel;

import java.awt.event.KeyEvent;

public class GameLoop implements Runnable {
    private final GameModel gameModel;
    private final Panel view;
    private final InputHandler inputHandler;
    private boolean running;

    public GameLoop(GameModel gameModel, Panel view, InputHandler inputHandler) {
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
        long lastTime = System.currentTimeMillis();
        while (running) {
            long startTime = System.currentTimeMillis();
            long elapsedTime = startTime - lastTime;
            lastTime = startTime;
            float deltaTime = elapsedTime / 1000.0f;

            update(deltaTime);
            render();

            int targetFPS = 60;
            long targetTime = 1000 / targetFPS;
            long waitTime = targetTime - (System.currentTimeMillis() - startTime);
            try {
                if (waitTime > 0) {
                    Thread.sleep(waitTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update(double deltaTime) {
        // Poll the input handler for key states
        if (inputHandler.isKeyPressed(KeyEvent.VK_A)) {
            // Rotate left
            gameModel.getPlayerShip().rotate(-Math.PI / 30);
        }
        if (inputHandler.isKeyPressed(KeyEvent.VK_D)) {
            // Rotate right
            gameModel.getPlayerShip().rotate(Math.PI / 30);
        }
        if (inputHandler.isKeyPressed(KeyEvent.VK_W)) {
            // Move forward

            gameModel.getPlayerShip().setMovingForward(true);
        } else {
            gameModel.getPlayerShip().setMovingForward(false);
        }
        if (inputHandler.isKeyPressed(KeyEvent.VK_S)) {
            // Move backward
            gameModel.getPlayerShip().setMovingBackward(true);
        } else {
            gameModel.getPlayerShip().setMovingBackward(false);
        }
        System.out.println(gameModel.getPlayerShip().getPos());
        // Update the game model (this will update the player ship and all its components)
        gameModel.update(deltaTime);
    }

    private void render() {
        view.render();
        // Here you would call update on the view (if needed)
    }
}