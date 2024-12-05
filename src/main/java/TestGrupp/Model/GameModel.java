package TestGrupp.Model;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;
import java.util.ArrayList;
import java.util.List;

public class GameModel implements GameEventListener {
    private List<GameObject> gameObjects;

    private CollisionManager collisionManager;

    private PlayerShip playerShip;

    private Score score;

    public GameModel() {
        this.gameObjects = new ArrayList<>();
        this.playerShip = new PlayerShip(new Point2d(0, 0), 0, 1, 1, this);
        this.score = new Score();
        addGameObject(playerShip);
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
        for (GameObject gameObject : new ArrayList<>(gameObjects)) {
            if (!gameObject.isActive()) {
                removeGameObject(gameObject);
            }
            gameObject.update(deltaTime);


        }
        collisionManager.update(gameObjects); // When this has been properly implemented only the collidible objects in the game will be sent to the collision manager
        score.updateScoreBasedOnTime(); //Update the score based on time
    }


    public PlayerShip getPlayerShip() {
        return playerShip;
    }

    public void spawnAsteroid(Point2d position, int childAsteroids) {
        double speed = 0.5;
        int health = 10;

        Asteroid asteroid = new Asteroid(position, 0.5, 0.5, 0.5, speed, health, childAsteroids, this);
        addGameObject(asteroid);
    }

    public void createEnemyShip(double x, double y, double rotation, double maxSpeed, int health) {
        //EnemyShip enemyShip = new EnemyShip(x, y, rotation, maxSpeed, health); // Adjust parameters as needed
        //addGameObject(enemyShip);
    }

    public void onAsteroidDestroyed(Point2d position, int childAsteroids) {
        for (int i = 0; i < childAsteroids; i++) {
            spawnAsteroid(position, childAsteroids);
        }
        score.addScore(50);
    }

    public void onProjectileFired(Point2d position, Vector2d velocity, double rotation, double speed, int damage) {
        Projectile projectile = new Projectile(position,rotation, velocity,1,1 ,speed, damage, this);
        addGameObject(projectile);
    }

    public void onEnemyDestroyed(EnemyShip enemy) {
        removeGameObject(enemy);
        score.addScore(100);
    }


}