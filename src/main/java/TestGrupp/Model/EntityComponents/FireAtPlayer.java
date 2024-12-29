package TestGrupp.Model.EntityComponents;

import TestGrupp.Model.GameEventListener;
import TestGrupp.Model.PhysicsComponent;
import TestGrupp.Model.TransformComponent;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

public class FireAtPlayer implements Component {
    private final GameEventListener listener;
    private final int projectileDamage;
    private final int firingRange;
    private final double cooldown; // Cooldown period in milliseconds
    private double timeSinceLastShot; // Time since the last shot in milliseconds

    public FireAtPlayer(GameEventListener listener, int projectileDamage, int firingRange, double cooldown) {
        this.listener = listener;
        this.projectileDamage = projectileDamage;
        this.firingRange = firingRange;
        this.cooldown = cooldown;
        this.timeSinceLastShot = cooldown; // Initialize to allow immediate firing
    }

    /**
     * Updates the component to fire at the player if within range and cooldown period has elapsed.
     *
     * @param deltaTime      The time elapsed since the last update, in seconds.
     * @param transform      The transform component of the entity.
     * @param physics        The physics component of the entity.
     * @param playerPosition The position of the player.
     */
    @Override
    public void update(double deltaTime, TransformComponent transform, PhysicsComponent physics, Point2d playerPosition) {
        timeSinceLastShot += deltaTime * 1000; // Convert deltaTime to milliseconds

        Vector2d directionToPlayer = new Vector2d(
                playerPosition.getX() - transform.getPosition().getX(),
                playerPosition.getY() - transform.getPosition().getY()
        );

        if (directionToPlayer.length() <= firingRange && timeSinceLastShot >= cooldown) {
            fire(transform);
            timeSinceLastShot = 0; // Reset the time since the last shot
        }
    }

    private void fire(TransformComponent transform) {
        double projectileSpeed = 400;
        Point2d position = new Point2d(transform.getPosition());
        double rotation = transform.getRotation();
        Vector2d velocity = new Vector2d(Math.cos(rotation), Math.sin(rotation));

        if (listener != null) {
            listener.onProjectileFired(position, velocity, rotation, projectileSpeed, projectileDamage, false);
        }
    }
}