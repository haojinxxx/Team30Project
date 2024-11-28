package TestGrupp.Model;

public class PhysicsComponent {
    private float velocityX;
    private float velocityY;
    private float acceleration;
    private float friction;
    private float maxSpeed;

    public PhysicsComponent() {
        this.velocityX = 0;
        this.velocityY = 0;
        this.acceleration = 0;
        this.friction = 0.98f;
        this.maxSpeed = 10.0f; // Default max speed
    }

    public float getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void update(float deltaTime, TransformComponent transform) {
        float radians = (float) Math.toRadians(transform.getRotation());
        velocityX += (float) (acceleration * Math.cos(radians) * deltaTime);
        velocityY += (float) (acceleration * Math.sin(radians) * deltaTime);

        // Apply friction
        velocityX *= friction;
        velocityY *= friction;

        // Calculate the current speed
        float currentSpeed = (float) Math.sqrt(velocityX * velocityX + velocityY * velocityY);

        // Limit the speed to maxSpeed
        if (currentSpeed > maxSpeed) {
            float scale = maxSpeed / currentSpeed;
            velocityX *= scale;
            velocityY *= scale;
        }

        transform.setX(transform.getX() + velocityX * deltaTime);
        transform.setY(transform.getY() + velocityY * deltaTime);
    }
}