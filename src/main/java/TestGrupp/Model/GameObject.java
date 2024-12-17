package TestGrupp.Model;

import javax.vecmath.Point2d;

public abstract class GameObject {
    protected GameEventListener listener;
    private int id;
    private boolean active;
    private boolean collidible;
    private TransformComponent transform;
    //private PhysicsComponent physics;

/**
 * Constructs a new GameObject with the specified position, rotation, scale, and event listener.
 *
 * @param position the initial position of the game object
 * @param rotation the initial rotation of the game object
 * @param scaleX the initial scale factor along the X-axis
 * @param scaleY the initial scale factor along the Y-axis
 * @param listener the event listener associated with the game object
 */
public GameObject(Point2d position, double rotation, double scaleX, double scaleY, GameEventListener listener) {
    this.id = System.identityHashCode(this);
    this.active = true;
    this.transform = new TransformComponent(position, rotation, scaleX, scaleY);
    this.listener = listener;
}

    public int getId() {
        return id;
    }

    public GameEventListener getListener() {
        return listener;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isCollidible() {
        return collidible;
    }

    /**
     * Sets the active state of the game object.
     *
     * @param active the new active state
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets the transform component of the game object.
     *
     * @return the transform component
     */
    public TransformComponent getTransform() {
        return transform;
    }

    public Point2d getPos() { return new Point2d(transform.getPosition()); }
    public void setPos(Point2d pos) {
        transform.setPosition(pos);
    }

    public void update(double deltaTime) {};



}