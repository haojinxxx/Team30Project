package TestGrupp.Model;

public class Projectile extends GameObject {

    private double angle;
    private final PhysicsComponent physics;
    private int damage;

    public Projectile(float initialX, float initialY, float rotation, float scaleX, float scaleY, double speed, int damage) {
        super(initialX, initialY, rotation, scaleX, scaleY);
        this.physics = new PhysicsComponent();
        angle = this.getTransform().getRotation();
        this.getTransform().setX(initialX);
        this.getTransform().setY(initialY);


        physics.setVelocityX((float) (speed * Math.cos(Math.toRadians(angle))));
        physics.setVelocityY((float) (speed * Math.sin(Math.toRadians(angle))));

        this.damage = damage;
    }
    public int getDamage() {
        return damage;
    }

    public void update(double deltaTime) {
        super.update(deltaTime);
        physics.update(deltaTime, this.getTransform());
    }
}