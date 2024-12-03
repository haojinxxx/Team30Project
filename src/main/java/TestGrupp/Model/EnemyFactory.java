package TestGrupp.Model;

import java.util.HashMap;
import java.util.Map;

public class EnemyFactory {
    private final Map<String, Enemy> enemyMap = new HashMap<>();

    public void registerEnemy(String type, Enemy enemy) {
        enemyMap.put(type, enemy);
    }

    public Enemy createEnemy(String type) {
        return enemyMap.get(type);
    }
}
