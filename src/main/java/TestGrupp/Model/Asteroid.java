package TestGrupp.Model;

public class Asteroid extends GameObject {
    private final int childAsteroids;
    private final PhysicsComponent physics;
    private final HealthComponent health;
    private GameModel gameModel;

    // Constructor
    public Asteroid(float initialX, float initialY, float rotation, float scaleX, float scaleY, double speed, int health, int childAsteroids) {
        super();
        this.childAsteroids = childAsteroids;
        TransformComponent transform = this.getTransform();
        transform.setX(initialX);
        transform.setY(initialY);
        transform.setRotation(rotation);
        float angle = this.getTransform().getRotation();
        this.health = new HealthComponent(health);
        this.physics = new PhysicsComponent();
        this.physics.setVelocityX((float) (speed * Math.cos(Math.toRadians(angle))));
        this.physics.setVelocityY((float) (speed * Math.sin(Math.toRadians(angle))));
    }

    // Set the GameModel instance
    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    // Methods
    public void move(float deltaTime) {
        physics.update(deltaTime, this.getTransform());
    }

    public void takeDamage(int damage) {
        this.health.removeHealth(damage);
        if (this.health.getHealth() <= 0) {
            for (int i = 0; i < childAsteroids; i++) {
                if (gameModel != null) {
                    gameModel.createAsteroid(this.getTransform().getX(), this.getTransform().getY(), (float) Math.random() * 360, 1, 1, 1, 10, 0);
                }
            }
            this.setActive(false);
        }
    }
}