package TestGrupp.View;

import javax.swing.*;
import java.awt.*;

public class PowerUpView extends JPanel {

    public PowerUpView() {
        setLayout(new GridBagLayout());
        setOpaque(false); // Make the panel transparent

        // Create buttons
        JButton powerUp1 = new JButton("PowerUp 1");
        JButton powerUp2 = new JButton("PowerUp 2");
        JButton powerUp3 = new JButton("PowerUp 3");
        JButton powerUp4 = new JButton("PowerUp 4");


        //Set preferred size for buttons
        Dimension buttonSize = new Dimension(100, 100);
        powerUp1.setPreferredSize(buttonSize);
        powerUp2.setPreferredSize(buttonSize);
        powerUp3.setPreferredSize(buttonSize);
        powerUp4.setPreferredSize(buttonSize);

        // Add buttons to the panel with specific positions
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding
        gbc.anchor = GridBagConstraints.EAST;  // Anchor to the right

        gbc.gridx = 0;
        add(powerUp1, gbc);

        gbc.gridx = 1;
        add(powerUp2, gbc);

        gbc.gridx = 2;
        add(powerUp3, gbc);

        gbc.gridx = 3;
        gbc.insets = new Insets(5, 5, 5, 30); // Add more padding to the right
        add(powerUp4, gbc);
    }
}
