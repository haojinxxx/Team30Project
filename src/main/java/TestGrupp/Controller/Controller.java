package TestGrupp.Controller;

import TestGrupp.Model.GameModel;
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

*/}
}