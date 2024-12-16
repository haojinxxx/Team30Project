package TestGrupp.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

public class PowerUpSprite extends Sprite{
    private Image PowerUpImage;
    private int powerUpWidth;
    private int powerUpHeight;

    public PowerUpSprite(int powerUpWidth, int powerUpHeight) {
        super();
        this.powerUpWidth = powerUpWidth;
        this.powerUpHeight = powerUpHeight;
        setPreferredSize(new Dimension(powerUpWidth, powerUpHeight)); // Add padding for rotation
        setOpaque(false); // Make the panel transparent
        try {
            PowerUpImage = ImageIO.read(new File("src/main/resources/images/HealthPowerUp.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (PowerUpImage != null) {
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
            g2d.drawImage(PowerUpImage, 0, 0, powerUpWidth, powerUpHeight, this); // Adjust image bounds

            // Restore the original transform
            g2d.setTransform(originalTransform);
        }
    }

}
