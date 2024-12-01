package TestGrupp.Model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class TransformComponent {
    private Point2D position;  // Position represented by Point2D
    private double rotation;  // Rotation in degrees or radians (your choice)
    private double scaleX;    // Scale factor in the x-direction
    private double scaleY;    // Scale factor in the y-direction

    public TransformComponent(double x, double y, double rotation, double scaleX, double scaleY) {
        this.position = new Point2D.Double(x, y);  // Initialize position using Point2D
        this.rotation = rotation;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(double x, double y) {
        this.position.setLocation(x, y);
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public void setX(double x) {
        this.position.setLocation(x, position.getY());
    }

    public void setY(double y) {
        this.position.setLocation(position.getX(), y);
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
