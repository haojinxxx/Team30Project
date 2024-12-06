package TestGrupp.Model;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

public class PhysicsComponent {
    private Vector2d velocity;      // Represents velocity as a 2D vector
    private Vector2d acceleration;  // Represents acceleration as a 2D vector
    private double friction;        // Friction coefficient
    private double maxSpeed;        // Maximum speed limit
    private double previousRotation;  // The previous rotation to check for sharp turns

    public PhysicsComponent(double maxSpeed, double friction) {
        this.velocity = new Vector2d(0, 0);       // Initial velocity
        this.acceleration = new Vector2d(0, 0);  // Initial acceleration
        this.friction = friction;                  // Default friction
        this.maxSpeed = maxSpeed;                   // Default max speed
        this.previousRotation = 0;  // Initialize previous rotation to zero
    }

    // Getter for Velocity
    public Vector2d getVelocity() {
        return velocity;
    }

    // Setter for Velocity
    public void setVelocity(Vector2d velocity) {
        this.velocity.set(velocity);
    }

    // Getter for Acceleration
    public Vector2d getAcceleration() {
        return acceleration;
    }

    // Setter for Acceleration
    public void setAcceleration(double x, double y) {
        this.acceleration = new Vector2d(x, y); // Ensure it updates correctly
    }

    // Getter for Friction
    public double getFriction() {
        return friction;
    }

    // Setter for Friction
    public void setFriction(double friction) {
        if (friction < 0 || friction > 1) {
            throw new IllegalArgumentException("Friction must be between 0 and 1.");
        }
        this.friction = friction;
    }

    // Getter for Max Speed
    public double getMaxSpeed() {
        return maxSpeed;
    }

    // Setter for Max Speed
    public void setMaxSpeed(double maxSpeed) {
        if (maxSpeed < 0) {
            throw new IllegalArgumentException("Max speed must be a positive value.");
        }
        this.maxSpeed = maxSpeed;
    }

    /**
     * Updates the physics state of the entity.
     *
     * @param deltaTime The time elapsed since the last update, in seconds.
     * @param transform The transform component to update the entity's position.
     */
    public void update(double deltaTime, TransformComponent transform) {
        // Apply acceleration to velocity
        if (acceleration.length() > 0) {
            Vector2d scaledAcceleration = new Vector2d(acceleration);
            scaledAcceleration.scale(deltaTime);
            velocity.add(scaledAcceleration);
        }

        // Debugging acceleration vector
        System.out.println("Acceleration: " + acceleration);

        // Apply friction differently based on whether acceleration is zero
        double velocityLength = velocity.length();

        if (acceleration.length() > 0) {
            // Full friction when accelerating
            velocity.scale(friction);
        } else {
            // Apply a reduced friction when not accelerating (gliding)
            double glideFriction = 0.98; // Reduced friction when no acceleration
            velocity.scale(glideFriction); // Glide effect
        }

        // Limit speed to maxSpeed
        double currentSpeed = velocity.length();
        if (currentSpeed > maxSpeed) {
            velocity.scale(maxSpeed / currentSpeed);
        }

        // Update position using velocity
        Point2d position = transform.getPosition();
        position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;
        transform.setPosition(position);

        // Update rotation based on acceleration vector
        if (acceleration.length() > 0) {
            double rotation = Math.atan2(acceleration.y, acceleration.x);
            System.out.println("New Rotation: " + rotation); // Debug rotation value
            transform.setRotation(rotation);
        }
    }
}
