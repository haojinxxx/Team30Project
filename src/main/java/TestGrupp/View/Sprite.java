package TestGrupp.View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Sprite extends JComponent {
    private double rotation;
    private BufferedImage image;

    // Constructor now only accepts an image when available
    public Sprite() {
        setOpaque(false); // Make sure the sprite component is transparent
    }

    // Template method
    public final void initializeSprite() {
        loadImage();
        setPreferredSize(new Dimension(getSpriteWidth() + 20, getSpriteHeight() + 20)); // Add padding for rotation
    }

    // Abstract methods to be implemented by subclasses
    protected abstract void loadImage();
    protected abstract int getSpriteWidth();
    protected abstract int getSpriteHeight();


    // Method to set the image for the sprite
    public void setImage(BufferedImage image) {
        this.image = image;
        repaint(); // Repaint after the image is set
    }

    // Method to set the rotation angle
    public void setRotation(double rotation) {
        this.rotation = rotation;
        //repaint(); // Repaint after the rotation is set
    }
    
    public double getRotation() {
        return rotation;
    }

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
