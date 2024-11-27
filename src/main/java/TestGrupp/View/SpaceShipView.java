package TestGrupp.View;

import javax.swing.*;
import java.awt.*;

public class SpaceShipView extends JPanel {

    public SpaceShipView() {
        setPreferredSize(new Dimension(50, 50));
        setOpaque(false); // Make the panel transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Define the points of the triangle (spaceship)
        int[] xPoints = {25, 0, 50};
        int[] yPoints = {0, 50, 50};

        // Set the color and draw the triangle
        g2d.setColor(Color.decode("#67B091")); // White color
        g2d.fillPolygon(xPoints, yPoints, 3);
    }
}
