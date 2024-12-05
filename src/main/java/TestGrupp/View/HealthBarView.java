package TestGrupp.View;

import javax.swing.*;
import java.awt.*;

public class HealthBarView extends JPanel {

    public HealthBarView(int margin) {
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        setOpaque(false); // Make the panel transparent

        // Create health bar label
        JLabel healthBarLabel = new JLabel("HP");
        healthBarLabel.setForeground(Color.decode("#9D9EF8"));
        healthBarLabel.setFont(new Font("Arial", Font.BOLD, 20));


        // Create health bar
        JProgressBar healthBar = new JProgressBar();
        healthBar.setValue(200);
        healthBar.setForeground(Color.decode("#BE4343"));
        healthBar.setBackground(Color.decode("#5B2B2B"));
        healthBar.setPreferredSize(new Dimension(600, 25));
        healthBar.setMinimumSize(new Dimension(300, 25));


        // Add components to the panel with specific positions
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST; // Anchor to the left

        gbc.insets = new Insets(0, margin, 0, 30); // Add some padding
        gbc.gridx = 0;
        add(healthBarLabel, gbc);

        gbc.insets = new Insets(0, 0, 0, 50); // Add some right side padding
        gbc.gridx = 1;
        gbc.weightx = 1.0; // Allow horizontal expansion
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        add(healthBar, gbc);


    }
}
