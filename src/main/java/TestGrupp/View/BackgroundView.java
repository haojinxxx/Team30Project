package TestGrupp.View;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
public class BackgroundView extends JPanel {
    private int width;
    private int height;
    private Image backGroundImage;

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


    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        if (backGroundImage != null) {
           // g.drawImage(backGroundImage, width, height, this);
            g.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
