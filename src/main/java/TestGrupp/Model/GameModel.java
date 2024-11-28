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

    // Method to create a new Asteroid
    public void createAsteroid(float x, float y, float rotation, float scaleX, float scaleY, double speed, int health, int childAsteroids) {
        Asteroid asteroid = new Asteroid(x, y, rotation, scaleX, scaleY, speed, health, childAsteroids);
        addGameObject(asteroid);
    }
}