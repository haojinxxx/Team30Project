package TestGrupp.Model;

import java.util.ArrayList;
import java.util.List;

public class Ship extends GameObject{

    private double x, y; // position
    private double speed;
    private double maxSpeed;
    private double acceleration;
    private double angle;
    private Healthbar healthBar;

    private List<Projectile> shipProjectiles;


    private Ship() {
        this.x = 0;
        this.y = 0;
        this.speed = 0; // default speed
        this.maxSpeed = 10;
        this.acceleration = 0.2;
        this.angle = 0; // Initial angle
        this.healthBar = new Healthbar(100);
        this.shipProjectiles = new ArrayList<>();
    }


    public void move (char direction) {
        switch (direction) {
            case 'w':
                if (speed < maxSpeed) {
                    speed = speed + acceleration;
                }
                break;
            case 's':
                if (speed > 0) {
                    speed = speed - acceleration;
                }
                break;
            case 'a':
                angle = angle-5; // Left
                break;
            case 'd':
                angle = angle+5; // Right
                break;
        }

        // Update position based on angle
        x += speed * Math.cos(Math.toRadians(angle));
        y += speed * Math.sin(Math.toRadians(angle));
    }

    public void shootProjectile() {
        double projectileSpeed = 15;
        shipProjectiles.add(new Projectile(x, y, angle, projectileSpeed));
    }

    public void takeDamage(int damage) {
        healthBar.removeHealth(damage);
    }

    public void addHealth(int health) {
        healthBar.addHealth(health);
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
