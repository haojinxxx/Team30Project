package TestGrupp.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

/**
 * Represents the player's projectile sprite in the game.
 * This class handles loading the image and providing the dimensions for the player's projectile.
 */
public class PlayerProjectileSprite extends Sprite {
    private BufferedImage projectileImage;
    private int projectileWidth;
    private int projectileHeight;

    /**
     * Constructs a new player projectile sprite with the specified dimensions.
     *
     * @param projectileWidth the width of the player projectile
     * @param projectileHeight the height of the player projectile
     */
    public PlayerProjectileSprite(int projectileWidth, int projectileHeight) {
        super();
        this.projectileWidth = projectileWidth;
        this.projectileHeight = projectileHeight;
        initializeSprite();
    }

    /**
     * Load the image for the player projectile sprite.
     */
    @Override
    protected void loadImage() {
        try {
            projectileImage = ImageIO.read(new File("src/main/resources/images/PlayerShip-Projectile.png"));
            setImage(projectileImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the width of the player projectile sprite.
     *
     * @return the width of the player projectile sprite
     */
    @Override
    protected int getSpriteWidth() {
        return projectileWidth;
    }

    /**
     * Get the height of the player projectile sprite.
     *
     * @return the height of the player projectile sprite
     */
    @Override
    protected int getSpriteHeight() {
        return projectileHeight;
    }

}