package TestGrupp.View;

import javax.swing.*;
import java.awt.*;

public class Panel extends JFrame {

    public Panel(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(1000, 800));

        // Set the background color of the content pane
        getContentPane().setBackground(Color.black);


        // Create and add the bottom panel to the frame
        BottomPanel bottomPanel = new BottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);

        // Create and add the spaceship panel to the frame
        SpaceShipView spaceShipView = new SpaceShipView();
        add(spaceShipView, BorderLayout.CENTER);


        // Set the frame to full screen
        // Needs to be done last, after adding components
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (gd.isFullScreenSupported()) {
            gd.setFullScreenWindow(this);
        } else {
            System.err.println("Full screen not supported");
            setSize(900, 600); // Fallback to a default size
        }

        setVisible(true);
    }

    public void updateFrame() {
        // Update frame logic
    }
}