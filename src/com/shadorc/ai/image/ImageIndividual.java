package com.shadorc.ai.image;

import com.shadorc.ai.Individual;

import java.util.concurrent.ThreadLocalRandom;

public class ImageIndividual extends Individual<ImageData> {

    public ImageIndividual(final ImageData chromosome) {
        super(chromosome);
    }

    @Override
    public ImageIndividual mate(final Individual<ImageData> partner) {
        final ImageData childChromosome = new ImageData(ImageAlgorithm.TARGET.getWidth(), ImageAlgorithm.TARGET.getHeight());

        for (int x = 0; x < childChromosome.getWidth(); x++) {
            for (int y = 0; y < childChromosome.getHeight(); y++) {
                final int percentage = ThreadLocalRandom.current().nextInt(100);

                if (percentage < 49) {
                    childChromosome.setColor(x, y, this.getChromosome().getColor(x, y));
                } else if (percentage < 98) {
                    childChromosome.setColor(x, y, partner.getChromosome().getColor(x, y));
                } else {
                    childChromosome.setColor(x, y, ImageAlgorithm.mutatedGenes());
                }
            }
        }
        return new ImageIndividual(childChromosome);
    }

    @Override
    protected long computeFitness() {
        return this.getChromosome().distance(ImageAlgorithm.TARGET);
    }
}
