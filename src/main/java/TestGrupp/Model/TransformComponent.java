package TestGrupp.Model;

import javax.vecmath.Point2d;
import java.awt.geom.Rectangle2D;

public class TransformComponent {
    private Point2d position;
    private double rotation; // Stored in radians
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

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation % (2 * Math.PI); // Normalize to [0, 2π]
        if (this.rotation < 0) this.rotation += 2 * Math.PI; // Ensure positive values
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
