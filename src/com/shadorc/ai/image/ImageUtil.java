package com.shadorc.ai.image;

import java.awt.image.BufferedImage;

public class ImageUtil {

    public static Integer[] getColor(final BufferedImage image, final int x, final int y) {
        final int color = image.getRGB(x, y);
        final int red = (color & 0x00ff0000) >> 16;
        final int green = (color & 0x0000ff00) >> 8;
        final int blue = color & 0x000000ff;
        return new Integer[]{red, green, blue};
    }

}
