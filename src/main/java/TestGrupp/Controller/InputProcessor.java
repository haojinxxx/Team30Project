package TestGrupp.Controller;

import TestGrupp.Model.GameModel;

import java.awt.event.KeyEvent;

public class InputProcessor {

    private InputHandler inputHandler;

    private long lastFireTime;
    private long fireCooldown;

    public InputProcessor(GameModel gameModel, InputHandler inputHandler, SoundManager soundManager, long fireCooldown) {
        this.inputHandler = inputHandler;
        this.fireCooldown = fireCooldown;
    }

    public void processInput() {
        processRotationInput();
        processForwardMovement();
        processFireInput();
    }

    private void processRotationInput() {
        if (inputHandler.isKeyPressed(KeyEvent.VK_A)) {
            GameModel.getPlayerShip().rotate(-Math.PI / 30);
        }
        if (inputHandler.isKeyPressed(KeyEvent.VK_D)) {
            GameModel.getPlayerShip().rotate(Math.PI / 30);
        }
    }

    private void processForwardMovement() {
        if (inputHandler.isKeyPressed(KeyEvent.VK_W)) {
            GameModel.getPlayerShip().setMovingForward(true);
            SoundManager.playThrusterSound();
        } else {
            GameModel.getPlayerShip().setMovingForward(false);
            SoundManager.stopThrusterSound();
        }
    }

    private void processFireInput() {
        if (inputHandler.isKeyPressed(KeyEvent.VK_SPACE)) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastFireTime >= fireCooldown) {
                GameModel.getPlayerShip().fire();
                SoundManager.playFireSound();
                lastFireTime = currentTime;
            }
        }
    }
}
