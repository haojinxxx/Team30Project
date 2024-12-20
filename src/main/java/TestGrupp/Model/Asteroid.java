package TestGrupp.Model;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Asteroid extends GameObject implements Enemy {
    private final int childAsteroids;
    private final PhysicsComponent physics;
    private final HealthComponent health;
    private final ScreenDataSingleton screenDataSingleton;
    private GameEventListener listener;

    // Constructor
    public Asteroid(Point2d position, double rotation, double speed, int health, int childAsteroids, GameEventListener listener) {
        super(position, rotation, listener);
        this.childAsteroids = childAsteroids;
        this.listener = listener;
        this.screenDataSingleton = ScreenDataSingleton.getInstance(0, 0, 0);
        // This does not work, we are not able to access the singleton from multiple places in the codebase which is a problem
        // The issue seems to be with that we are using lazy initialization where you pass arguments to the getInstance method (currently done in the Controller class)
        // which then calls the private constructor. I think we need to redesign it a bit to be able to use it throughout the codebase.
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


    private boolean isOnMap() {
        Rectangle mapArea = screenDataSingleton.getMapArea();
        Rectangle2D.Float boundingBOx = this.getTransform().getBoundingBox();
        return mapArea.contains(boundingBOx);


    }
    public void update(double deltaTime) {
        if (!isOnMap()) {
            setActive(false);
        }
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
        //this.setActive(false);
    }

    public void onCollision(GameObject other) {
        if (other instanceof Projectile) {
            Projectile projectile = (Projectile) other;
            takeDamage(projectile.getDamage());
        }
    }


    @Override
    public void spawn(GameModel gameModel, Point2d pos) {
        gameModel.spawnAsteroid(pos, childAsteroids);
    }
}