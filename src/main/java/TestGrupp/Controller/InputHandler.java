package TestGrupp.Controller;

import TestGrupp.Model.GameModel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class InputHandler implements KeyListener {
    // Set to track the keys pressed
    private Set<Integer> pressedKeys;

    public InputHandler() {
        // Initialize the set for tracking pressed keys
        pressedKeys = new HashSet<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used, so we leave it empty
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Add the key code to the set when the key is pressed
        pressedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Remove the key code from the set when the key is released
        pressedKeys.remove(e.getKeyCode());
    }

    // Check if a specific key is pressed
    public boolean isKeyPressed(int keyCode) {
        return pressedKeys.contains(keyCode);
    }
}