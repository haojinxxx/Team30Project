package TestGrupp.View;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

/**
 * Represents the enemy projectile sprite in the game.
 * This class handles loading the image and providing the dimensions for the enemy projectile.
 */
public class EnemyProjectileSprite extends Sprite {
    private BufferedImage projectileImage;
    private int projectileWidth;
    private int projectileHeight;


    /**
     * Constructs a new enemy projectile sprite with the specified dimensions.
     *
     * @param projectileWidth the width of the enemy projectile
     * @param projectileHeight the height of the enemy projectile
     */
    public EnemyProjectileSprite(int projectileWidth, int projectileHeight) {
        super();
        this.projectileWidth = projectileWidth;
        this.projectileHeight = projectileHeight;
        initializeSprite();
    }

    /**
     * Load the image for the enemy projectile sprite.
     */
    @Override
    protected void loadImage() {
        try {
            projectileImage = ImageIO.read(new File("src/main/resources/images/EnemyShipBasic-Projectile.png"));
            setImage(projectileImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the width of the enemy projectile sprite.
     *
     * @return the width of the enemy projectile sprite
     */
    @Override
    protected int getSpriteWidth() {
        return projectileWidth;
    }

    /**
     * Get the height of the enemy projectile sprite.
     *
     * @return the height of the enemy projectile sprite
     */
    @Override
    protected int getSpriteHeight() {
        return projectileHeight;
    }
}