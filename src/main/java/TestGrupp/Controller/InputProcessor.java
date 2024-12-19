package TestGrupp.Controller;

import TestGrupp.Model.GameModel;

import java.awt.event.KeyEvent;

public class InputProcessor {

    private final SoundManager soundManager;
    private final InputHandler inputHandler;
    private final GameModel gameModel;

    private long lastFireTime;
    private final long fireCooldown;

    public InputProcessor(GameModel gameModel, InputHandler inputHandler, SoundManager soundManager, long fireCooldown) {
        this.gameModel = gameModel;
        this.inputHandler = inputHandler;
        this.fireCooldown = fireCooldown;
        this.soundManager = soundManager;
    }

    public void processInput() {
        processRotationInput();
        processForwardMovement();
        processFireInput();
        processPowerUpInput();
    }

    private void processRotationInput() {
        if (inputHandler.isKeyPressed(KeyEvent.VK_A)) {
            gameModel.getPlayerShip().rotate(-Math.PI / 30);
        }
        if (inputHandler.isKeyPressed(KeyEvent.VK_D)) {
            gameModel.getPlayerShip().rotate(Math.PI / 30);
        }
    }

    private void processForwardMovement() {
        if (inputHandler.isKeyPressed(KeyEvent.VK_W)) {
            gameModel.getPlayerShip().setMovingForward(true);
            soundManager.playThrusterSound();
        } else {
            gameModel.getPlayerShip().setMovingForward(false);
            soundManager.stopThrusterSound();
        }
    }

    private void processFireInput() {
        if (inputHandler.isKeyPressed(KeyEvent.VK_SPACE)) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastFireTime >= fireCooldown) {
                gameModel.getPlayerShip().fire();
                soundManager.playFireSound();
                lastFireTime = currentTime;
            }
        }
    }

    private void processPowerUpInput() {
        if (inputHandler.isKeyPressed(KeyEvent.VK_H)) {
            gameModel.getPlayerShip().activateStoredPowerUp(0);
        }
        if (inputHandler.isKeyPressed(KeyEvent.VK_J)) {
            gameModel.getPlayerShip().activateStoredPowerUp(1);
        }
        if (inputHandler.isKeyPressed(KeyEvent.VK_K)) {
            gameModel.getPlayerShip().activateStoredPowerUp(2);
        }
        if (inputHandler.isKeyPressed(KeyEvent.VK_L)) {
            gameModel.getPlayerShip().activateStoredPowerUp(3);
        }
    }

}
