package TestGrupp.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents the enemy ship sprite in the game.
 * This class handles loading the image and providing the dimensions for the enemy ship.
 */
public class EnemyShipSprite extends Sprite {
    private BufferedImage playerShipImage;
    private int shipWidth;
    private int shipHeight;

    public EnemyShipSprite(int shipWidth, int shipHeight) {
        super();
        this.shipWidth = shipWidth;
        this.shipHeight = shipHeight;
        initializeSprite();
    }

    /**
     * Load the image for the enemy ship sprite.
     */
    @Override
    protected void loadImage() {
        try {
            playerShipImage = ImageIO.read(new File("src/main/resources/images/EnemyShipBasic-Model.png"));
            setImage(playerShipImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the width of the enemy ship sprite.
     *
     * @return the width of the enemy ship sprite
     */
    protected int getSpriteWidth() {
        return shipWidth;
    }

    /**
     * Get the height of the enemy ship sprite.
     *
     * @return the height of the enemy ship sprite
     */
    @Override
    protected int getSpriteHeight() {
        return shipHeight;
    }
}
