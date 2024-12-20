package TestGrupp.Model;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;
import java.io.FileInputStream;
import java.util.*;

import TestGrupp.Observer.Observer;
import TestGrupp.Observer.Subject;

public class GameModel implements GameEventListener, Subject  {
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

        this.powerup= new healthPowerUp(new Point2d(700, 700), this);

        ScreenDataSingleton screenData = ScreenDataSingleton.getInstance();

        addGameObject(playerShip);


        // Initialize the EnemySpawner
        this.enemySpawner = new EnemySpawner(screenData.getWidth(), screenData.getHeight(), this);
        this.enemySpawner.setSpawnRate("Asteroid", 2000);   //Start spawning asteroids
        this.enemySpawner.setSpawnRate("EnemyShip", 5000); //Start spawning enemy ships



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
    public void notifyObservers(List<GameObjectDTO> gameObjectDTOs, int score, Map<Integer, PowerUp> collectedPowerUps) {
        for (Observer observer : observers) {
            observer.update(gameObjectDTOs);
            observer.updateScore(score);// Pass the DTO list to each observer
            observer.updateHealth(playerShip.getHealth());
            observer.updatePowerUps(new ArrayList<>(collectedPowerUps.values()));
        }
    }

    public void resetGame() {
        // Clear all game objects
        gameObjects.clear();

        // Reset the score
        score.resetScore();

        // Respawn the player at the initial position
        playerShip = new PlayerShip(screenCenter, 0, this);
        playerShip.setPos(screenCenter); // Set player position to center
        addGameObject(playerShip);

        // Reinitialize the EnemySpawner
        ScreenDataSingleton screenData = ScreenDataSingleton.getInstance();
        enemySpawner = new EnemySpawner(screenData.getWidth(), screenData.getHeight(), this);
        enemySpawner.setSpawnRate("Asteroid", 2000);
        enemySpawner.setSpawnRate("EnemyShip", 5000);

        // Notify observers about the reset state
        notifyObservers(new ArrayList<>(), score.getScore(), new HashMap<>());
    }


    // Check if any enemies have been spawned and add them to the gameObjects list
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

        checkAndAddSpawnedEnemies(Asteroid.class);
        checkAndAddSpawnedEnemies(EnemyShip.class);




        collisionManager.update(gameObjects);
        notifyObservers(gameObjectDTOs, score.getScore(), playerShip.getCollectedPowerUps());
        score.updateScoreBasedOnTime();

        notifyObservers(gameObjectDTOs, score.getScore(), playerShip.getCollectedPowerUps());
  


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




    public void spawnPowerUp(Point2d position) {
        /*PowerUp powerUp;
        Random random = new Random();
        int randomPowerUp = random.nextInt(1); // Assuming we have 3 types of PowerUps

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
        /*
        for (int i = 0; i < childAsteroids; i++) {
            spawnAsteroid(position, childAsteroids);
        }
        score.addScore(50);
        //notifyObservers(); // Notify observers of the event

         */
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
    public void onPlayerHealthChanged(int health) {
        // Do whatever, this is placeholder code
        System.out.println("Player health changed to: " + health);
        //notifyObservers(); // Notify observers of the event
    }

    @Override
    public void onPlayerDestroyed() {
        // Do whatever, this is placeholder code
        resetGame();
        System.out.println("Player destroyed");
        //notifyObservers(); // Notify observers of the event
    }
}