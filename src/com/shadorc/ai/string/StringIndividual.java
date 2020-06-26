package com.shadorc.ai.string;

import com.shadorc.ai.Individual;

import java.util.concurrent.ThreadLocalRandom;

public class StringIndividual extends Individual<String> {

    public StringIndividual(final String chromosome) {
        super(chromosome);
    }

    @Override
    public StringIndividual mate(final Individual<String> partner) {
        final StringBuilder childChromosome = new StringBuilder();

        for (int i = 0; i < this.getChromosome().length(); i++) {
            final int percentage = ThreadLocalRandom.current().nextInt(100);

            if (percentage < 45) {
                childChromosome.append(this.getChromosome().charAt(i));
            } else if (percentage < 90) {
                childChromosome.append(partner.getChromosome().charAt(i));
            } else {
                childChromosome.append(StringAlgorithm.mutatedGenes());
            }
        }
        return new StringIndividual(childChromosome.toString());
    }

    @Override
    protected long computeFitness() {
        final long size = StringAlgorithm.TARGET.length();
        int fitness = 0;
        for (int i = 0; i < size; i++) {
            if (this.getChromosome().charAt(i) != StringAlgorithm.TARGET.charAt(i)) {
                fitness++;
            }
        }
        return fitness;
    }
}
