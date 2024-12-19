package TestGrupp.Model;

import javax.vecmath.Point2d;
import java.util.*;

public class EnemySpawner {
    private final GameEventListener gameEventListener;
    private final int screenWidth;
    private final int screenHeight;
    private final Random random;
    private final EnemyFactory enemyFactory;
    private final Map<String, Integer> spawnRates;
    private final Map<String, Timer> timers;
    private ArrayList<GameObject> spawnedEnemies;


    // Constructor
    public EnemySpawner(int screenWidth, int screenHeight, GameEventListener gameEventListener) {
        this.gameEventListener = gameEventListener;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.random = new Random();
        this.enemyFactory = new EnemyFactory();
        this.spawnRates = new HashMap<>();
        this.timers = new HashMap<>();
        this.spawnedEnemies = new ArrayList<>();
    }

    // Set spawn rate for enemy type
    public void setSpawnRate(String enemyType, int spawnRate) {
        spawnRates.put(enemyType, spawnRate);
        if (timers.containsKey(enemyType)) {
            timers.get(enemyType).cancel();
        }
        Timer timer = new Timer();
        timers.put(enemyType, timer);
        startSpawning(enemyType, spawnRate, timer);
    }

    // Start spawning enemies
    private void startSpawning(String enemyType, int spawnRate, Timer timer) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                spawnEnemy(enemyType);
            }
        }, 0, spawnRate);
    }

    // Spawn enemy on a randomly chosen edge on the screen with a random position on that edge
    private void spawnEnemy(String enemyType) {
        Point2d pos = new Point2d();
        int edge = random.nextInt(4);
        switch (edge) {
            case 0: // Top edge
                pos.setX(random.nextDouble() * screenWidth);
                pos.setY(0);
                break;
            case 1: // Bottom edge
                pos.setX(random.nextDouble() * screenWidth);
                pos.setY(screenHeight);
                break;
            case 2: // Left edge
                pos.setX(0);
                pos.setY(random.nextDouble() * screenHeight);
                break;
            case 3: // Right edge
                pos.setX(screenWidth);
                pos.setY(random.nextDouble() * screenHeight);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + edge);
        }
        GameObject enemy = enemyFactory.createEnemy(enemyType, gameEventListener);
        if (enemy != null) {
            enemy.getTransform().setPosition(pos);
            spawnedEnemies.add(enemy);
        } else {
            throw new IllegalStateException("Enemy could not be spawned");
        }
    }

    // Return the list of spawned enemies
    public List<GameObject> getSpawnedEnemies() {
        return new ArrayList<>(spawnedEnemies);
    }

}