package TestGrupp.Model;


import TestGrupp.Model.Behaviors.AttackState;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

public class EnemyShip extends GameObject implements Enemy {
    private final int projectileDamage;
    private final int firingRange;

    // Fields
    private final GameEventListener listener;
    private double speed;

    private boolean collidible;

    private HealthComponent health;
    private PhysicsComponent physics;
    private BehaviorState behaviorState;

    // Constructor

    public EnemyShip(Point2d position, double rotation, double maxSpeed, int health, int projectileDamage, int firingRange, GameEventListener listener, EnemyProvider enemyProvider) {
        super(position, rotation, maxSpeed, health, listener);
        this.getTransform().setPosition(position);
        this.getTransform().setRotation(rotation);
        this.projectileDamage = projectileDamage;
        this.listener = listener;
        double angle = this.getTransform().getRotation();
        this.health = new HealthComponent(health);
        this.physics = new PhysicsComponent(maxSpeed, 0.95);
        this.physics.setVelocity(new Vector2d(Math.cos(Math.toRadians(angle)), Math.sin(Math.toRadians(angle))));

        this.collidible = true;
        this.firingRange = firingRange;

        this.behaviorState = new AttackState(GameModel.getPlayerShip().getTransform(), this, enemyProvider);
    }


    public int getFiringRange() {
        return firingRange;
    }
    public void update(double deltaTime) {
        super.update(deltaTime);
        if (behaviorState != null) {
            behaviorState.update(getTransform(), physics, deltaTime);
        }
        physics.update(deltaTime, this.getTransform());

        Vector2d velocity = physics.getVelocity();
        if (velocity.length() > 0) {
            double rotation = Math.atan2(velocity.y, velocity.x);
            this.getTransform().setRotation(rotation);
        }

    }

    public PhysicsComponent getPhysics() {
        return physics;
    }


    public void fire() {
        double projectileSpeed = 10;
        Point2d position = new Point2d(this.getTransform().getPosition());
        double rotation = this.getTransform().getRotation();
        int projectileDamage = this.projectileDamage;

        Vector2d velocity = new Vector2d(Math.cos(Math.toRadians(rotation)), Math.sin(Math.toRadians(rotation)));

        if (listener != null) {
            listener.onProjectileFired(position, velocity, rotation, projectileSpeed, projectileDamage, false);
        }
    }

    public void takeDamage(int damage) {
        this.health.removeHealth(damage);
        if (this.health.getHealth() <= 0) {
            this.setActive(false);
            if (listener != null) {
                listener.onEnemyDestroyed(this);
            }
        }
    }



    @Override
    public void spawn(GameModel gameModel, Point2d pos) {
        gameModel.createEnemyShip(pos, Math.random() * 360, 100, 100);
    }

}