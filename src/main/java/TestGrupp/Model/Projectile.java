package TestGrupp.Model;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

public class Projectile extends GameObject {

    private final PhysicsComponent physics;
    private int damage;

    private boolean isPlayerProjectile;

    // Constructor to spawn projectile with an initial position, rotation, velocity, scale, damage, and other parameters
    public Projectile(Point2d initialPosition, double rotation, double scaleX, double scaleY, double speed, int damage, GameEventListener listener, boolean playerProjectile) {
        super(initialPosition, rotation, scaleX, scaleY, listener);  // Using scaleX and scaleY for size
        this.physics = new PhysicsComponent(speed, 0.95);  // Initialize the PhysicsComponent with speed and friction
        this.isPlayerProjectile = playerProjectile;
        this.getTransform().setPosition(initialPosition);
        this.getTransform().setRotation(rotation);

        // Calculate velocity based on the rotation and speed
        double velocityX = Math.cos(rotation) * speed;
        double velocityY = Math.sin(rotation) * speed;
        Vector2d velocity = new Vector2d(velocityX, velocityY);

        // Set the calculated velocity in the PhysicsComponent
        physics.setVelocity(velocity);
        this.damage = damage;

        // Mark this object as a projectile to avoid friction application
        physics.setIsProjectile(true);
    }

    // Getter for damage
    public int getDamage() {
        return damage;
    }

    // Check if it's a player projectile
    public boolean isPlayerProjectile() {
        return isPlayerProjectile;
    }

    // Update the projectile's position
    public void update(double deltaTime) {
        super.update(deltaTime);  // Update the base GameObject (position, etc.)
        physics.update(deltaTime, this.getTransform());  // Update the physics and apply velocity
    }

    /**
     * This method is called when the projectile collides with another GameObject.
     * If the other GameObject is a PowerUp or a projectile from the same sender, the projectile is not destroyed.
     * Otherwise, the projectile is deactivated.
     *
     * @param other the GameObject that the projectile collides with
     */
    public void onCollision(GameObject other) {
        // We don't want projectiles to be destroyed when hitting a PowerUp, the condition after || is to prevent a projectile from being destroyed by hitting its sender,
        // Did a trick with the boolean, so it catches both player and enemy projectiles
        if (other instanceof PowerUp || (other instanceof PlayerShip == isPlayerProjectile)) {
            return;  // Do nothing if the projectile collides with a PowerUp, and it's a player projectile
        } else {
            setActive(false);  // Deactivate the projectile if it collides with anything else
        }
    }
}
