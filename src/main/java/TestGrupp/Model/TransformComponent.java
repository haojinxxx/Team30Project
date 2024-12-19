package TestGrupp.Model;

import javax.vecmath.Point2d;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

/**
 * The TransformComponent class represents the position, rotation, and scale of a game object.
 */
public class TransformComponent {
    private Point2d position;
    private double rotation; // Stored in radians
    private double scaleX;
    private double scaleY;

    /**
     * Constructs a new TransformComponent with the specified position, rotation, and scale.
     *
     * @param position the initial position of the game object
     * @param rotation the initial rotation of the game object, in radians
     * @param scaleX the initial scale factor along the X-axis
     * @param scaleY the initial scale factor along the Y-axis
     */
    public TransformComponent(Point2d position, double rotation, double scaleX, double scaleY) {
        this.position = position;
        this.rotation = rotation;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    /**
     * Gets the position of the game object.
     *
     * @return the position of the game object
     */
    public Point2d getPosition() {
        return position;
    }

    /**
     * Sets the position of the game object.
     *
     * @param position the new position of the game object
     */
    public void setPosition(Point2d position) {
        this.position.set(position);
    }

    /**
     * Gets the rotation of the game object.
     *
     * @return the rotation of the game object, in radians
     */
    public double getRotation() {
        return rotation;
    }

    /**
     * Sets the rotation of the game object.
     * The rotation is normalized to the range [0, 2π].
     *
     * @param rotation the new rotation of the game object, in radians
     */
    public void setRotation(double rotation) {
        this.rotation = rotation % (2 * Math.PI); // Normalize to [0, 2π]
        if (this.rotation < 0) this.rotation += 2 * Math.PI; // Ensure positive values
    }

    /**
     * Gets the bounding box of the game object.
     *
     * @return the bounding box of the game object
     */
    public Rectangle2D.Float getBoundingBox() {
        Point2d[] corners = new Point2d[]{
                new Point2d(position.getX() - scaleX / 2, position.getY() - scaleY / 2),
                new Point2d(position.getX() + scaleX / 2, position.getY() - scaleY / 2),
                new Point2d(position.getX() + scaleX / 2, position.getY() + scaleY / 2),
                new Point2d(position.getX() - scaleX / 2, position.getY() + scaleY / 2)
        };


        AffineTransform transform = new AffineTransform();
        transform.rotate(rotation, position.getX(), position.getY());
        Path2D path = new Path2D.Double();
        path.moveTo(corners[0].getX(), corners[0].getY());
        for (int i = 1; i < corners.length; i++) {
            path.lineTo(corners[i].getX(), corners[i].getY());
        }
        path.closePath();
        path.transform(transform);

        // Get the bounding box of the rotated shape
        Rectangle2D bounds = path.getBounds2D();
        return new Rectangle2D.Float(
                (float) bounds.getX(),
                (float) bounds.getY(),
                (float) bounds.getWidth(),
                (float) bounds.getHeight()
        );
    }
}
