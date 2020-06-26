package com.shadorc.ai.image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

public class ImageFrame extends JFrame {

    private final JLabel label2;

    public ImageFrame(final BufferedImage img) {
        super("Image generation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        final ImageIcon icon1 = new ImageIcon(img);
        final JLabel label1 = new JLabel();
        label1.setIcon(icon1);

        this.label2 = new JLabel();

        this.add(label1);
        this.add(this.label2);

        this.pack();
        this.setPreferredSize(new Dimension(img.getWidth() * 2, img.getHeight() * 2));
        this.setVisible(true);
    }

    public void updateImage(final BufferedImage img) {
        final ImageIcon icon = new ImageIcon(img);
        this.label2.setIcon(icon);
    }

}
