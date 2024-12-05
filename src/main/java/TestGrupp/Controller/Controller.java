package TestGrupp.Controller;

import TestGrupp.Model.GameModel;
import java.awt.*;
import java.awt.event.*;
import TestGrupp.View.Panel;

import javax.swing.*;



public class Controller {
    private GameModel gm;
    private InputHandler ih;
    private GameLoop loop;
    private Panel panel;

    public Controller(GameModel gm, Panel panel) {
        this.gm = gm;
        this.panel = panel;

        // Create InputHandler and GameLoop instances
        this.ih = new InputHandler();
        this.loop = new GameLoop(this.gm, this.panel, this.ih);

        // Set up the panel to listen for key events
        this.panel.setFocusable(true);
        this.panel.requestFocusInWindow();
        this.panel.addKeyListener(this.ih);
    }

    // Method to start the game loop
    public void startGame() {
        loop.start();  // This calls the start() method on the GameLoop instance
    }

    // Method to stop the game loop (if needed)
    public void stopGame() {
        loop.stop();
    }
}