package com.shadorc.ai.image;

import com.shadorc.ai.GeneticAlgorithm;
import com.shadorc.ai.Individual;

import java.util.concurrent.ThreadLocalRandom;

public class ImageIndividual extends Individual<ImageData, Integer[]> {

    public ImageIndividual(final GeneticAlgorithm<ImageData, Integer[]> algorithm, final ImageData chromosome) {
        super(algorithm, chromosome);
    }

    @Override
    public ImageIndividual mate(final Individual<ImageData, Integer[]> partner) {
        final ImageData childChromosome = new ImageData(this.algorithm.getTarget().getWidth(),
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
    protected long computeFitness() {
        return this.getChromosome().distance(this.algorithm.getTarget());
    }
}
