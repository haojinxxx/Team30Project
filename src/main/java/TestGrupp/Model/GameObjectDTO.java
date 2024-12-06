package TestGrupp.Model;

import javax.vecmath.Point2d;

public class GameObjectDTO {
    private int id;
    private Point2d position;
    private double rotation;
    private boolean active;
    private String spriteType; // E.g., "PlayerShip", "Asteroid", etc.

    public GameObjectDTO(int id, Point2d position, double rotation, boolean active, String spriteType) {
        this.id = id;
        this.position = position;
        this.rotation = rotation;
        this.active = active;
        this.spriteType = spriteType;
    }

    public int getId() { return id; }
    public Point2d getPosition() { return position; }
    public double getRotation() { return rotation; }
    public boolean isActive() { return active; }
    public String getSpriteType() { return spriteType; }
}
