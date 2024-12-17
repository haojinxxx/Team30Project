package TestGrupp.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

public class PlayerShipSprite extends Sprite {
    private Image playerShipImage;
    private int shipWidth;
    private int shipHeight;

    public PlayerShipSprite(int shipWidth, int shipHeight) {
        super();
        this.shipWidth = shipWidth;
        this.shipHeight = shipHeight;
        // Increase the bounding box size by a small factor to prevent clipping during rotation
        setPreferredSize(new Dimension(shipWidth+ 20, shipHeight + 20)); // Add padding for rotation
        setOpaque(false); // Make the panel transparent
        try {
            playerShipImage = ImageIO.read(new File("src/main/resources/images/PlayerShip-Model.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (playerShipImage != null) {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            // Save the current transform state
            AffineTransform originalTransform = g2d.getTransform();

            // Move the origin to the center of the sprite
            g2d.translate(width / 2, height / 2);

            // Rotate the sprite around its center
            g2d.rotate(getRotation() + Math.PI / 2); // Correcting the rotation offset from default image rotation

            //g2d.drawImage(playerShipImage, -playerShipImage.getWidth(this) / 2, -playerShipImage.getHeight(this) / 2, this);
            g2d.drawImage(playerShipImage, -shipWidth / 2, -shipHeight / 2, shipWidth, shipHeight, this);

            // Restore the original transform
            g2d.setTransform(originalTransform);
        }
    }
}
