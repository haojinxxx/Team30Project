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

     /*
     upAction = new upAction();
     leftAction = new leftAction();
     rightAction = new rightAction();

     gm.player.getInputMap().put(KeyStroke.getKeyStroke("W"), "up");
     gm.player.getActionMap().put("up", upAction);
     gm.player.getInputMap().put(KeyStroke.getKeyStroke("A"), "left");
     gm.player.getActionMap().put("left", leftAction);
     gm.player.getInputMap().put(KeyStroke.getKeyStroke("D"), "right");
     gm.player.getActionMap().put("right", rightAction);

    }



    class upAction extends AbstractAction {
        public void actionPerformed(ActionEvent e) {

        }
    }

    class leftAction extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            gm.player.lRotate();
        }
    }

    class rightAction extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            gm.player.move();
        }
    }


}

*/}

    @Override
    public void keyTyped(KeyEvent e) {

    }

   @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {

        }
        if (key == KeyEvent.VK_RIGHT) {

        }
        if (key == KeyEvent.VK_UP) {
            panel.spaceShipView.setLocation(panel.spaceShipView.getX() - 10, panel.spaceShipView.getY());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void update() {
        panel.repaint();
    }
}