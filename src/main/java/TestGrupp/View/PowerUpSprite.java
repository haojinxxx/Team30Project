package TestGrupp.View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

public class PowerUpSprite extends Sprite{
    private BufferedImage powerUpImage;
    private int powerUpWidth;
    private int powerUpHeight;


    public PowerUpSprite(int powerUpWidth, int powerUpHeight) {
        super();
        this.powerUpWidth = powerUpWidth;
        this.powerUpHeight = powerUpHeight;
        initializeSprite();
    }


    @Override
    protected void loadImage() {
        try {
            powerUpImage = ImageIO.read(new File("src/main/resources/images/HealthPowerUp.png"));
            setImage(powerUpImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getSpriteWidth() {
        return powerUpWidth;
    }

    @Override
    protected int getSpriteHeight() {
        return powerUpHeight;
    }


}
