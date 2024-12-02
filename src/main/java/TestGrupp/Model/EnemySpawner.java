package TestGrupp.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class EnemySpawner {
    private final GameModel gameModel;
    private final int screenWidth;
    private final int screenHeight;
    private final Random random;
    private final EnemyFactory enemyFactory;
    private final Map<String, Integer> spawnRates;
    private final Map<String, Timer> timers;

    // Constructor
    public EnemySpawner(GameModel gameModel, int screenWidth, int screenHeight, EnemyFactory enemyFactory) {
        this.gameModel = gameModel;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.random = new Random();
        this.enemyFactory = enemyFactory;
        this.spawnRates = new HashMap<>();
        this.timers = new HashMap<>();
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
        double x, y;
        int edge = random.nextInt(4);
        switch (edge) {
            case 0: // Top edge
                x = random.nextDouble() * screenWidth;
                y = 0;
                break;
            case 1: // Bottom edge
                x = random.nextDouble() * screenWidth;
                y = screenHeight;
                break;
            case 2: // Left edge
                x = 0;
                y = random.nextDouble() * screenHeight;
                break;
            case 3: // Right edge
                x = screenWidth;
                y = random.nextDouble() * screenHeight;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + edge);
        }
        Enemy enemy = enemyFactory.createEnemy(enemyType);
        if (enemy != null) {
            enemy.spawn(gameModel, x, y);
        }
    }
}