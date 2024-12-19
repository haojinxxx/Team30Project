package TestGrupp.View;

import TestGrupp.Model.Score;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    int buttonWidth;
    private PowerUpView powerUpView;
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
        powerUpView = new PowerUpView(margin, buttonWidth);


        add(healthBarView, BorderLayout.CENTER);
        add(powerUpView, BorderLayout.EAST);


    }
    public PowerUpView getPowerUpView() {
        return this.powerUpView;
    }

    public int getButtonWidth(){return buttonWidth;}

}