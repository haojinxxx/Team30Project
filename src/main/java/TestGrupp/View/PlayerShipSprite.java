package TestGrupp.View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents the player's ship sprite in the game.
 * This class handles loading the image and providing the dimensions for the player's ship.
 */
public class PlayerShipSprite extends Sprite {
    private BufferedImage playerShipImage;
    private int shipWidth;
    private int shipHeight;

    /**
     * Constructs a new player ship sprite with the specified dimensions.
     *
     * @param shipWidth the width of the player ship
     * @param shipHeight the height of the player ship
     */
    public PlayerShipSprite(int shipWidth, int shipHeight) {
        super();
        this.shipWidth = shipWidth;
        this.shipHeight = shipHeight;
        initializeSprite();
    }

    /**
     * Load the image for the player ship sprite.
     */
    @Override
    protected void loadImage() {
        try {
            playerShipImage = ImageIO.read(new File("src/main/resources/images/PlayerShip-Model.png"));
            setImage(playerShipImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the width of the player ship sprite.
     *
     * @return the width of the player ship sprite
     */
    @Override
    protected int getSpriteWidth() {
        return shipWidth;
    }

    /**
     * Get the height of the player ship sprite.
     *
     * @return the height of the player ship sprite
     */
    @Override
    protected int getSpriteHeight() {
        return shipHeight;
    }
}

