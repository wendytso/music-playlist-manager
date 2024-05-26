package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//EFFECTS: extends JTextArea to allow background change
//Code based on: https://stackoverflow.com/questions/26386422/how-to-set-background-image-to-a-jtextarea-in-a-jpanel
public class MyTextArea extends JTextArea {

    private Image image;

    public MyTextArea(int a, int b) {
        super(a, b);
        try {
            image = ImageIO.read(new File("resources/playlistImage.jpg"));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void setImage(ImageIcon icon) {
        this.image = icon.getImage();
        setOpaque(false);
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, null);
        super.paintComponent(graphics);
    }
}
