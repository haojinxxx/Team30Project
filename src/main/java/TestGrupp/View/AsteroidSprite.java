package TestGrupp.View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AsteroidSprite extends Sprite {
    private BufferedImage asteroidImage;
    private int asteroidWidth;
    private int asteroidHeight;

    public AsteroidSprite(int asteroidWidth, int asteroidHeight) {
        super();
        this.asteroidWidth = asteroidWidth;
        this.asteroidHeight = asteroidHeight;
        initializeSprite();
    }


    @Override
    protected void loadImage() {
        try {
            asteroidImage = ImageIO.read(new File("src/main/resources/images/EnemyJunkBolt-Size1.png"));
            setImage(asteroidImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getSpriteWidth() {
        return asteroidWidth;
    }

    @Override
    protected int getSpriteHeight() {
        return asteroidHeight;
    }
}
