package TestGrupp.Model;

import javax.vecmath.Point2d;
import java.util.ArrayList;
import java.util.List;

public class PlayerShip extends GameObject {

    private double maxSpeed;
    private double acceleration;
    private HealthComponent health;
    private PhysicsComponent physics;
    private TransformComponent transform;
    private boolean hasShield;
    private List<Projectile> shipProjectiles;

    private double desiredRotation;  // The rotation the player is aiming for
    private double rotationSpeed;    // The speed at which rotation occurs
    private boolean rotating;        // Whether the ship is rotating

    private boolean movingForward;
    private boolean movingBackward;

    public PlayerShip(Point2d position, double rotation, int scaleX, int scaleY, GameEventListener listener) {
        super(position, 0, 0, 0, listener);
        this.health = new HealthComponent(100);
        this.shipProjectiles = new ArrayList<>();
        this.hasShield = false;
        this.physics = new PhysicsComponent();
        this.transform = new TransformComponent(position, rotation, scaleX, scaleY);

        // Default settings
        this.rotationSpeed = 300.0;
        this.rotating = false;
        this.desiredRotation = rotation;

        // Movement settings
        this.acceleration = 200;
        this.maxSpeed = 10.0;    // Set the max speed for the ship
        this.movingForward = false;
        this.movingBackward = false;
    }

    // Rotation logic: Update desired rotation
    public void rotate(Double degrees) {
        this.desiredRotation = transform.getRotation() + degrees;
        this.rotating = true;
    }

    public void stopRotation() {
        this.rotating = false;
    }

    // Move logic: Set flags for moving forward or backward
    public void setMovingForward(boolean movingForward) {
        this.movingForward = movingForward;
    }

    public void setMovingBackward(boolean movingBackward) {
        this.movingBackward = movingBackward;
    }

    // Update the position and handle physics updates
    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
        System.out.println("update i playership");

        // Handle rotation (rotate towards desired angle)
        if (rotating) {
            double angleDiff = desiredRotation - transform.getRotation();

            if (Math.abs(angleDiff) > rotationSpeed * deltaTime) {
                transform.setRotation(transform.getRotation() + Math.signum(angleDiff) * rotationSpeed * deltaTime);
            } else {
                transform.setRotation(desiredRotation);
                rotating = false;  // Stop rotating once the desired rotation is reached
            }
        }

        // Handle movement based on input (accelerate ship forward or backward)
        if (movingForward) {
            // Move forward in the direction the ship is facing
            double moveAngle = transform.getRotation();
            double moveX = Math.cos(Math.toRadians(moveAngle)) * acceleration;
            double moveY = Math.sin(Math.toRadians(moveAngle)) * acceleration;
            physics.setAcceleration(moveX, moveY);
        } else if (movingBackward) {
            // Move backward (retardation is just negative acceleration)
            double moveAngle = transform.getRotation();
            double moveX = Math.cos(Math.toRadians(moveAngle)) * -acceleration;
            double moveY = Math.sin(Math.toRadians(moveAngle)) * -acceleration;
            physics.setAcceleration(moveX, moveY);
        } else {
            // No movement, stop acceleration
            physics.setAcceleration(0, 0);
        }

        // Update the physics, which will also update the transform (position)
        physics.update(deltaTime, transform);
    }

    public void takeDamage(int damage) {
        if (!hasShield) {
            health.removeHealth(damage);
        }
    }

    public void addHealth(int health) {
        this.health.addHealth(health);
    }

    // Retrieve all projectiles that have been fired
    public List<Projectile> getProjectiles() {
        return shipProjectiles;
    }
}