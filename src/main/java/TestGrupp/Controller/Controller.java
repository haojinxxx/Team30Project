package TestGrupp.Controller;

import TestGrupp.Model.GameModel;
import java.awt.*;
import java.awt.event.*;
import TestGrupp.View.Panel;

import javax.swing.*;



public class Controller {
    //a basic controller that handles KEYBOARD input

    GameModel gm;
    InputHandler ih;
    Panel panel;

    Action upAction;
    Action leftAction;
    Action rightAction;
    public Controller(GameModel gm, Panel panel) {

        this.gm = gm;
        this.panel = panel;
        this.ih = new InputHandler(this.panel);


        this.panel.setFocusable(true);
        this.panel.requestFocusInWindow();

   }



    public void update() {
        panel.repaint();
    }
}