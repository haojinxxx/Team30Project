package TestGrupp.Model;

import javax.vecmath.Point2d;
import java.awt.geom.Rectangle2D;

public class TransformComponent {
    private Point2d position;
    private double rotation;
    private double scaleX;
    private double scaleY;

    public TransformComponent(Point2d position, double rotation, double scaleX, double scaleY) {
        this.position = position;
        this.rotation = rotation;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public Point2d getPosition() {
        return position;
    }

    public void setPosition(Point2d position) {
        this.position.set(position);
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public void setX(double x) {
        this.position.set(x, position.getY());
    }

    public void setY(double y) {
        this.position.set(position.getX(), y);
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getScaleX() {
        return scaleX;
    }

    public void setScaleX(double scaleX) {
        this.scaleX = scaleX;
    }

    public double getScaleY() {
        return scaleY;
    }

    public void setScaleY(double scaleY) {
        this.scaleY = scaleY;
    }

    public Rectangle2D.Float getBoundingBox() {
        return new Rectangle2D.Float(
                (float) position.getX(),
                (float) position.getY(),
                (float) scaleX,
                (float) scaleY
        );
    }
}