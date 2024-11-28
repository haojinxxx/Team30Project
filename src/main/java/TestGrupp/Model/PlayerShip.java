package TestGrupp.Model;

import java.util.ArrayList;
import java.util.List;

public class PlayerShip extends GameObject{

    private double x, y; // position
    private double speed;
    private double maxSpeed;
    private double acceleration;
    private double angle;
    private HealthComponent health;
    private PhysicsComponent physics;
    boolean hasShield;

    private List<Projectile> shipProjectiles;


    private PlayerShip() {
        super(0, 0,0,0,0);
        this.health = new HealthComponent(100);
        this.shipProjectiles = new ArrayList<>();
        this.hasShield = false;
        this.physics = new PhysicsComponent();
    }


    public void move(char direction) {
        switch (direction) {
            case 'w':
                physics.setAcceleration(0.2);
                break;
            case 's':
                physics.setAcceleration(-0.2);
                break;
            case 'a':
                getTransform().setRotation(getTransform().getRotation() - 5);
                break;
            case 'd':
                getTransform().setRotation(getTransform().getRotation() + 5);
                break;
        }
        physics.update(1.0f, getTransform());
    }

    public void shootProjectile() {
        double projectileSpeed = 15;
        shipProjectiles.add(new Projectile((float) x, (float) y, (float) angle, 1.0f, 1.0f, projectileSpeed, 10));
    }

    public void takeDamage(int damage) {
        if(!hasShield) {
            health.removeHealth(damage);
        }
    }

    public void addHealth(int health) {
        this.health.addHealth(health);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }



    //retrieve all projectiles that has been fired
    //useful when rendering/updating position
    public List<Projectile> getProjectiles() {
        return shipProjectiles;
    }


}
