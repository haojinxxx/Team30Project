package TestGrupp.Model;

import javax.vecmath.Point2d;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EnemyFactory {
    private final Map<String, Supplier <Enemy>> enemyMap = new HashMap<>();

    public void registerEnemy(String type, Supplier <Enemy> supplier) {
        enemyMap.put(type, supplier);
    }

    public Enemy createEnemy(String type, Point2d position) {
        Supplier<Enemy> supplier = enemyMap.get(type);
        if (supplier != null) {
            Enemy enemy = supplier.get();
            if (enemy instanceof GameObject) {
                ((GameObject) enemy).getTransform().setPosition(position);
            }
            if (enemy instanceof Asteroid) {
                ((Asteroid) enemy).getPhysics().setSpeed(2.0); // Increase the speed value
            }
            return enemy;
        }
        throw new IllegalArgumentException("Unknown enemy type: " + type);
    }


}
