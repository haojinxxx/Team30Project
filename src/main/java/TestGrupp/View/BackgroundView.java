package TestGrupp.View;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;

/**
 * Represents the background view in the game.
 * This class handles loading the image and providing the dimensions for the background.
 */
public class BackgroundView extends JPanel {
    private int width;
    private int height;
    private Image backGroundImage;

    /**
     * Constructs a new background view with the specified dimensions.
     *
     * @param width the width of the background
     * @param height the height of the background
     */
    public BackgroundView(int width, int height) {
        this.width = width;
        this.height = height;

        // Set the background to space_background.png
        try {
            backGroundImage = ImageIO.read(new File("src/main/resources/images/space_background.png"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(width, height));
    }

    /**
     * Paints the background image onto the panel.
     *
     * @param g the Graphics context in which to paint
     */
    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        if (backGroundImage != null) {
           // g.drawImage(backGroundImage, width, height, this);
            g.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
