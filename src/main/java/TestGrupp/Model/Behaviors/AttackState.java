package TestGrupp.Model.Behaviors;

import TestGrupp.Model.BehaviorState;
import TestGrupp.Model.EnemyShip;
import TestGrupp.Model.TransformComponent;
import TestGrupp.Model.PhysicsComponent;
import javax.vecmath.Vector2d;
public class AttackState implements BehaviorState {
    private TransformComponent playerTransform;
    private EnemyShip enemyShip; // Reference to the enemy ship

    public AttackState(TransformComponent playerTransform, EnemyShip enemyShip) {
        this.playerTransform = playerTransform;
        this.enemyShip = enemyShip; // Initialize enemy ship
    }

    @Override
    public void update(TransformComponent transform, PhysicsComponent physics, double deltaTime) {
        Vector2d direction = new Vector2d(
                playerTransform.getPosition().getX() - transform.getPosition().getX(),
                playerTransform.getPosition().getY() - transform.getPosition().getY()
        );
        direction.normalize();
        direction.scale(physics.getMaxSpeed());
        physics.setVelocity(direction);

        double distance = Math.sqrt(
                Math.pow(playerTransform.getPosition().getX() - transform.getPosition().getX(), 2) +
                        Math.pow(playerTransform.getPosition().getY() - transform.getPosition().getY(), 2)
        );
        if (distance <= enemyShip.getFiringRange()) {
            enemyShip.fire();
        }
    }
}