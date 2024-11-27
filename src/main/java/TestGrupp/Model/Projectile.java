package TestGrupp.Model;

public class Projectile {
    private double x, y;
    private double speed;
    private double angle;

    public Projectile(double x, double y, double angle, double speed) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.speed = speed;
    }

    public void move() {
        x += speed * Math.cos(Math.toRadians(angle));
        y += speed * Math.sin(Math.toRadians(angle));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


}
