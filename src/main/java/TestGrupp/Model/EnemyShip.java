package TestGrupp.Model;

public class EnemyShip extends GameObject implements Enemy {
    // Fields
    private double speed;

    private HealthComponent health;
    private PhysicsComponent physics;


    // Constructor
    public EnemyShip(double initialX, double initialY, double rotation, double maxSpeed, int health) {
        super(initialX, initialY, rotation, maxSpeed, health);
        this.getTransform().setX(initialX);
        this.getTransform().setY(initialY);
        this.getTransform().setRotation(rotation);

        double angle = this.getTransform().getRotation();
        this.health = new HealthComponent(health);

        this.physics = new PhysicsComponent();
        this.physics.setVelocityX(maxSpeed * Math.cos(Math.toRadians(angle)));
        this.physics.setVelocityY(maxSpeed * Math.sin(Math.toRadians(angle)));

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



    @Override
    public void spawn(GameModel gameModel, double x, double y) {
        gameModel.createEnemyShip(x, y, Math.random() * 360, 1, 100);
    }

}