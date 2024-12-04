package TestGrupp.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import TestGrupp.View.*;
import TestGrupp.Model.*;

import javax.vecmath.Point2d;


public class InputHandler implements KeyListener {
    Panel view;
    GameModel gm;

    public InputHandler (Panel view, GameModel model)
    {
        this.view = view;
        this.gm = model;
        this.view.addKeyListener(this);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
        }
        if (key == KeyEvent.VK_RIGHT) {
            gm.getPlayerShip().setPos(new Point2d(gm.getPlayerShip().getX() + 10, gm.getPlayerShip().getY()));
           // System.out.println(gm.getPlayerShip().getX());
            }
        if (key == KeyEvent.VK_W) {
            gm.getPlayerShip().move();
            System.out.println(gm.getPlayerShip().getPos());
            }
        if (key == KeyEvent.VK_DOWN) {
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
