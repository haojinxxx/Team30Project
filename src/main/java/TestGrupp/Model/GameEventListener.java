package TestGrupp.Model;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;
import java.util.List;

public interface GameEventListener {
    void onAsteroidDestroyed(Point2d position, int childAsteroids);
    void onProjectileFired(Point2d position, Vector2d velocity, double rotation, double speed, int damage, boolean isPlayerProjectile);
    void onEnemyDestroyed(EnemyShip enemy);
    void onPowerUpCollected(PowerUp powerUp);
    void onPlayerDestroyed();
    void onPlayerHealthChanged(int health);


    Point2d getPlayerPosition();
}