package com.shadorc.ai.string;

import com.shadorc.ai.GeneticAlgorithm;
import com.shadorc.ai.Individual;

import java.util.concurrent.ThreadLocalRandom;

public class StringIndividual extends Individual<String, Character> {

    public StringIndividual(final GeneticAlgorithm<String, Character> algorithm, final String chromosome) {
        super(algorithm, chromosome);
    }

    @Override
    public StringIndividual mate(final Individual<String, Character> partner) {
        final StringBuilder childChromosome = new StringBuilder();

        for (int i = 0; i < this.getChromosome().length(); i++) {
            final int percentage = ThreadLocalRandom.current().nextInt(100);

            if (percentage < 45) {
                childChromosome.append(this.getChromosome().charAt(i));
            } else if (percentage < 90) {
                childChromosome.append(partner.getChromosome().charAt(i));
            } else {
                childChromosome.append(this.algorithm.mutatedGenes());
            }
        }
        return new StringIndividual(this.algorithm, childChromosome.toString());
    }

    @Override
    protected long computeFitness() {
        final long size = this.algorithm.getTarget().length();
        int fitness = 0;
        for (int i = 0; i < size; i++) {
            if (this.getChromosome().charAt(i) != this.algorithm.getTarget().charAt(i)) {
                fitness++;
            }
        }
        return fitness;
    }
}
