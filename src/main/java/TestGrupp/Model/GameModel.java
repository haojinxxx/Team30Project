package TestGrupp.Model;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import TestGrupp.Observer.Observer;
import TestGrupp.Observer.Subject;

public class GameModel implements GameEventListener, Subject  {
    private List<GameObject> gameObjects;
    private CollisionManager collisionManager;
    private GameEventListener listener;
    private static PlayerShip playerShip;
    private List<Observer> observers;
    private Point2d screenCenter;
    private Score score;
    private Random random;

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

        this.gameObjects = new ArrayList<>();
        this.screenCenter = new Point2d(0, 0);
        this.observers = new ArrayList<>();
        this.collisionManager = new CollisionManager(gameObjects);

        Properties properties = getGameProperties();
        int playerWidth = Integer.parseInt(properties.getProperty("PlayerShip.width"));
        int playerHeight = Integer.parseInt(properties.getProperty("PlayerShip.height"));
        this.playerShip = new PlayerShip(screenCenter, 0, this);
        this.score = new Score();

        this.powerup= new healthPowerUp(new Point2d(200, 200), this);

        //this.playerShip = new PlayerShip(new Point2d(1920/2,1080/2), 0, 1, 1, this);
        //this.playerShip = new PlayerShip(new Point2d((double) 1920 /2, (double) 1080 /2), 0, 1, 1, this);

        addGameObject(this.playerShip);
        //spawnAsteroid(screenCenter, 2);

        EnemyFactory enemyFactory = new EnemyFactory();
        enemyFactory.registerEnemy("Asteroid", new Asteroid(new Point2d(), 0, 1, 1, 2, this));
        enemyFactory.registerEnemy("EnemyShip", new EnemyShip(new Point2d(), 0, 800, 10, 0, 400, this));

        EnemySpawner enemySpawner = new EnemySpawner(this, 1920, 1080, enemyFactory);
        enemySpawner.setSpawnRate("Asteroid", 2000); // Spawn an asteroid every 2000 milliseconds (2 seconds)
        enemySpawner.setSpawnRate("EnemyShip", 5000); // Spawn an enemy ship every 5000 milliseconds (5 seconds)

        EnemyFactory enemyFactory2 = new EnemyFactory();
        enemyFactory2.registerEnemy("EnemyShip", new EnemyShip(new Point2d(), 0, 800, 50, 20, 50, this));

        EnemySpawner enemySpawner2 = new EnemySpawner(this, 1920, 1080, enemyFactory2);
        enemySpawner2.setSpawnRate("EnemyShip", 5000); // Spawn an asteroid every 2000 milliseconds (2 seconds)
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
    public void notifyObservers(List<GameObjectDTO> gameObjectDTOs, int score) {
        for (Observer observer : observers) {
            observer.update(gameObjectDTOs);
            observer.updateScore(score);// Pass the DTO list to each observer
        }
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
        notifyObservers(gameObjectDTOs, score.getScore());
        score.updateScoreBasedOnTime();

        notifyObservers(gameObjectDTOs, score.getScore());
  


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



    public PlayerShip getPlayerShip() {
        return playerShip;
    }

    public Point2d getPlayerPosition() {
        return playerShip.getTransform().getPosition();
    }

    public void setScreenCenter(Point2d center) {
        this.screenCenter = center;
        getPlayerShip().setPos(center);

    }

    public void spawnAsteroid(Point2d position, int childAsteroids) {
        double speed = 300;
        int health = 10;
        // random rotation
        double rotationAngle = Math.random() * 360;
        Asteroid asteroid = new Asteroid(position, rotationAngle, speed, health, childAsteroids, this);
        addGameObject(asteroid);
    }

    public void createEnemyShip(Point2d pos, double rotation, double maxSpeed, int health) {
        EnemyShip enemyShip = new EnemyShip(pos, rotation, maxSpeed, health, 10, 200, this);

        addGameObject(enemyShip);
    }

    public void spawnPowerUp(Point2d position) {
        /*PowerUp powerUp;
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
        }*/
        PowerUp powerUp = new healthPowerUp(position, this);
        addGameObject(powerUp);
    }
    private Random random2 = new Random();

    private boolean shouldSpawnPowerUp() {
        int chance = 20; // 20% chance to spawn a random powerup
        return random2.nextInt(100) < chance;
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

        if (shouldSpawnPowerUp()) {
            spawnPowerUp(enemy.getTransform().getPosition());
        }
        //notifyObservers(); // Notify observers of the event
    }

    @Override
    public  void onPowerUpCollected(PowerUp powerUp) {
        removeGameObject(powerUp);
        //notifyObservers();
    }

    @Override
    public void onPlayerDestroyed() {
        // Do whatever, this is placeholder code

        System.out.println("Player destroyed");
        //notifyObservers(); // Notify observers of the event
    }
}