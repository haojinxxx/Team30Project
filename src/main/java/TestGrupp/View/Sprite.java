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
            AffineTransform originalTransform = g2d.getTransform();  // Save the original transform

            // Rotate around the center of the component
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            g2d.rotate(rotation, centerX, centerY);  // Rotate by the current angle

            // Draw the image centered in the component
            g2d.drawImage(image, 0, 0, this);

            // Restore the original transform after drawing
            g2d.setTransform(originalTransform);
        }
    }
}
