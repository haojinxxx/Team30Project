package TestGrupp.View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

/**
 * Represents the power-up sprite in the game.
 * This class handles loading the image and providing the dimensions for the power-up.
 */
public class PowerUpSprite extends Sprite{
    private BufferedImage powerUpImage;
    private int powerUpWidth;
    private int powerUpHeight;

    /**
     * Constructs a new power-up sprite with the specified dimensions.
     *
     * @param powerUpWidth the width of the power-up
     * @param powerUpHeight the height of the power-up
     */
    public PowerUpSprite(int powerUpWidth, int powerUpHeight) {
        super();
        this.powerUpWidth = powerUpWidth;
        this.powerUpHeight = powerUpHeight;
        initializeSprite();
    }

    /**
     * Load the image for the power-up sprite.
     */
    @Override
    protected void loadImage() {
        try {
            powerUpImage = ImageIO.read(new File("src/main/resources/images/HealthPowerUp.png"));
            setImage(powerUpImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the width of the power-up sprite.
     *
     * @return the width of the power-up sprite
     */
    @Override
    protected int getSpriteWidth() {
        return powerUpWidth;
    }

    /**
     * Get the height of the power-up sprite.
     *
     * @return the height of the power-up sprite
     */
    @Override
    protected int getSpriteHeight() {
        return powerUpHeight;
    }


}
