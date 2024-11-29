package TestGrupp.Model;

public abstract class GameObject {
    private int id;
    private boolean active;
    private TransformComponent transform;
    //private PhysicsComponent physics;

    public GameObject(double initialX, double initialY, double rotation, double scaleX, double scaleY) {
        this.id = System.identityHashCode(this);
        this.active = true;
        this.transform = new TransformComponent(initialX, initialY, rotation, scaleX, scaleY);
        //this.physics = new PhysicsComponent();
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public TransformComponent getTransform() {
        return transform;
    }

    /*
    public PhysicsComponent getPhysics() {
        return physics;
    }*/

    // Main update method in GameObject class
    public void update(double deltaTime) {
        if (!this.isActive()) {
            return;
        }

    }
}