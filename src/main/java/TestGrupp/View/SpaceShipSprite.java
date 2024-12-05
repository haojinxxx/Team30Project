package TestGrupp.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SpaceShipSprite extends Sprite {
    private Image playerShipImage;

    public SpaceShipSprite(int shipSquareDimension) {
        super();
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
            int x = (getWidth() - getWidth()) / 2;
            int y = (getHeight() - getHeight()) / 2;
            g.drawImage(playerShipImage, x, y, getWidth(), getHeight(), this);
        }
    }
}