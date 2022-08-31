package com.shadorc.ai.path;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PathFrame extends JFrame {

    private final List<City> cities;

    public PathFrame() {
        super("Path generation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        this.cities = new ArrayList<>();

        final JPanel panel = new JPanel() {
            @Override
            public void paintComponent(final Graphics g) {
                super.paintComponent(g);

                final Graphics2D g2d = (Graphics2D) g;
                for (int i = 0; i < PathFrame.this.cities.size(); ++i) {
                    final City city1 = PathFrame.this.cities.get(i);
                    final City city2 = PathFrame.this.cities.get((i + 1) % PathFrame.this.cities.size());
                    g2d.drawLine(city1.x(), city1.y(), city2.x(), city2.y());
                }
            }
        };

        this.setContentPane(panel);
        this.setSize(new Dimension(1024, 720));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void updateCities(final List<City> cities) {
        this.cities.clear();
        this.cities.addAll(cities);

        this.getContentPane().repaint();
    }

}
