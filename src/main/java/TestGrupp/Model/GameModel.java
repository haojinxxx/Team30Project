// src/main/java/TestGrupp/Model/GameModel.java
package TestGrupp.Model;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;
import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;

import TestGrupp.Observer.Observer;

public class GameModel implements GameEventListener {
    private List<GameObject> gameObjects;
    private CollisionManager collisionManager;
    private GameEventListener listener;
    private PlayerShip playerShip;
    private List<Observer> observers;
    private Point2d screenCenter;
    private Score score;
    private Random random;
    private PowerUp powerup;
    private Properties gameProperties;
    private EnemySpawner enemySpawner;

    public GameModel() {
        String configPath = "src/main/resources/config.properties";
        try {
            FileInputStream propsInput = new FileInputStream(configPath);
            this.gameProperties = new Properties();
            gameProperties.load(propsInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.gameObjects = new ArrayList<>();
        this.screenCenter = new Point2d(0, 0);
        this.observers = new ArrayList<>();
        this.collisionManager = new CollisionManager(gameObjects);

        Properties properties = getGameProperties();
        int playerWidth = Integer.parseInt(properties.getProperty("PlayerShip.width"));
        int playerHeight = Integer.parseInt(properties.getProperty("PlayerShip.height"));
        this.playerShip = new PlayerShip(screenCenter, 0, this);
        this.score = new Score();

        this.powerup = new healthPowerUp(new Point2d(700, 700), this);

        ScreenDataSingleton screenData = ScreenDataSingleton.getInstance();

        addGameObject(playerShip);

        // Initialize the EnemySpawner
        this.enemySpawner = new EnemySpawner(screenData.getWidth(), screenData.getHeight(), this);
        this.enemySpawner.setSpawnRate("Asteroid", 2000);   // Start spawning asteroids
        this.enemySpawner.setSpawnRate("EnemyShip", 5000); // Start spawning enemy ships
    }

    public Properties getGameProperties() {
        return gameProperties;
    }

    public int getScore() {
        return score.getScore();
    }

    public void resetGame() {
        // Clear all game objects
        gameObjects.clear();

        // Reset the score
        score.resetScore();

        // Respawn the player at the initial position
        Point2d startPos = new Point2d(screenCenter);
        this.playerShip = new PlayerShip(startPos, 0, this);
        System.out.printf("Screencenter: %s\n", screenCenter);
        addGameObject(playerShip);

        // Reinitialize the EnemySpawner
        ScreenDataSingleton screenData = ScreenDataSingleton.getInstance();
        enemySpawner = new EnemySpawner(screenData.getWidth(), screenData.getHeight(), this);
        enemySpawner.setSpawnRate("Asteroid", 2000);
        enemySpawner.setSpawnRate("EnemyShip", 5000);

        // Notify observers about the reset state
        List<GameObjectDTO> gameObjectDTOS = createGameObjectDTOs();
        notifyObservers(gameObjectDTOS);
    }

    private void checkAndAddSpawnedEnemies(Class<? extends GameObject> enemyClass) {
        List<GameObject> spawnedEnemies = enemySpawner.getSpawnedEnemies();
        for (GameObject enemy : new ArrayList<>(spawnedEnemies)) {
            if (enemyClass.isInstance(enemy)) {
                if (!enemy.isActive()) {
                    removeGameObject(enemy);
                    spawnedEnemies.remove(enemy);
                    System.out.println(enemyClass.getSimpleName().toLowerCase() + " id: " + enemy.getId() + " despawned");
                } else if (!gameObjects.contains(enemy)) {
                    addGameObject(enemy);
                    System.out.println(enemyClass.getSimpleName().toLowerCase() + " id: " + enemy.getId() + " spawned");
                }
            }
        }
    }

    private List<GameObjectDTO> createGameObjectDTOs() {
        return gameObjects.stream()
                .map(gameObject -> new GameObjectDTO(
                        gameObject.getId(),
                        gameObject.getTransform().getPosition(),
                        gameObject.getTransform().getRotation(),
                        gameObject.isActive(),
                        determineSpriteType(gameObject)
                ))
                .collect(Collectors.toList());
    }
    public void update(double deltaTime) {
        score.updateScoreBasedOnTime();
        for (GameObject gameObject : new ArrayList<>(gameObjects)) {
            if (!gameObject.isActive()) {
                removeGameObject(gameObject);
                continue;
            }
            gameObject.update(deltaTime);
        }

        checkAndAddSpawnedEnemies(Asteroid.class);
        checkAndAddSpawnedEnemies(EnemyShip.class);

        collisionManager.update(gameObjects);

        List<GameObjectDTO> gameObjectDTOs = createGameObjectDTOs();
        notifyObservers(gameObjectDTOs);
    }

    public String determineSpriteType(GameObject gameObject) {
        if (gameObject instanceof PlayerShip) return "PlayerShip";
        if (gameObject instanceof Asteroid) return "Asteroid";
        if (gameObject instanceof EnemyShip) return "EnemyShip";
        if (gameObject instanceof PowerUp) return "PowerUp";
        if (gameObject instanceof Projectile) {
            if (((Projectile) gameObject).isPlayerProjectile()) return "PlayerProjectile";
            else return "EnemyProjectile";
        }

        throw new IllegalArgumentException("Unknown GameObject type");
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

    public PlayerShip getPlayerShip() {
        return playerShip;
    }

    public Point2d getPlayerPosition() {
        return playerShip.getTransform().getPosition();
    }

    public void setScreenCenter(Point2d center) {
        this.screenCenter = center;
        Point2d startPos = new Point2d(center);
        getPlayerShip().setPos(startPos);
    }

    public void spawnPowerUp(Point2d position) {
        PowerUp powerUp = new healthPowerUp(position, this);
        addGameObject(powerUp);
    }

    private boolean shouldSpawnPowerUp() {
        int chance = 20; // 20% chance to spawn a random powerup
        return new Random().nextInt(100) < chance;
    }

    @Override
    public void onAsteroidDestroyed(Point2d position, int childAsteroids) {
        // Handle asteroid destruction
    }

    @Override
    public void onProjectileFired(Point2d position, Vector2d velocity, double rotation, double speed, int damage, boolean isPlayerProjectile) {
        Projectile projectile = new Projectile(position, rotation, 1, 1, speed, damage, listener, isPlayerProjectile);
        addGameObject(projectile);
    }

    @Override
    public void onEnemyDestroyed(EnemyShip enemy) {
        removeGameObject(enemy);
        score.addScore(100);

        if (shouldSpawnPowerUp()) {
            spawnPowerUp(enemy.getTransform().getPosition());
        }
    }

    @Override
    public void onPowerUpCollected(PowerUp powerUp) {
        removeGameObject(powerUp);
    }

    @Override
    public void onPlayerHealthChanged(int health) {
        // Handle player health change
    }

    @Override
    public void onPlayerDestroyed() {
        resetGame();
        System.out.println("Player destroyed");
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(List<GameObjectDTO> gameObjectDTOs) {
        for (Observer observer : observers) {
            observer.update(gameObjectDTOs);
            observer.updateScore(score.getScore());
            observer.updateHealth(playerShip.getHealth());
            observer.updatePowerUps(new ArrayList<>(playerShip.getCollectedPowerUps().values()));
        }
    }
}