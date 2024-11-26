package TestGrupp.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Panel extends JFrame {

    public Panel(String title) {

        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setBackground(Color.black);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void updateFrame() {

    }

}
