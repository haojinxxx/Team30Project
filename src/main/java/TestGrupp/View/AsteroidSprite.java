package TestGrupp.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

public class AsteroidSprite extends Sprite {
    private Image asteroidImage;

    public AsteroidSprite(int asteroidDimension) {
        super();
        setPreferredSize(new Dimension(asteroidDimension, asteroidDimension));
        setOpaque(false); // Make the panel transparent
        try {
            asteroidImage = ImageIO.read(new File("src/main/resources/images/EnemyJunkBolt-Size1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
}
