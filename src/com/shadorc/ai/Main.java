package com.shadorc.ai;

import com.shadorc.ai.image.ImageAlgorithm;

import java.io.IOException;

public class Main {

    public static void main(final String[] args) throws IOException, InterruptedException {
        final ImageAlgorithm imageAlgorithm = new ImageAlgorithm("image.jpg");
        imageAlgorithm.compute();
    }
}
