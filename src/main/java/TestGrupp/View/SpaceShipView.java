package TestGrupp.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SpaceShipView extends JPanel {

    private Image playerShipImage;
    private int shipSquareDimension;

    public SpaceShipView(int shipSquareDimension) {
        this.shipSquareDimension = shipSquareDimension;
        setPreferredSize(new Dimension(shipSquareDimension, shipSquareDimension));
        setOpaque(false); // Make the panel transparent
        try {
            playerShipImage = ImageIO.read(new File("src/main/resources/images/PlayerShip-Model.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (playerShipImage != null) {
            int x = (getWidth() - shipSquareDimension) / 2;
            int y = (getHeight() - shipSquareDimension) / 2;
            g.drawImage(playerShipImage, x, y, shipSquareDimension, shipSquareDimension, this);
        }
    }


}
