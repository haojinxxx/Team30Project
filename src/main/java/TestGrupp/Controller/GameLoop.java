package TestGrupp.Controller;

import TestGrupp.Model.GameModel;


// I've "borrowed" boilerplate gameloop code
public class GameLoop implements Runnable {
    private final GameModel gameModel;
    private boolean running;

    public GameLoop(GameModel gameModel) {
        this.gameModel = gameModel;
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
        gameModel.update(deltaTime);
    }

    private void render() {
        // Here we should call update on the view
    }
}