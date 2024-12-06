package TestGrupp.Model;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

public class Projectile extends GameObject {

    private final PhysicsComponent physics;
    private int damage;

    public Projectile(Point2d initialPosition, double rotation, Vector2d velocity, double scaleX, double scaleY, double speed, int damage, GameEventListener listener) {
        super(initialPosition, rotation, scaleX, scaleY, listener);
        this.physics = new PhysicsComponent(speed, 0.95);
        this.getTransform().setPosition(initialPosition);
        this.getTransform().setRotation(rotation);

        physics.setVelocity(velocity);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void update(double deltaTime) {
        super.update(deltaTime);
        physics.update(deltaTime, this.getTransform());
    }
}