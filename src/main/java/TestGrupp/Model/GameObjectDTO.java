package TestGrupp.Model;

public class GameObjectDTO {
    private int id;
    private double x;
    private double y;
    private double rotation;
    private boolean active;
    private String spriteType; // E.g., "PlayerShip", "Asteroid", etc.

    public GameObjectDTO(int id, double x, double y, double rotation, boolean active, String spriteType) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.active = active;
        this.spriteType = spriteType;
    }

    public int getId() { return id; }
    public double getX() { return x; }
    public double getY() { return y; }
    public double getRotation() { return rotation; }
    public boolean isActive() { return active; }
    public String getSpriteType() { return spriteType; }
}
