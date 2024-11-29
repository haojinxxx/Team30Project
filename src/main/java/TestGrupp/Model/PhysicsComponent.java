package TestGrupp.Model;

public class PhysicsComponent {
    private double velocityX;
    private double velocityY;
    private double acceleration;
    private double friction;
    private double maxSpeed;

    public PhysicsComponent() {
        this.velocityX = 0;
        this.velocityY = 0;
        this.acceleration = 0;
        this.friction = 0.98f;
        this.maxSpeed = 10.0f; // Default max speed
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getFriction() {
        return friction;
    }

    public void setFriction(double friction) {
        this.friction = friction;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void update(double deltaTime, TransformComponent transform) {
        double radians = (double) Math.toRadians(transform.getRotation());
        velocityX += (double) (acceleration * Math.cos(radians) * deltaTime);
        velocityY += (double) (acceleration * Math.sin(radians) * deltaTime);

        // Apply friction
        velocityX *= friction;
        velocityY *= friction;

        // Calculate the current speed
        double currentSpeed = (double) Math.sqrt(velocityX * velocityX + velocityY * velocityY);

        // Limit the speed to maxSpeed
        if (currentSpeed > maxSpeed) {
            double scale = maxSpeed / currentSpeed;
            velocityX *= scale;
            velocityY *= scale;
        }

        transform.setX(transform.getX() + velocityX * deltaTime);
        transform.setY(transform.getY() + velocityY * deltaTime);
    }
}