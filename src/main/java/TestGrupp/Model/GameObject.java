package TestGrupp.Model;

import TestGrupp.Controller.ConfigurationLoader;

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
 * @param listener the event listener associated with the game object
 */
public GameObject(Point2d position, double rotation, GameEventListener listener) {
    this.id = System.identityHashCode(this);
    this.active = true;
    System.out.printf("%s.width\n", this.getClass().getSimpleName());
    double scaleX = ConfigurationLoader.getProperty(this.getClass().getSimpleName() + ".width");
    double scaleY = ConfigurationLoader.getProperty(this.getClass().getSimpleName() + ".height");

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

    public void onCollision(GameObject other) {};



}