
package TestGrupp.View;

import TestGrupp.Model.Score;
import TestGrupp.Observer.Observer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Observable;

public class ScoreView extends JPanel {
    private JLabel scoreLabel;

    public ScoreView() {
        setLayout(new FlowLayout(FlowLayout.LEFT)); // Change alignment to left
        setOpaque(false); // Make the panel transparent
        setBorder(new EmptyBorder(0, 30, 0, 0)); // Add left inset

        JLabel label = new JLabel("Score:");
        label.setFont(new Font("Monospaced", Font.BOLD, 25));
        label.setForeground(Color.WHITE);
        label.setBorder(new EmptyBorder(0, 10, 0, 0));

        scoreLabel = new JLabel("0");
        scoreLabel.setFont(new Font("Monospaced", Font.BOLD, 25));
        scoreLabel.setForeground(Color.WHITE);

        add(label);
        add(scoreLabel);
    }

    public void updateScore(int score) {
        scoreLabel.setText(Integer.toString(score));
    }

}
