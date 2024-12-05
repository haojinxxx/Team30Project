package TestGrupp.View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Sprite extends JComponent {
    private double rotation;

    public void setRotation(double rotation) {
        this.rotation = rotation;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform originalTransform = g2d.getTransform();
        g2d.rotate(Math.toRadians(rotation), getWidth() / 2, getHeight() / 2);
        // Draw the component (e.g., an image or shape)
        g2d.setTransform(originalTransform);
    }
}