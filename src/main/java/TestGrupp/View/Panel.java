package TestGrupp.View;

import TestGrupp.Observer.Observer;

import javax.swing.*;

public class Panel extends JFrame implements Observer {
    private SpaceShipView spaceShipView;
    private BackgroundView backGroundView;
    private BottomPanel bottomPanel;

    public Panel(String title) {
        // Initialization code
    }

    @Override
    public void update() {
        // Update the view based on the model changes
        render();
    }

    public void render() {
        // Update all sprites location, rotation, etc.
        repaint();
    }
}