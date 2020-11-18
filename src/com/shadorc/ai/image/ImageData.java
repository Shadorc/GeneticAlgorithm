package com.shadorc.ai.image;

import java.awt.image.BufferedImage;

public class ImageData {

    private final Integer[][][] data;

    public ImageData(final int width, final int height) {
        this.data = new Integer[width][height][3];
    }

    public ImageData(final BufferedImage image) {
        this.data = new Integer[image.getWidth()][image.getHeight()][3];
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                this.data[x][y] = ImageUtil.getColor(image, x, y);
            }
        }
    }

    public int getRed(final int x, final int y) {
        return this.data[x][y][0];
    }

    public int getGreen(final int x, final int y) {
        return this.data[x][y][1];
    }

    public int getBlue(final int x, final int y) {
        return this.data[x][y][2];
    }

    public int getWidth() {
        return this.data.length;
    }

    public int getHeight() {
        return this.data[0].length;
    }

    public Integer[] getColor(final int x, final int y) {
        return this.data[x][y];
    }

    public void setColor(final int x, final int y, final Integer[] rgb) {
        this.data[x][y] = rgb;
    }

    public long distance(final ImageData other) {
        long distance = 0;
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                distance += Math.abs(this.getRed(x, y) - other.getRed(x, y));
                distance += Math.abs(this.getGreen(x, y) - other.getGreen(x, y));
                distance += Math.abs(this.getBlue(x, y) - other.getBlue(x, y));
            }
        }
        return distance;
    }

    public BufferedImage toBufferedImage() {
        final BufferedImage bufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                final int rgb = 255 << 24 | (this.getRed(x, y) & 255) << 16
                        | (this.getGreen(x, y) & 255) << 8 | (this.getBlue(x, y) & 255);
                bufferedImage.setRGB(x, y, rgb);
            }
        }
        return bufferedImage;
    }

}
