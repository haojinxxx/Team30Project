package TestGrupp.Model;

public class PhysicsComponent {
    private float velocityX;
    private float velocityY;
    private float accelerationX;
    private float accelerationY;
    private float forwardAcceleration;

    public PhysicsComponent() {
        this.velocityX = 0;
        this.velocityY = 0;
        this.accelerationX = 0;
        this.accelerationY = 0;
        this.forwardAcceleration = 0;
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

    public float getAccelerationX() {
        return accelerationX;
    }

    public void setAccelerationX(float accelerationX) {
        this.accelerationX = accelerationX;
    }

    public float getAccelerationY() {
        return accelerationY;
    }

    public void setAccelerationY(float accelerationY) {
        this.accelerationY = accelerationY;
    }

    public float getForwardAcceleration() {
        return forwardAcceleration;
    }

    public void setForwardAcceleration(float forwardAcceleration) {
        this.forwardAcceleration = forwardAcceleration;
    }

    public void update(float deltaTime, TransformComponent transform) {
        // Update the velocity based on acceleration
        velocityX += accelerationX * deltaTime;
        velocityY += accelerationY * deltaTime;

        // Update the velocity based on forward acceleration and rotation
        float radians = (float) Math.toRadians(transform.getRotation());
        velocityX += (float) (forwardAcceleration * Math.cos(radians) * deltaTime);
        velocityY += (float) (forwardAcceleration * Math.sin(radians) * deltaTime);

        // Update the position based on velocity
        transform.setX(transform.getX() + velocityX * deltaTime);
        transform.setY(transform.getY() + velocityY * deltaTime);
    }
}