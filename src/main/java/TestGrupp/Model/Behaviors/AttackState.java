package TestGrupp.Model.Behaviors;

import TestGrupp.Model.*;

import javax.vecmath.Vector2d;
import java.util.List;

public class AttackState implements BehaviorState {
    private TransformComponent playerTransform;
    private EnemyShip enemyShip; // Reference to the enemy ship
    private EnemyProvider enemyProvider; // Reference to the enemy provider

    private static final double SEPARATION_DISTANCE = 30.0; // Minimum distance to maintain from other enemies
    private static final double ALIGNMENT_DISTANCE = 100.0; // Distance to consider for alignment
    private static final double COHESION_DISTANCE = 100.0; // Distance to consider for cohesion

    public AttackState(TransformComponent playerTransform, EnemyShip enemyShip, EnemyProvider enemyProvider) {
        this.playerTransform = playerTransform;
        this.enemyShip = enemyShip; // Initialize enemy ship
        this.enemyProvider = enemyProvider; // Initialize enemy provider
    }

    @Override
    public void update(TransformComponent transform, PhysicsComponent physics, double deltaTime) {
        Vector2d separation = new Vector2d();
        Vector2d alignment = new Vector2d();
        Vector2d cohesion = new Vector2d();
        int alignmentCount = 0;
        int cohesionCount = 0;

        List<EnemyShip> enemies = enemyProvider.getEnemies();
        for (EnemyShip otherEnemy : enemies) {
            if (otherEnemy != enemyShip) {
                Vector2d toOtherEnemy = new Vector2d(
                        otherEnemy.getTransform().getPosition().getX() - transform.getPosition().getX(),
                        otherEnemy.getTransform().getPosition().getY() - transform.getPosition().getY()
                );
                double distanceToOtherEnemy = toOtherEnemy.length();

                // Separation
                if (distanceToOtherEnemy < SEPARATION_DISTANCE) {
                    toOtherEnemy.normalize();
                    toOtherEnemy.scale(-1 / distanceToOtherEnemy); // Repulsion force inversely proportional to distance
                    separation.add(toOtherEnemy);
                }

                // Alignment
                if (distanceToOtherEnemy < ALIGNMENT_DISTANCE) {
                    alignment.add(otherEnemy.getPhysics().getVelocity());
                    alignmentCount++;
                }

                // Cohesion
                if (distanceToOtherEnemy < COHESION_DISTANCE) {
                    cohesion.add(otherEnemy.getTransform().getPosition());
                    cohesionCount++;
                }
            }
        }

        // Average the alignment and cohesion vectors
        if (alignmentCount > 0) {
            alignment.scale(1.0 / alignmentCount);
        }
        if (cohesionCount > 0) {
            cohesion.scale(1.0 / cohesionCount);
            cohesion.sub(transform.getPosition());
        }

        // Combine the forces
        Vector2d direction = new Vector2d(
                playerTransform.getPosition().getX() - transform.getPosition().getX(),
                playerTransform.getPosition().getY() - transform.getPosition().getY()
        );
        direction.normalize();
        direction.scale(physics.getMaxSpeed()); // Scale to the enemy ship's max speed

        direction.add(separation);
        direction.add(alignment);
        direction.add(cohesion);

        physics.setVelocity(direction);

        System.out.printf("The distance to the player is: %.2f\n", direction.length());
        double distanceToPlayer = direction.length();
        if (distanceToPlayer <= enemyShip.getFiringRange()) {
            System.out.printf("Enemy ship %d is firing at the player!\n", enemyShip.getId());
            enemyShip.fire();
        }
    }
}