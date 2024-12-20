package TestGrupp.View;

import TestGrupp.Model.PowerUp;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PowerUpView extends JPanel {

    private JButton powerUp1;
    private JButton powerUp2;
    private JButton powerUp3;
    private JButton powerUp4;

    public PowerUpView(int margin, int buttonWidth) {
        setLayout(new GridBagLayout());
        setOpaque(false); // Make the panel transparent

        // Create buttons
        powerUp1 = createCustomButton("", "src/main/resources/images/HealthPowerUp.png");
        powerUp2 = createCustomButton("PowerUp 2", null);
        powerUp3 = createCustomButton("PowerUp 3", null);
        powerUp4 = createCustomButton("PowerUp 4", null);

        // Set preferred size for buttons
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

    private JButton createCustomButton(String text, String imagePath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.decode("#261F34"));
        button.setForeground(Color.decode("#9D9EF8"));
        button.setBorder(BorderFactory.createLineBorder(Color.decode("#9D9EF8"), 5));
        if (imagePath != null) {
            ImageIcon icon = new ImageIcon(imagePath);
            Image image = icon.getImage(); // transform it
            Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            icon = new ImageIcon(newimg);  // transform it back
            button.setIcon(icon);
        }
        return button;
    }

    private void highlightButton(JButton button, Color color) {
        button.setBackground(color);
    }

    public void highlightPowerUps(List<PowerUp> collectedPowerUps) {
        resetButtonColors();
        for (PowerUp powerUp : collectedPowerUps) {
            switch (powerUp.getType()) {
                case "Health":
                    highlightButton(powerUp1, Color.decode("#6A6494"));
                    break;
                case "Shield":
                    highlightButton(powerUp2, Color.decode("#6A6494"));
                    break;
                case "Speed":
                    highlightButton(powerUp3, Color.decode("#6A6494"));
                    break;
                case "Damage":
                    highlightButton(powerUp4, Color.decode("#6A6494"));
                    break;
            }
        }
    }

    private void resetButtonColors() {
        powerUp1.setBackground(Color.decode("#261F34"));
        powerUp2.setBackground(Color.decode("#261F34"));
        powerUp3.setBackground(Color.decode("#261F34"));
        powerUp4.setBackground(Color.decode("#261F34"));
    }


}