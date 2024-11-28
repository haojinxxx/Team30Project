package TestGrupp.Controller;

import java.awt.*;
import java.awt.event.*;
import TestGrupp.View.Panel;
import TestGrupp.Model.GameModel;
import javax.swing.*;

public class Controller {
    //a basic controller that handles KEYBOARD input

    GameModel gm;
    Panel panel;

    Action upAction;
    Action leftAction;
    Action rightAction;
    public Controller(GameModel gm, Panel panel) {

     this.gm = gm;
     this.panel = panel;

     upAction = new upAction();
     leftAction = new leftAction();
     rightAction = new rightAction();
    }

/*
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {

        }
        if (key == KeyEvent.VK_A) {

        }
        if (key == KeyEvent.VK_D) {

        }
    */


    class upAction extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            gm.player.move();
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


