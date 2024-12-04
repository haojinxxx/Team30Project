package TestGrupp.View;

import javax.swing.*;
import java.awt.*;

public class Panel extends JFrame {

    private final int widthScreen;
    private final int heightScreen;
    private final int shipSquareDimension;
    private final int margin;
    private SpaceShipView spaceShipView;
    private BackgroundView backGroundView;
    BottomPanel bottomPanel;
    private ScoreView scoreView;

    public Panel(String title) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.widthScreen = screenSize.width;
        this.heightScreen = screenSize.height;
        this.shipSquareDimension = (int) (0.05 * widthScreen);  // 5% of the screen width
        this.margin = 30;

        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(widthScreen, heightScreen);
        setUndecorated(true); // Make the frame borderless
        setResizable(false);
        setLocationRelativeTo(null);  // Center the frame

        // Set the screen to full screen
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        device.setFullScreenWindow(this);

        // Create a layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(widthScreen, heightScreen));
        setContentPane(layeredPane);

        // Create and add the background panel to the layered pane
        backGroundView = new BackgroundView(widthScreen, heightScreen);
        backGroundView.setBounds(0, 0, widthScreen, heightScreen);
        layeredPane.add(backGroundView, JLayeredPane.DEFAULT_LAYER);

        // Create and add the spaceship panel to the layered pane
        spaceShipView = new SpaceShipView(shipSquareDimension);
        spaceShipView.setBounds((widthScreen - shipSquareDimension) / 2, (heightScreen - shipSquareDimension) / 2, shipSquareDimension, shipSquareDimension);
        layeredPane.add(spaceShipView, JLayeredPane.PALETTE_LAYER);

        // Create and add the bottom panel to the frame
        bottomPanel = new BottomPanel(widthScreen, margin);
        bottomPanel.setBounds(0, heightScreen - bottomPanel.getPreferredSize().height, widthScreen, bottomPanel.getPreferredSize().height);
        layeredPane.add(bottomPanel, JLayeredPane.MODAL_LAYER);
        setVisible(true);

        // Create and add the score view to the layered pane
        scoreView = new ScoreView();
        scoreView.setBounds(widthScreen - 200, 0, 200, 50); // Adjust the size and position as needed
        layeredPane.add(scoreView, JLayeredPane.POPUP_LAYER);
    }

    public void updateFrame() {
        // Update frame logic
    }


    public SpaceShipView getSpaceShipView() {
        return spaceShipView; //not secure?
    }



}