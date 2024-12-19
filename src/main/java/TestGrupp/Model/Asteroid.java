package TestGrupp.Model;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

public class Asteroid extends GameObject implements Enemy {
    private final int childAsteroids;
    private final PhysicsComponent physics;
    private final HealthComponent health;
    private GameEventListener listener;

    // Constructor
    public Asteroid(Point2d position, double rotation, double speed, int health, int childAsteroids, GameEventListener listener) {
        super(position, rotation, listener);
        this.childAsteroids = childAsteroids;
        this.listener = listener;

        TransformComponent transform = this.getTransform();
        transform.setPosition(position);
        transform.setRotation(rotation);

        this.health = new HealthComponent(health);

        this.physics = new PhysicsComponent(speed, 0); // Adjust friction as needed
        Vector2d initialVelocity = new Vector2d(Math.cos(Math.toRadians(rotation)), Math.sin(Math.toRadians(rotation)));
        initialVelocity.scale(speed);
        this.physics.setVelocity(initialVelocity);

        this.physics.setIsProjectile(true); // Asteroids should be treated as projectiles
    }


    // Methods
    public void update(double deltaTime) {
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
                listener.onAsteroidDestroyed(this.getTransform().getPosition(), 0);
            }
        }
        this.setActive(false);
    }


    public void onCollision(GameObject other) {
        if (other instanceof Projectile) {
            Projectile projectile = (Projectile) other;
            takeDamage(projectile.getDamage());
        }
    }
}