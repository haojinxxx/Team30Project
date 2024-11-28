package TestGrupp.Model;

public class PhysicsComponent {
    private float velocityX;
    private float velocityY;
    private float forwardAcceleration;

    private float friction;
    public PhysicsComponent() {
        this.velocityX = 0;
        this.velocityY = 0;
        this.forwardAcceleration = 0;
        this.friction = 0.98f;

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

    public float getForwardAcceleration() {
        return forwardAcceleration;
    }

    public void setForwardAcceleration(float forwardAcceleration) {
        this.forwardAcceleration = forwardAcceleration;
    }

    public float getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }


    public void update(float deltaTime, TransformComponent transform) {

        float radians = (float) Math.toRadians(transform.getRotation());
        velocityX += (float) (forwardAcceleration * Math.cos(radians) * deltaTime);
        velocityY += (float) (forwardAcceleration * Math.sin(radians) * deltaTime);

        velocityX *= friction;
        velocityY *= friction;

        transform.setX(transform.getX() + velocityX * deltaTime);
        transform.setY(transform.getY() + velocityY * deltaTime);
    }
}