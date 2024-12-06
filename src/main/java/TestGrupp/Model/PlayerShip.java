package TestGrupp.Model;

import javax.vecmath.Point2d;
import java.util.ArrayList;
import java.util.List;

public class PlayerShip extends GameObject {
    private double maxSpeed;           // Maximum speed of the ship
    private double acceleration;       // Acceleration factor
    private double friction;           // Friction factor (how quickly the ship slows down)
    private HealthComponent health;    // Component to track health
    private PhysicsComponent physics;  // Component for movement and physics
    private boolean hasShield;         // Whether the ship has an active shield
    private List<Projectile> shipProjectiles; // Projectiles fired by the ship

    private double desiredRotation;    // The target rotation in radians
    private double rotationSpeed;      // Rotation speed in radians per second
    private boolean rotating;          // Whether the ship is actively rotating

    private boolean movingForward;     // Flag for forward movement
    private boolean movingBackward;    // Flag for backward movement

    public PlayerShip(Point2d position, double rotation, int scaleX, int scaleY, GameEventListener listener) {
        super(position, -Math.PI / 2, scaleX, scaleY, listener); // Call to the parent GameObject class
        this.health = new HealthComponent(100); // Start with full health
        this.shipProjectiles = new ArrayList<>();
        this.hasShield = false;

        // Default movement and rotation settings
        this.rotationSpeed = Math.toRadians(200); // Convert degrees/sec to radians/sec
        this.desiredRotation = rotation;
        this.acceleration = 2000; // Increase the acceleration factor
        this.maxSpeed = 3000.0;   // Increase the max speed for faster movement
        this.friction = 0.001;    // Decrease friction for slower deceleration
        this.rotating = false;
        this.movingForward = false;
        this.movingBackward = false;

        this.physics = new PhysicsComponent(maxSpeed, 0.95);
    }

    // Handle ship rotation to a specific angle in radians
    public void rotate(double radians) {
        this.desiredRotation = getTransform().getRotation() + radians;
        this.rotating = true;
    }

    // Stop the rotation
    public void stopRotation() {
        this.rotating = false;
    }

    // Set forward movement flag
    public void setMovingForward(boolean movingForward) {
        this.movingForward = movingForward;
    }

    // Set backward movement flag
    public void setMovingBackward(boolean movingBackward) {
        this.movingBackward = movingBackward;
    }

    // Add a projectile to the ship's fired projectiles list
    public void fireProjectile(Projectile projectile) {
        shipProjectiles.add(projectile);
    }

    // Retrieve all projectiles fired by the ship
    public List<Projectile> getProjectiles() {
        return shipProjectiles;
    }

    // Take damage, reducing health unless a shield is active
    public void takeDamage(int damage) {
        if (!hasShield) {
            health.removeHealth(damage);
        }
    }

    // Add health to the ship
    public void addHealth(int healthPoints) {
        this.health.addHealth(healthPoints);
    }

    // Activate the ship's shield
    public void activateShield() {
        this.hasShield = true;
    }

    // Deactivate the ship's shield
    public void deactivateShield() {
        this.hasShield = false;
    }

    // Update the ship's state
    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        // Handle rotation logic
        if (rotating) {
            double angleDiff = desiredRotation - getTransform().getRotation();
            angleDiff = Math.atan2(Math.sin(angleDiff), Math.cos(angleDiff)); // Normalize to [-π, π]

            // Smoothly update the rotation to match the desiredRotation
            double rotationChange = rotationSpeed * deltaTime;
            if (Math.abs(angleDiff) > rotationChange) {
                // Rotate towards desired angle with speed limit
                getTransform().setRotation(getTransform().getRotation() + Math.signum(angleDiff) * rotationChange);
            } else {
                // If the angle is close enough, set the rotation to the desired rotation
                getTransform().setRotation(desiredRotation);
                rotating = false;
            }
        }

        // Handle movement logic
        if (movingForward || movingBackward) {
            double moveAngle = getTransform().getRotation(); // Ship's rotation in radians
            double speedMultiplier = movingForward ? 1 : -1; // Determine forward/backward

            // Calculate acceleration vector based on ship's rotation
            double moveX = Math.cos(moveAngle) * acceleration * speedMultiplier;
            double moveY = Math.sin(moveAngle) * acceleration * speedMultiplier;

            // Debugging log for acceleration vector
            System.out.println("Moving Forward: " + movingForward);
            System.out.println("Rotation (radians): " + getTransform().getRotation());
            System.out.println("Acceleration Vector: (" + moveX + ", " + moveY + ")");

            physics.setAcceleration(moveX, moveY); // Set the acceleration to the physics component
        } else {
            physics.setAcceleration(0, 0); // No movement when flags are off
        }

        // Update physics and position
        physics.update(deltaTime, getTransform());
    }

    // Get the current health value
    public int getHealth() {
        return health.getHealth();
    }

    // Check if the shield is active
    public boolean isShieldActive() {
        return hasShield;
    }
}
