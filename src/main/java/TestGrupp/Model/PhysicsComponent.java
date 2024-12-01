package TestGrupp.Model;

import javax.vecmath.Vector2d;

public class PhysicsComponent {
    private Vector2d velocity;      // Represents velocity as a 2D vector
    private Vector2d acceleration; // Represents acceleration as a 2D vector
    private double friction;       // Friction coefficient
    private double maxSpeed;       // Maximum speed limit

    public PhysicsComponent() {
        this.velocity = new Vector2d(0, 0);       // Initial velocity
        this.acceleration = new Vector2d(0, 0);  // Initial acceleration
        this.friction = 0.98;                    // Default friction
        this.maxSpeed = 10.0;                    // Default max speed
    }

    // Getter and Setter for Velocity
    public Vector2d getVelocity() {
        return velocity;
    }

    public void setVelocity(double x, double y) {
        this.velocity.set(x, y);
    }

    // Getter and Setter for Acceleration
    public Vector2d getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double x, double y) {
        this.acceleration.set(x, y);
    }

    // Getter and Setter for Friction
    public double getFriction() {
        return friction;
    }

    public void setFriction(double friction) {
        this.friction = friction;
    }

    // Getter and Setter for Max Speed
    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    // Update method
    public void update(double deltaTime, TransformComponent transform) {
        // Apply acceleration to velocity
        Vector2d scaledAcceleration = new Vector2d(acceleration);
        scaledAcceleration.scale(deltaTime);  // acceleration * deltaTime
        velocity.add(scaledAcceleration);    // velocity += acceleration * deltaTime

        // Apply friction to velocity
        velocity.scale(friction);

        // Limit speed to maxSpeed
        double currentSpeed = velocity.length();
        if (currentSpeed > maxSpeed) {
            velocity.scale(maxSpeed / currentSpeed);  // Scale velocity down to maxSpeed
        }

        // Update position using velocity
        double newX = transform.getX() + velocity.x * deltaTime;
        double newY = transform.getY() + velocity.y * deltaTime;
        transform.setPosition(newX, newY);
    }
}
