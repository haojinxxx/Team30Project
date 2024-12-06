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
}
