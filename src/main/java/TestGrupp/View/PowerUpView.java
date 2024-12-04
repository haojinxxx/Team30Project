package TestGrupp.View;

import javax.swing.*;
import java.awt.*;

public class PowerUpView extends JPanel {


    public PowerUpView(int margin, int buttonWidth) {
        setLayout(new GridBagLayout());
        setOpaque(false); // Make the panel transparent

        // Create buttons
        JButton powerUp1 = createCustomButton("PowerUp 1");
        JButton powerUp2 = createCustomButton("PowerUp 2");
        JButton powerUp3 = createCustomButton("PowerUp 3");
        JButton powerUp4 = createCustomButton("PowerUp 4");

        //Set preferred size for buttons
        Dimension buttonSize = new Dimension(buttonWidth, buttonWidth);
        powerUp1.setPreferredSize(buttonSize);
        powerUp2.setPreferredSize(buttonSize);
        powerUp3.setPreferredSize(buttonSize);
        powerUp4.setPreferredSize(buttonSize);

        // Add buttons to the panel with specific positions
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5); // Add some padding
        gbc.anchor = GridBagConstraints.EAST;  // Anchor to the right

        gbc.gridx = 0;
        add(powerUp1, gbc);

        gbc.gridx = 1;
        add(powerUp2, gbc);

        gbc.gridx = 2;
        add(powerUp3, gbc);

        gbc.gridx = 3;
        gbc.insets = new Insets(10, 5, 10, margin); // Add more padding to the right
        add(powerUp4, gbc);
    }

    private JButton createCustomButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.decode("#4C4568"));
        button.setForeground(Color.decode("#9D9EF8"));
        button.setBorder(BorderFactory.createLineBorder(Color.decode("#9D9EF8"), 5));;
        return button;
    }

}
