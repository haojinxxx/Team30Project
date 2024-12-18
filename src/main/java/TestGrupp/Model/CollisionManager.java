package TestGrupp.Model;

import java.awt.geom.Rectangle2D;
import java.util.List;

public class CollisionManager {
    public CollisionManager(List<GameObject> collidibleObjects) {

    }

    public void update(List<GameObject> collidibleObjects) {
        // Iterate through all collidable game objects and check for collisions
        for (int i = 0; i < collidibleObjects.size(); i++) {
            GameObject obj1 = collidibleObjects.get(i);
            for (int j = i + 1; j < collidibleObjects.size(); j++) {
                GameObject obj2 = collidibleObjects.get(j);
                if (checkCollision(obj1, obj2)) {
                    handleCollision(obj1, obj2);  // Handle the collision
                }
            }
        }
    }

    private boolean checkCollision(GameObject obj1, GameObject obj2) {
        Rectangle2D.Float box1 = obj1.getTransform().getBoundingBox();
        Rectangle2D.Float box2 = obj2.getTransform().getBoundingBox();
        return box1.intersects(box2);
    }
    public static void handleCollision(GameObject owner, GameObject other) {
        // If the owner is a PlayerShip
        if (owner instanceof PlayerShip) {
            PlayerShip playerShip = (PlayerShip) owner;

            if (other instanceof Asteroid) {
                playerShip.takeDamage(10);
            } else if (other instanceof EnemyShip) {
                EnemyShip enemyShip = (EnemyShip) other;
                enemyShip.takeDamage(5);
            } else if (other instanceof Projectile) {
                Projectile projectile = (Projectile) other;
                playerShip.takeDamage(projectile.getDamage());
            }
            else if (other instanceof PowerUp) {
                PowerUp powerUp = (PowerUp) other;
                if (powerUp instanceof healthPowerUp){
                    playerShip.collectPowerUp(powerUp, 0);
                }
                else if (powerUp instanceof shieldPowerUp){
                    playerShip.collectPowerUp(powerUp, 1);
                }
            }
        }

        // If the owner is an EnemyShip
        else if (owner instanceof EnemyShip) {
            EnemyShip enemyShip = (EnemyShip) owner;

            if (other instanceof PlayerShip) {
                PlayerShip playerShip = (PlayerShip) other;
                playerShip.takeDamage(5);
            } else if (other instanceof Projectile) {
                Projectile projectile = (Projectile) other;
                enemyShip.takeDamage(projectile.getDamage());
            }
        }

        // If the owner is an Asteroid
        else if (owner instanceof Asteroid) {
            Asteroid asteroid = (Asteroid) owner;

            if (other instanceof PlayerShip) {
                PlayerShip playerShip = (PlayerShip) other;
                playerShip.takeDamage(10);
            }
        }
    }
}