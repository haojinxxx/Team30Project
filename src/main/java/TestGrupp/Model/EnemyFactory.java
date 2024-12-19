package TestGrupp.Model;

import javax.vecmath.Point2d;
import java.util.HashMap;
import java.util.Map;

public class EnemyFactory {
    private final Map<String, Enemy> enemyMap = new HashMap<>();

    public void registerEnemy(String type, Enemy enemy) {
        enemyMap.put(type, enemy);
    }

    /*
    public Enemy createEnemy(String type) {
        return enemyMap.get(type);
    }

     */

    public GameObject createEnemy(String type, GameEventListener gameEventListener) {
        switch (type) {
            case "Asteroid":
                Asteroid asteroid = new Asteroid(new Point2d(), 0,1,1,0.5,10,0,gameEventListener);
                //registerEnemy("Asteroid", asteroid);
                return asteroid;
            default:
                throw new IllegalArgumentException("Invalid enemy type: " + type);
        }

    }
}
