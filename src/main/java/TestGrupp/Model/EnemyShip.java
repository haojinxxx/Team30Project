package TestGrupp.Model;

import TestGrupp.Model.EntityComponents.FacePlayer;
import TestGrupp.Model.EntityComponents.FireAtPlayer;
import TestGrupp.Model.EntityComponents.Component;

import javax.vecmath.Point2d;
import java.util.ArrayList;
import java.util.List;

public class EnemyShip extends GameObject {

    private final GameEventListener listener;
    private final List<Component> components;
    private final PhysicsComponent physics;
    private final HealthComponent health;
    private final int firingRange;

    public EnemyShip(Point2d position, double rotation, double maxSpeed, int health, int projectileDamage, int firingRange, GameEventListener listener) {
        super(position, rotation, listener);
        this.listener = listener;
        this.firingRange = firingRange;


        this.physics = new PhysicsComponent(maxSpeed, 0.95);



        this.components = new ArrayList<>();
        
        this.health = new HealthComponent(health);


        this.components.add(new FacePlayer());
        this.components.add(new FireAtPlayer(listener, projectileDamage, firingRange, 2));
    }

    private Double distanceToPlayer(Point2d playerPosition) {
        return this.getPos().distance(playerPosition);
    }


    /**
 * Updates the enemy ship by updating its components and physics.
 *
 * @param deltaTime The time elapsed since the last update, in seconds.
 */
    @Override
    public void update(double deltaTime) {


        super.update(deltaTime);
        Point2d playerPosition = listener.getPlayerPosition();


        for (Component component : components) {
            component.update(deltaTime, this.getTransform(), this.physics, playerPosition);
        }

        // Have the ship not move if it is too close to the player
        if (distanceToPlayer(playerPosition) < firingRange*0.75) {
            return;
        }

        double moveAngle = getTransform().getRotation();
        double moveX = Math.cos(moveAngle) * 4000;
        double moveY = Math.sin(moveAngle) * 4000;

        physics.setAcceleration(moveX, moveY);
        physics.update(deltaTime, this.getTransform());

    }

    /**
     * Inflicts damage to the enemy ship and checks if it should be destroyed.
     *
     * @param damage The amount of damage to inflict.
     */
    public void takeDamage(int damage) {
        System.out.printf("Enemy ship took %d damage\n", damage);
        this.health.removeHealth(damage);
        if (this.health.getHealth() <= 0) {
            System.out.printf("Enemy ship destroyed\n");
            this.setActive(false);
            if (listener != null) {
                listener.onEnemyDestroyed(this);
            }
        }
    }


    @Override
    public void onCollision(GameObject other) {
        System.out.printf("Goes here");
        if (other instanceof Projectile) {
            Projectile projectile = (Projectile) other;
            if (projectile.isPlayerProjectile()) {
                System.out.printf("Too here");
                takeDamage(projectile.getDamage());
            }
        }
    }

}