package com.shadorc.ai.image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

public class ImageFrame extends JFrame {

    private final JLabel generatedImg;

    public ImageFrame(final BufferedImage img) {
        super("Image generation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        final JLabel originalImg = new JLabel();
        originalImg.setIcon(new ImageIcon(img));
        this.add(originalImg);

        this.generatedImg = new JLabel();
        this.add(this.generatedImg);

        this.pack();
        this.setPreferredSize(new Dimension(img.getWidth() * 2, img.getHeight() * 2));
        this.setVisible(true);
    }

    public void updateImage(final BufferedImage img) {
        this.generatedImg.setIcon(new ImageIcon(img));
    }

}
