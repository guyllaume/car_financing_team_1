package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PictureView extends JPanel {
    private BufferedImage image;

    public PictureView(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            // Draw the image at the upper-left corner of the panel
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
