package TestGrupp.Model;

import TestGrupp.Model.EntityComponents.FacePlayer;
import TestGrupp.Model.EntityComponents.FireAtPlayer;
import TestGrupp.Model.EntityComponents.Component;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;
import java.util.ArrayList;
import java.util.List;

public class EnemyShip extends GameObject implements Enemy {
    private final int projectileDamage;
    private final int firingRange;
    private final GameEventListener listener;
    private final List<Component> components;
    private final PhysicsComponent physics;
    private final HealthComponent health;

    public EnemyShip(Point2d position, double rotation, double maxSpeed, int health, int projectileDamage, int firingRange, GameEventListener listener) {
        super(position, rotation, maxSpeed, health, listener);
        this.projectileDamage = projectileDamage;
        this.firingRange = firingRange;
        this.listener = listener;


        this.physics = new PhysicsComponent(maxSpeed, 0.95);
        //this.physics.setIsProjectile(true);



        this.components = new ArrayList<>();
        
        this.health = new HealthComponent(health);

        // Add components based on desired behavior
        this.components.add(new FacePlayer());
        this.components.add(new FireAtPlayer(listener, projectileDamage, firingRange, 2));
    }


    @Override
    public void update(double deltaTime) {


        super.update(deltaTime);
        Point2d playerPosition = listener.getPlayerPosition();

        // Update all components
        for (Component component : components) {
            component.update(deltaTime, this.getTransform(), this.physics, playerPosition);
        }

        // Set physics acceleration vector based on current rotation
        Vector2d acceleration = new Vector2d(Math.cos(getTransform().getRotation()), Math.sin(getTransform().getRotation()));

        double moveAngle = getTransform().getRotation();
        double moveX = Math.cos(moveAngle) * 2000;
        double moveY = Math.sin(moveAngle) * 2000;

        physics.setAcceleration(moveX, moveY);
        physics.update(deltaTime, this.getTransform());

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