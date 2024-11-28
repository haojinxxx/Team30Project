package TestGrupp.Model;

public class Projectile extends GameObject {

    private double angle;
    private int damage;

    public Projectile(float initialX, float initialY, float rotation, float scaleX, float scaleY, double speed, int damage) {
        super(initialX, initialY, rotation, scaleX, scaleY);
        PhysicsComponent physicsComponent = this.getPhysics();
        angle = this.getTransform().getRotation();
        this.getTransform().setX(initialX);
        this.getTransform().setY(initialY);


       physicsComponent.setVelocityX((float) (speed * Math.cos(Math.toRadians(angle))));
        this.getPhysics().setVelocityY((float) (speed * Math.sin(Math.toRadians(angle))));

        this.damage = damage;
    }
    public int getDamage() {
        return damage;
    }
}