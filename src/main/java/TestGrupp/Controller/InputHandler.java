package TestGrupp.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import TestGrupp.View.Panel;


public class InputHandler implements KeyListener {
    Panel panel;

    public InputHandler (Panel view)
    {
        this.panel = view;
        this.panel.addKeyListener(this);
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
}
