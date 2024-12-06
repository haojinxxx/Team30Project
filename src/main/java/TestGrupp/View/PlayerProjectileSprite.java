package TestGrupp.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

public class PlayerProjectileSprite extends Sprite {
    private Image projectileImage;

    public PlayerProjectileSprite(int projectileSquareDimension) {
        super();
        // Increase the bounding box size by a small factor to prevent clipping during rotation
        setPreferredSize(new Dimension(projectileSquareDimension + 20, projectileSquareDimension + 20)); // Add padding for rotation
        setOpaque(false); // Make the panel transparent
        try {
            projectileImage = ImageIO.read(new File("src/main/resources/images/PlayerShip-Projectile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (projectileImage != null) {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            // Save the current transform state
            AffineTransform originalTransform = g2d.getTransform();

            // Move the origin to the center of the sprite
            g2d.translate(width / 2, height / 2);

            // Rotate the sprite around its center
            g2d.rotate(getRotation() + Math.PI / 2); // Correcting the rotation offset from default image rotation

            // Translate back to the original position after rotation
            g2d.translate(-width / 2, -height / 2);

            // Draw the image at the center of the sprite (no resizing)
            g2d.drawImage(projectileImage, 0, 0, width - 20, height - 20, this); // Adjust image bounds

            // Restore the original transform
            g2d.setTransform(originalTransform);
        }
    }
}