package TestGrupp.Model;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;

import TestGrupp.Observer.Observer;
import TestGrupp.Observer.ObserverScore;
import TestGrupp.Observer.Subject;

public class GameModel implements GameEventListener, Subject  {
    private List<GameObject> gameObjects;
    private CollisionManager collisionManager;
    private GameEventListener listener;
    private static PlayerShip playerShip;
    private List<Observer> observers;
    private List<ObserverScore> scoreObservers = new ArrayList<>();
    private Point2d screenCenter;
    private Score score;

    private PowerUp powerup;

    private Properties gameProperties;



    public GameModel() {
        String configPath = "src/main/resources/config.properties";
        try {
            FileInputStream propsInput = new FileInputStream(configPath);
            this.gameProperties = new Properties();
            gameProperties.load(propsInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.collisionManager = new CollisionManager(gameObjects);
        this.gameObjects = new ArrayList<>();
        this.screenCenter = new Point2d(0, 0);
        this.observers = new ArrayList<>();

        Properties properties = getGameProperties();
        int playerWidth = Integer.parseInt(properties.getProperty("player.width"));
        int playerHeight = Integer.parseInt(properties.getProperty("player.height"));
        int asteroidWidth = Integer.parseInt(properties.getProperty("asteroid.width"));
        int asteroidHeight = Integer.parseInt(properties.getProperty("asteroid.height"));


        this.playerShip = new PlayerShip(screenCenter, 0, playerWidth, playerHeight, this);
        this.score = new Score();

        this.powerup= new healthPowerUp(new Point2d(200, 200), this);

        //this.playerShip = new PlayerShip(new Point2d(1920/2,1080/2), 0, 1, 1, this);
        //this.playerShip = new PlayerShip(new Point2d((double) 1920 /2, (double) 1080 /2), 0, 1, 1, this);

        addGameObject(this.playerShip);
        //spawnAsteroid(screenCenter, 2);

        EnemyFactory enemyFactory = new EnemyFactory();
        enemyFactory.registerEnemy("Asteroid", new Asteroid(new Point2d(), 0, asteroidWidth, asteroidHeight, 0.5, 10, 0, this));

        EnemySpawner enemySpawner = new EnemySpawner(this, 1920, 1080, enemyFactory);
        enemySpawner.setSpawnRate("Asteroid", 2000); // Spawn an asteroid every 2000 milliseconds (2 seconds)

        addGameObject(this.powerup);


    }

    public Properties getGameProperties() {
        return gameProperties;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(List<GameObjectDTO> gameObjectDTOs) {
        for (Observer observer : observers) {
            observer.update(gameObjectDTOs); // Pass the DTO list to each observer
        }
    }
    private void notifyScoreObservers(int score) {
        for (ObserverScore observer : scoreObservers) {
            observer.updateScore(score);
        }
    }
    public void addScoreObserver(ObserverScore observer) {
        scoreObservers.add(observer);
    }

    public void update(double deltaTime) {
        score.updateScoreBasedOnTime();
        List<GameObjectDTO> gameObjectDTOs = new ArrayList<>();
        for (GameObject gameObject : new ArrayList<>(gameObjects)) {
            if (!gameObject.isActive()) {
                removeGameObject(gameObject);
                continue;
            }
            gameObject.update(deltaTime);

            TransformComponent transform = gameObject.getTransform();
            String spriteType = determineSpriteType(gameObject); // Custom logic for sprite type
            //System.out.println("GameModel accessing rotation: " + gameObject.getTransform().getRotation());
            gameObjectDTOs.add(new GameObjectDTO(
                    gameObject.getId(),
                    transform.getPosition(),

                    transform.getRotation(),
                    gameObject.isActive(),
                    spriteType
            ));
        }
        collisionManager.update(gameObjects);
        notifyObservers(gameObjectDTOs);
        notifyScoreObservers(score.getScore());
    }

    private String determineSpriteType(GameObject gameObject) {
        if (gameObject instanceof PlayerShip) return "PlayerShip";
        if (gameObject instanceof Asteroid) return "Asteroid";
        if (gameObject instanceof EnemyShip) return "EnemyShip";
        if (gameObject instanceof PowerUp) return "PowerUp";
        if (gameObject instanceof Projectile) {
            if (((Projectile) gameObject).isPlayerProjectile()) return "PlayerProjectile";
            else
                return "EnemyProjectile";
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



    public static PlayerShip getPlayerShip() {
        return playerShip;
    }

    public void setScreenCenter(Point2d center) {
        this.screenCenter = center;
        getPlayerShip().setPos(center);

    }

    public void spawnAsteroid(Point2d position, int childAsteroids) {
        double speed = 0.5;
        int health = 10;

        Asteroid asteroid = new Asteroid(position, 0.5, 0.5, 0.5, speed, health, childAsteroids, this);
        addGameObject(asteroid);
    }

    public void createEnemyShip(Point2d pos, double rotation, double maxSpeed, int health) {
        EnemyShip enemyShip = new EnemyShip(pos, rotation, maxSpeed, health, 0, 50, this);
        addGameObject(enemyShip);
    }

    public void spawnPowerUp(Point2d position) {
        PowerUp powerUp;
        Random random = new Random();
        int randomPowerUp = random.nextInt(2); // Assuming we have 3 types of PowerUps

        switch (randomPowerUp) {
            case 0:
                powerUp = new healthPowerUp(position,  this);
                break;
            case 1:
                powerUp = new shieldPowerUp(position, this);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + randomPowerUp);
        }
        addGameObject(powerUp);
    }

    @Override
    public void onAsteroidDestroyed(Point2d position, int childAsteroids) {
        for (int i = 0; i < childAsteroids; i++) {
            spawnAsteroid(position, childAsteroids);
        }
        score.addScore(50);
        //notifyObservers(); // Notify observers of the event
    }

    @Override
    public void onProjectileFired(Point2d position, Vector2d velocity, double rotation, double speed, int damage, boolean isPlayerProjectile) {
        Projectile projectile = new Projectile(position, rotation,1,1, speed, damage, listener, isPlayerProjectile);
        addGameObject(projectile);
        //notifyObservers(); // Notify observers of the event
    }

    @Override
    public void onEnemyDestroyed(EnemyShip enemy) {
        removeGameObject(enemy);
        score.addScore(100);
        //notifyObservers(); // Notify observers of the event
    }
}