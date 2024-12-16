package TestGrupp.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AsteroidSprite extends Sprite {
    private BufferedImage asteroidImage;
    private int asteroidWidth;
    private int asteroidHeight;

    public AsteroidSprite(int asteroidWidth, int asteroidHeight) {
        super();
        this.asteroidWidth = asteroidWidth;
        this.asteroidHeight = asteroidHeight;
        initializeSprite();
        /*
        setPreferredSize(new Dimension(asteroidWidth, asteroidHeight));
        setOpaque(false); // Make the panel transparent
        try {
            asteroidImage = ImageIO.read(new File("src/main/resources/images/EnemyJunkBolt-Size1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

         */
    }

    /*
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (asteroidImage != null) {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            // Save the current transform state
            AffineTransform originalTransform = g2d.getTransform();

            // Move the origin to the center of the sprite
            g2d.translate(width / 2, height / 2);

            // Rotate the sprite around its center
            g2d.rotate(getRotation());

            // Translate back to the original position after rotation
            g2d.translate(-width / 2, -height / 2);

            // Draw the image at the center of the sprite
            g2d.drawImage(asteroidImage, 0, 0, width, height, this);

            // Restore the original transform
            g2d.setTransform(originalTransform);
        }
    }

     */

    @Override
    protected void loadImage() {
        try {
            asteroidImage = ImageIO.read(new File("src/main/resources/images/Asteroid.png"));
            setImage(asteroidImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getSpriteWidth() {
        return asteroidWidth;
    }

    @Override
    protected int getSpriteHeight() {
        return asteroidHeight;
    }
}
