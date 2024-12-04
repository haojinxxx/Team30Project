package TestGrupp.Model;

import javax.vecmath.Point2d;

public abstract class GameObject {
    protected GameEventListener listener;
    private int id;
    private boolean active;
    private Point2d position;
    private boolean collidible;
    private TransformComponent transform;
    //private PhysicsComponent physics;

    public GameObject(Point2d position, double rotation, double scaleX, double scaleY, GameEventListener listener) {
        this.id = System.identityHashCode(this);
        this.active = true;
        this.transform = new TransformComponent(position, rotation, scaleX, scaleY);
        this.listener = listener;
        this.position = position;
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

    public void setActive(boolean active) {
        this.active = active;
    }

    public TransformComponent getTransform() {
        return transform;
    }

    public double getX() {
        return getPos().getX();
    }

    public double getY() {
        return getPos().getY();
    }

    public Point2d getPos() { return new Point2d(position); }
    public void setPos(Point2d pos) {
        position = pos;
    }

    public void update(double deltaTime) {
        if (!this.isActive()) {
            return;
        }

    }
}