package TestGrupp.View;

import TestGrupp.Model.PowerUp;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Represents the power-up view in the game.
 * This class handles the layout and appearance of the power-up buttons.
 */
public class PowerUpView extends JPanel {

    private JButton powerUp1;
    private JButton powerUp2;
    private JButton powerUp3;
    private JButton powerUp4;


    /**
     * Constructs a new power-up view with the specified margin and button width.
     *
     * @param margin the margin between the buttons
     * @param buttonWidth the width of the buttons
     */
    public PowerUpView(int margin, int buttonWidth) {
        setLayout(new GridBagLayout());
        setOpaque(false); // Make the panel transparent

        // Create buttons
        powerUp1 = createCustomButton("", "src/main/resources/images/HealthPowerUp.png");
        powerUp2 = createCustomButton("", "src/main/resources/images/ShieldPowerUp.png");
        powerUp3 = createCustomButton("", "src/main/resources/images/SpeedPowerUp.png");
        powerUp4 = createCustomButton("", "src/main/resources/images/DamagePowerUp.png");

        // Set preferred size for buttons
        Dimension buttonSize = new Dimension(buttonWidth, buttonWidth);
        powerUp1.setPreferredSize(buttonSize);
        powerUp2.setPreferredSize(buttonSize);
        powerUp3.setPreferredSize(buttonSize);
        powerUp4.setPreferredSize(buttonSize);

        powerUp1.setFocusable(false);
        powerUp2.setFocusable(false);
        powerUp3.setFocusable(false);
        powerUp4.setFocusable(false);

        // Add buttons to the panel with specific positions
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5); // Add some padding
        gbc.anchor = GridBagConstraints.EAST;  // Anchor to the right

        gbc.gridx = 0;
        add(createButtonWithLabel(powerUp1, "H"), gbc);

        gbc.gridx = 1;
        add(createButtonWithLabel(powerUp2, "J"), gbc);

        gbc.gridx = 2;
        add(createButtonWithLabel(powerUp3, "K"), gbc);

        gbc.gridx = 3;
        gbc.insets = new Insets(10, 5, 10, margin); // Add more padding to the right
        add(createButtonWithLabel(powerUp4, "L"), gbc);
    }




    /**
     * Create a custom button with the specified text.
     *
     * @param text the text to display on the button
     * @return the custom button
     */

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

    private JLayeredPane createButtonWithLabel(JButton button, String labelText) {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(button.getPreferredSize());

        button.setBounds(0, 0, button.getPreferredSize().width, button.getPreferredSize().height);
        layeredPane.add(button, JLayeredPane.DEFAULT_LAYER);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Monospaced", Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        label.setBounds(10, 5, 20, 20); // Position the label in the top left corner
        layeredPane.add(label, JLayeredPane.PALETTE_LAYER);

        return layeredPane;
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