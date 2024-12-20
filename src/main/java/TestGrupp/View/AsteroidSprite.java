package TestGrupp.View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents the asteroid sprite in the game.
 * This class handles loading the image and providing the dimensions for the asteroid.
 */
public class AsteroidSprite extends Sprite {
    private BufferedImage asteroidImage;
    private int asteroidWidth;
    private int asteroidHeight;

    /**
     * Constructs a new asteroid sprite with the specified dimensions.
     *
     * @param asteroidWidth the width of the asteroid
     * @param asteroidHeight the height of the asteroid
     */
    public AsteroidSprite(int asteroidWidth, int asteroidHeight) {
        super();
        this.asteroidWidth = asteroidWidth;
        this.asteroidHeight = asteroidHeight;
        initializeSprite();
    }

    /**
     * Load the image for the asteroid sprite.
     */
    @Override
    protected void loadImage() {
        try {
            asteroidImage = ImageIO.read(new File("src/main/resources/images/EnemyJunkBolt-Size1.png"));
            setImage(asteroidImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the width of the asteroid sprite.
     *
     * @return the width of the asteroid sprite
     */
    @Override
    protected int getSpriteWidth() {
        return asteroidWidth;
    }

    /**
     * Get the height of the asteroid sprite.
     *
     * @return the height of the asteroid sprite
     */
    @Override
    protected int getSpriteHeight() {
        return asteroidHeight;
    }
}
