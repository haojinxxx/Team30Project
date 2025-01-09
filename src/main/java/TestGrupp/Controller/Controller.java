// src/main/java/TestGrupp/Controller/Controller.java
package TestGrupp.Controller;

import TestGrupp.Model.GameModel;
import TestGrupp.Model.GameObjectDTO;
import TestGrupp.Model.PowerUp;
import TestGrupp.Model.ScreenDataSingleton;
import TestGrupp.Observer.Observer;
import TestGrupp.View.View;

import javax.vecmath.Point2d;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Controller implements Observer {
    private final GameModel gm;
    private final GameLoop loop;
    private final View view;
    private ScreenDataSingleton screenDataSingleton;

    public Controller(GameModel gm, View view) {
        this.gm = gm;
        this.view = view;
        this.screenDataSingleton = ScreenDataSingleton.getInstance();

        InputHandler ih = new InputHandler();
        InputProcessor inputProcessor = new InputProcessor(
                this.gm,
                ih,
                SoundManager.getInstance(),
                ConfigurationLoader.getProperty("PlayerShip.fireRate")
        );

        this.loop = new GameLoop(this.gm, this.view, ih, inputProcessor);

        this.view.setFocusable(true);
        this.view.requestFocusInWindow();
        this.view.addKeyListener(ih);

        // Register the controller as an observer to the model
        this.gm.addObserver(this);
    }

    public void initializeModelWithScreenCenter() {
        int screenWidth = view.getScreenWidth();
        int screenHeight = view.getScreenHeight();

        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;
        Point2d screenCenter = new Point2d(centerX, centerY);

        gm.setScreenCenter(screenCenter);
    }

    public void startGame() {
        initializeModelWithScreenCenter();
        loop.start();
    }

    public void stopGame() {
        loop.stop();
    }

    @Override
    public void update(List<GameObjectDTO> gameObjectDTOs) {
        view.update(gameObjectDTOs);
    }

    @Override
    public void updateScore(int score) {
        view.updateScore(score);
    }

    @Override
    public void updateHealth(int health) {
        view.updateHealth(health);
    }

    @Override
    public void updatePowerUps(List<PowerUp> collectedPowerUps) {
        view.updatePowerUps(collectedPowerUps);
    }
}