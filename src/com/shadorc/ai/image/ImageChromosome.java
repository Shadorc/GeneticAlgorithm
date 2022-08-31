package com.shadorc.ai.image;

import com.shadorc.ai.Chromosome;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ImageChromosome extends Chromosome<Integer[]> {

    private static final Integer[] DEFAULT_GENES = {255, 255, 255}; // RGB

    public ImageChromosome(final List<Integer[]> integers) {
        super(integers);
    }

    @Override
    public Integer[] mutatedGenes() {
        final Integer[] genes = new Integer[DEFAULT_GENES.length];
        for (int i = 0; i < genes.length; ++i) {
            genes[i] = ThreadLocalRandom.current().nextInt(DEFAULT_GENES[i]);
        }
        return genes;
    }

}
