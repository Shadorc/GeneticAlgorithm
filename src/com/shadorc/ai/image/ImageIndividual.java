/*
package com.shadorc.ai.image;

import com.shadorc.ai.Chromosome;
import com.shadorc.ai.Individual;

import java.util.concurrent.ThreadLocalRandom;

public class ImageIndividual extends Individual<Integer[]> {

    public ImageIndividual(final ImageAlgorithm algorithm, final Chromosome<Integer[]> chromosome) {
        super(algorithm, chromosome);
    }

    public ImageIndividual(final ImageAlgorithm algorithm) {
        super(algorithm);
    }

    @Override
    public ImageIndividual mate(final Individual<Integer[]> partner) {
        final ImageData childChromosome = new ImageData(this.getAlgorithm().getTarget().getWidth(),
                this.algorithm.getTarget().getHeight());

        for (int x = 0; x < childChromosome.getWidth(); x++) {
            for (int y = 0; y < childChromosome.getHeight(); y++) {
                final int percentage = ThreadLocalRandom.current().nextInt(100);

                if (percentage < 49) {
                    childChromosome.setColor(x, y, this.getChromosome().getColor(x, y));
                } else if (percentage < 98) {
                    childChromosome.setColor(x, y, partner.getChromosome().getColor(x, y));
                } else {
                    childChromosome.setColor(x, y, this.algorithm.mutatedGenes());
                }
            }
        }
        return new ImageIndividual(this.algorithm, childChromosome);
    }

    @Override
    protected double computeFitness() {
        return this.getChromosome().distance(this.algorithm.getTarget());
    }
}
*/
