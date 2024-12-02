package TestGrupp.Model;

public class Asteroid extends GameObject implements Enemy{
    private final int childAsteroids;
    private final PhysicsComponent physics;
    private final HealthComponent health;
    private GameModel gameModel;

    // Constructor
    public Asteroid(double initialX, double initialY, double rotation, double scaleX, double scaleY, double speed, int health, int childAsteroids) {
        super(initialX,initialY,rotation,scaleX,scaleY);
        this.childAsteroids = childAsteroids;

        TransformComponent transform = this.getTransform();
        transform.setX(initialX);
        transform.setY(initialY);
        transform.setRotation(rotation);
        double angle = this.getTransform().getRotation();

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
    public void uppdate(float deltaTime) {
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

    @Override
    public void spawn(GameModel gameModel, double x, double y) {
        gameModel.createAsteroid(x, y, Math.random() * 360, 1, 1, 1, 10, 0);
    }
}