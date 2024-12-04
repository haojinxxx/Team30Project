package TestGrupp.View;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    int buttonWidth;
    public BottomPanel(int widthScreen, int margin) {
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        setBackground(Color.decode("#47406A"));
        setBorder(BorderFactory.createLineBorder(Color.decode("#9D9EF8"), 5));
        //setSize(new Dimension(width, 120));

        //create the dimension for the power-up buttons
        buttonWidth = (int) (0.08 * widthScreen);   // 8% of the screen width

        // Create and add the Healthbar and PowerUp panels to the bottom panel
        HealthBarView healthBarView = new HealthBarView(margin);
        PowerUpView powerUpView = new PowerUpView(margin, buttonWidth);


        add(healthBarView, BorderLayout.CENTER);
        add(powerUpView, BorderLayout.EAST);


    }

    public int getButtonWidth(){return buttonWidth;}
}