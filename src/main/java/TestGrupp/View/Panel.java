package TestGrupp.View;

import javax.swing.*;
import java.awt.*;

public class Panel extends JFrame {

    public Panel(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(900, 600));

        // Set the background color of the content pane
        getContentPane().setBackground(Color.black);


/*
        // Example of adding components to the layout
        add(new JButton("North"), BorderLayout.NORTH);
        add(new JButton("South"), BorderLayout.SOUTH);
        add(new JButton("East"), BorderLayout.EAST);
        add(new JButton("West"), BorderLayout.WEST);
        add(new JButton("Center"), BorderLayout.CENTER);
*/

        // Create a panel for inventory and health display
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBackground(Color.darkGray);
        bottomPanel.setPreferredSize(new Dimension(900, 120));


        // Create and customize label
        JLabel healthLabel = new JLabel("HP");
        healthLabel.setForeground(Color.red);
        bottomPanel.add(healthLabel);


        // Add the bottom panel to the frame
        add(bottomPanel, BorderLayout.SOUTH);




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