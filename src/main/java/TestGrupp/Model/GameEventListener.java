package TestGrupp.Model;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

public interface GameEventListener {

    void onEnemyDestroyed(EnemyShip enemy);


    void onAsteroidDestroyed(Point2d position, int childAsteroids);

    void onProjectileFired(Point2d position, Vector2d velocity, double rotation, double projectileSpeed, int projectileDamage);

    // For every thing a GameObject does that affects the game state, add a method here
}
