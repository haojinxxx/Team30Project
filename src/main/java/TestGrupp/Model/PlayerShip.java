package TestGrupp.Model;

import javax.vecmath.Point2d;
import java.util.ArrayList;
import java.util.List;

public class PlayerShip extends GameObject{


    private double speed;
    private double maxSpeed;
    private double acceleration;
    private double angle;
    private HealthComponent health;
    private PhysicsComponent physics;
    private TransformComponent transform;
    boolean hasShield;

    private List<Projectile> shipProjectiles;

    public PlayerShip(Point2d position, double rotation, int scaleX, int scaleY, GameEventListener listener) {
        super(position,0,0,0,listener);
        this.health = new HealthComponent(100);
        this.shipProjectiles = new ArrayList<>();
        this.hasShield = false;
        this.physics = new PhysicsComponent();
        this.transform = new TransformComponent(position,rotation,scaleX,scaleY);
    }

/*
    public void shootProjectile() {
        double projectileSpeed = 15;
        shipProjectiles.add(new Projectile((float) x, (float) y, (float) angle, 1.0f, 1.0f, speed, projectileSpeed, 10));
    }*/

    public void takeDamage(int damage) {
        if(!hasShield) {
            health.removeHealth(damage);
        }
    }

    public void move() {
        //Make the playershop move depending on acclereation and angle using PhysicsComponent
        physics.update(0.1, transform);
    }

    public void rotate(Double degrees) {
        transform.setRotation(transform.getRotation() + degrees);
    }
    public void addHealth(int health) {
        this.health.addHealth(health);
    }









    //retrieve all projectiles that has been fired
    //useful when rendering/updating position
    public List<Projectile> getProjectiles() {
        return shipProjectiles;
    }


}
