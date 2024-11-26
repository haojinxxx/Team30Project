package TestGrupp.View;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {

    public BottomPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.darkGray);
        setPreferredSize(new Dimension(900, 120));

        // Create health bar label
        JLabel healthBarLabel = new JLabel("HP");
        healthBarLabel.setForeground(Color.gray);
        healthBarLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Create left panel for health bar label
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setOpaque(false); // Make the panel transparent
        leftPanel.add(healthBarLabel);

        // Create right panel for buttons
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setOpaque(false); // Make the panel transparent


        // Create buttons
        JButton powerUp1 = new JButton("PowerUp 1");
        JButton powerUp2 = new JButton("PowerUp 2");
        JButton powerUp3 = new JButton("PowerUp 3");
        JButton powerUp4 = new JButton("PowerUp 4");


        // Add buttons to the right panel with specific positions
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 50; // Add some width to the buttons
        gbc.ipady = 50; // Add some height to the buttons

        gbc.gridx = 0;
        rightPanel.add(powerUp1, gbc);

        gbc.gridx = 1;
        rightPanel.add(powerUp2, gbc);

        gbc.gridx = 2;
        rightPanel.add(powerUp3, gbc);

        gbc.gridx = 3;
        rightPanel.add(powerUp4, gbc);

        // Add left and right panels to the BottomPanel
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }
}