package TestGrupp.Model;

import TestGrupp.Model.EntityComponents.FacePlayer;
import TestGrupp.Model.EntityComponents.FireAtPlayer;
import TestGrupp.Model.EntityComponents.Component;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;
import java.util.ArrayList;
import java.util.List;

public class EnemyShip extends GameObject implements Enemy {

    private final GameEventListener listener;
    private final List<Component> components;
    private final PhysicsComponent physics;
    private final HealthComponent health;

    public EnemyShip(Point2d position, double rotation, double maxSpeed, int health, int projectileDamage, int firingRange, GameEventListener listener) {
        super(position, rotation, maxSpeed, health, listener);
        this.listener = listener;


        this.physics = new PhysicsComponent(maxSpeed, 0.95);



        this.components = new ArrayList<>();
        
        this.health = new HealthComponent(health);


        this.components.add(new FacePlayer());
        this.components.add(new FireAtPlayer(listener, projectileDamage, firingRange, 2));
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


        double moveAngle = getTransform().getRotation();
        double moveX = Math.cos(moveAngle) * 2000;
        double moveY = Math.sin(moveAngle) * 2000;

        physics.setAcceleration(moveX, moveY);
        physics.update(deltaTime, this.getTransform());

    }

    /**
     * Inflicts damage to the enemy ship and checks if it should be destroyed.
     *
     * @param damage The amount of damage to inflict.
     */
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