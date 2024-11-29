package TestGrupp.Model;

public class EnemyShip extends GameObject {
    // Fields
    private double speed;

    private HealthComponent health;
    private PhysicsComponent physics;


    // Constructor
    public EnemyShip(float initialX, float initialY, float rotation, float maxSpeed, int health) {
        super(initialX, initialY, rotation, maxSpeed, health);
        this.getTransform().setX(initialX);
        this.getTransform().setY(initialY);
        this.getTransform().setRotation(rotation);

        double angle = this.getTransform().getRotation();
        this.health = new HealthComponent(health);

        this.physics = new PhysicsComponent();
        this.physics.setVelocityX((float) (maxSpeed * Math.cos(Math.toRadians(angle))));
        this.physics.setVelocityY((float) (maxSpeed * Math.sin(Math.toRadians(angle))));

    }

    // Methods
    public void update(double deltaTime) {
        super.update(deltaTime);
        physics.update(deltaTime, this.getTransform());
    }

    public void takeDamage(int damage) {
        this.health.removeHealth(damage);
        if (this.health.getHealth() <= 0) {
            this.setActive(false);
        }
    }

}