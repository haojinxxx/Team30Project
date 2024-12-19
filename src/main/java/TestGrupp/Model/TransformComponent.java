package TestGrupp.Model;

import javax.vecmath.Point2d;
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
        return new Rectangle2D.Float(
                (float) position.getX(),
                (float) position.getY(),
                (float) scaleX,
                (float) scaleY
        );
    }
}
