package TestGrupp.Model;

import javax.vecmath.Point2d;

public class GameObjectDTO {
    private int id;
    private Point2d position;
    private double rotation;
    private boolean active;
    private String spriteType; // E.g., "PlayerShip", "Asteroid", etc.

    /**
     * Data Transfer Object (DTO) for GameObject.
     * This class is used to transfer data about game objects between different parts of the application.
     */
    public GameObjectDTO(int id, Point2d position, double rotation, boolean active, String spriteType) {
        this.id = id;
        this.position = position;
        this.rotation = rotation;
        this.active = active;
        this.spriteType = spriteType;
    }

    /**
     * Gets the ID of the game object.
     *
     * @return the ID of the game object
     */
    public int getId() { return id; }

    /**
     * Gets the position of the game object.
     *
     * @return the position of the game object
     */
    public Point2d getPosition() { return position; }

    /**
     * Gets the rotation of the game object.
     *
     * @return the rotation of the game object
     */
    public double getRotation() { return rotation; }

    /**
     * Checks if the game object is active.
     *
     * @return true if the game object is active, false otherwise
     */
    public boolean isActive() { return active; }

    /**
     * Gets the sprite type of the game object.
     *
     * @return the sprite type of the game object
     */
    public String getSpriteType() { return spriteType; }


}
