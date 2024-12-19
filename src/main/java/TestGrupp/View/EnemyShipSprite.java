package TestGrupp.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EnemyShipSprite extends Sprite {
    private BufferedImage playerShipImage;
    private int shipWidth;
    private int shipHeight;

    public EnemyShipSprite(int shipWidth, int shipHeight) {
        super();
        this.shipWidth = shipWidth;
        this.shipHeight = shipHeight;
        initializeSprite();
    }


    @Override
    protected void loadImage() {
        try {
            playerShipImage = ImageIO.read(new File("src/main/resources/images/EnemyShipBasic-Model.png"));
            setImage(playerShipImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected int getSpriteWidth() {
        return shipWidth;
    }

    @Override
    protected int getSpriteHeight() {
        return shipHeight;
    }
}
