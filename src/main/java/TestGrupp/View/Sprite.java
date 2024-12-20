package TestGrupp.View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * The abstract base class for all sprite components in the game.
 * It extends JComponent and provides common functionality for rendering and rotating sprites.
 */
public abstract class Sprite extends JComponent {
    private double rotation;
    private BufferedImage image;

    // Constructor now only accepts an image when available
    public Sprite() {
        setOpaque(false); // Make sure the sprite component is transparent
    }

    /**
     * Template method to initialize the sprite.
     * It loads the image and sets the preferred size with padding for rotation.
     */
    public final void initializeSprite() {
        loadImage();
        setPreferredSize(new Dimension(getSpriteWidth() + 20, getSpriteHeight() + 20)); // Add padding for rotation
    }

    /**
     * Load the image for the sprite.
     * This method must be implemented by subclasses.
     */
    protected abstract void loadImage();

    /**
     * Get the width of the sprite.
     * This method must be implemented by subclasses.
     *
     * @return the width of the sprite
     */
    protected abstract int getSpriteWidth();

    /**
     * Get the height of the sprite.
     * This method must be implemented by subclasses.
     *
     * @return the height of the sprite
     */
    protected abstract int getSpriteHeight();

    /**
     * Set the image for the sprite.
     *
     * @param image the BufferedImage to set for the sprite
     */
    public void setImage(BufferedImage image) {
        this.image = image;
        repaint(); // Repaint after the image is set
    }

    /**
     * Set the rotation for the sprite.
     *
     * @param rotation the rotation to set for the sprite
     */
    public void setRotation(double rotation) {
        this.rotation = rotation;
        //repaint(); // Repaint after the rotation is set
    }

    /**
     * Get the current rotation of the sprite.
     *
     * @return the rotation of the sprite
     */
    public double getRotation() {
        return rotation;
    }

    /**
     * Paint the sprite  with the current image and rotation.
     *
     * @param g the Graphics context in which to paint
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
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
            g2d.drawImage(image, -getSpriteWidth() / 2, -getSpriteHeight() / 2, getSpriteWidth(), getSpriteHeight(), this);

            // Restore the original transform
            g2d.setTransform(originalTransform);
        }
    }
}
