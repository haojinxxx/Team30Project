package TestGrupp.View;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

public class EnemyProjectileSprite extends Sprite {
    private BufferedImage projectileImage;
    private int projectileWidth;
    private int projectileHeight;


    public EnemyProjectileSprite(int projectileWidth, int projectileHeight) {
        super();
        this.projectileWidth = projectileWidth;
        this.projectileHeight = projectileHeight;
        initializeSprite();
    }


    @Override
    protected void loadImage() {
        try {
            projectileImage = ImageIO.read(new File("src/main/resources/images/EnemyShipBasic-Projectile.png"));
            setImage(projectileImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override

    protected int getSpriteWidth() {
        return projectileWidth;
    }

    @Override
    protected int getSpriteHeight() {
        return projectileHeight;
    }
}