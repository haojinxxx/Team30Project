package TestGrupp.View;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {

    public BottomPanel() {
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        setBackground(Color.darkGray);
        setPreferredSize(new Dimension(900, 120));

        // Create and add the Healthbar and PowerUp panels to the bottom panel
        HealthBarView healthBarView = new HealthBarView();
        PowerUpView powerUpView = new PowerUpView();


        add(healthBarView, BorderLayout.CENTER);
        add(powerUpView, BorderLayout.EAST);
    }
}