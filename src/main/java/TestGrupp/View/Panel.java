package TestGrupp.View;

import javax.swing.*;
import java.awt.*;

public class Panel extends JFrame {

    private final int widthScreen;
    private final int heightScreen;
    private final int shipSquareDimension;
    private final int margin;

    public Panel(String title) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.widthScreen = screenSize.width;
        this.heightScreen = screenSize.height;
        this.shipSquareDimension = (int) (0.05 * widthScreen);  // 5% of the screen width
        this.margin = 30;

        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(widthScreen, heightScreen);
        setResizable(false);
        setLocationRelativeTo(null);  // Center the frame

        // Set the background color of the content pane
        getContentPane().setBackground(Color.black);

        // Create and add the bottom panel to the frame
        BottomPanel bottomPanel = new BottomPanel(widthScreen, margin);
        add(bottomPanel, BorderLayout.SOUTH);

        // Create and add the spaceship panel to the frame
        SpaceShipView spaceShipView = new SpaceShipView(shipSquareDimension);
        add(spaceShipView, BorderLayout.CENTER);

        setVisible(true);
    }

    public void updateFrame() {
        // Update frame logic
    }
}