package TestGrupp.Model;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

public class Asteroid extends GameObject implements Enemy{
    private final int childAsteroids;
    private final PhysicsComponent physics;
    private final HealthComponent health;
    private GameEventListener listener;

    // Constructor
    public Asteroid(Point2d position, double rotation, double scaleX, double scaleY, double speed, int health, int childAsteroids, GameEventListener listener) {
        super(position, rotation, scaleX, scaleY, listener);
        this.childAsteroids = childAsteroids;
        this.listener = listener;

        TransformComponent transform = this.getTransform();
        transform.setPosition(position);
        transform.setRotation(rotation);
        double angle = this.getTransform().getRotation();

        this.health = new HealthComponent(health);

        this.physics = new PhysicsComponent();
        this.physics.setVelocity(new Vector2d(Math.cos(Math.toRadians(angle)), Math.sin(Math.toRadians(angle))));
    }

    // Methods
    public void update(float deltaTime) {
        physics.update(deltaTime, this.getTransform());
    }

    public void takeDamage(int damage) {
        this.health.removeHealth(damage);
        if (this.health.getHealth() <= 0) {
            destroy();
        }
    }
    public void destroy() {
        for (int i = 0; i < childAsteroids; i++) {
            if (listener != null) {
                listener.onAsteroidDestroyed(this.getTransform().getPosition(), childAsteroids);
            }
        }
        this.setActive(false);
    }

    @Override
    public void spawn(GameModel gameModel, double x, double y) {
        gameModel.createAsteroid(x, y, Math.random() * 360, 1, 1, 1, 10, 0);
    }
}