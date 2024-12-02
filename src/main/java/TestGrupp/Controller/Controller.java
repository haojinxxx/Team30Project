package TestGrupp.Controller;

import TestGrupp.Model.GameModel;
import java.awt.*;
import java.awt.event.*;
import TestGrupp.View.Panel;
import TestGrupp.Model.GameModel;
import javax.swing.*;

public class Controller implements KeyListener{
    //a basic controller that handles KEYBOARD input

    GameModel gm;
    Panel panel;

    Action upAction;
    Action leftAction;
    Action rightAction;
    public Controller(GameModel gm, Panel panel) {

        this.gm = gm;
        this.panel = panel;
        this.panel.addKeyListener(this);

        this.panel.setFocusable(true);
        this.panel.requestFocusInWindow();

   }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            panel.getSpaceShipView().setLocation(panel.getSpaceShipView().getX() - 10, panel.getSpaceShipView().getY());
        }
        if (key == KeyEvent.VK_RIGHT) {
            panel.getSpaceShipView().setLocation(panel.getSpaceShipView().getX() + 10, panel.getSpaceShipView().getY());
        }
        if (key == KeyEvent.VK_UP) {
            panel.getSpaceShipView().setLocation(panel.getSpaceShipView().getX(), panel.getSpaceShipView().getY() - 10);
        }
        if (key == KeyEvent.VK_DOWN) {
            panel.getSpaceShipView().setLocation(panel.getSpaceShipView().getX(), panel.getSpaceShipView().getY() + 10);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void update() {
        panel.repaint();
    }
}