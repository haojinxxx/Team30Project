package TestGrupp.Model;

import java.awt.geom.Rectangle2D;

public class CollisionComponent {
    private GameObject owner;

    public CollisionComponent(GameObject owner) {
        this.owner = owner;
    }

    public boolean checkCollision(GameObject other) {
        Rectangle2D.Float thisBoundingBox = owner.getTransform().getBoundingBox();
        Rectangle2D.Float otherBoundingBox = other.getTransform().getBoundingBox();
        return thisBoundingBox.intersects(otherBoundingBox);
    }

    public void handleCollision(GameObject other) {
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
                playerShip.takeDamage(projectile.getDamage()); // Example damage value for Projectiles
            }
        }

        // If the owner is an EnemyShip
        else if (owner instanceof EnemyShip) {
            EnemyShip enemyShip = (EnemyShip) owner;

            if (other instanceof PlayerShip) {
                PlayerShip playerShip = (PlayerShip) other;
                playerShip.takeDamage(5); // Example damage value for PlayerShip
            } else if (other instanceof Projectile) {
                Projectile projectile = (Projectile) other;
                enemyShip.takeDamage(projectile.getDamage()); // Example damage value for Projectiles
            }
        }

        // If the owner is an Asteroid
        else if (owner instanceof Asteroid) {
            Asteroid asteroid = (Asteroid) owner;

            if (other instanceof PlayerShip) {
                PlayerShip playerShip = (PlayerShip) other;
                playerShip.takeDamage(10); // Example damage value for PlayerShip hit by Asteroid
            }
        }
    }
}