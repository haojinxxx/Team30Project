package TestGrupp.Model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private List<GameObject> gameObjects;

    public GameModel() {
        this.gameObjects = new ArrayList<>();
    }

    public void addGameObject(GameObject gameObject) {
        this.gameObjects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        this.gameObjects.remove(gameObject);
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void update(double deltaTime) {
        for (GameObject gameObject : gameObjects) {
            if (!gameObject.isActive()) {
                removeGameObject(gameObject);
            }

            gameObject.update(deltaTime);

            // When we decide on when we want to spawn enemies and such (like a check for how many enemies there currently are and if we want more
            // we can add that here.
        }
    }

    public void createAsteroid(double x, double y, double rotation, double scaleX, double scaleY, double speed, int health, int childAsteroids) {
        Asteroid asteroid = new Asteroid(x, y, rotation, scaleX, scaleY, speed, health, childAsteroids);
        addGameObject(asteroid);
    }

    public void createEnemyShip(double x, double y, double rotation, double maxSpeed, int health) {
        EnemyShip enemyShip = new EnemyShip(x, y, rotation, maxSpeed, health); // Adjust parameters as needed
        addGameObject(enemyShip);
    }


}