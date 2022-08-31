package com.shadorc.ai.string;

import com.shadorc.ai.Individual;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StringIndividual extends Individual<Character> {

    public StringIndividual(final StringAlgorithm algorithm, final StringChromosome chromosome) {
        super(algorithm, chromosome);
    }

    public StringIndividual(final StringAlgorithm algorithm) {
        super(algorithm);
    }

    @Override
    public StringIndividual mate(final Individual<Character> partner) {
        final StringBuilder childChromosome = new StringBuilder();

        for (int i = 0; i < this.getChromosome().getGenes().size(); ++i) {
            final int percentage = ThreadLocalRandom.current().nextInt(100);

            if (percentage < 45) {
                childChromosome.append(this.getChromosome().getGenes().get(i));
            } else if (percentage < 90) {
                childChromosome.append(partner.getChromosome().getGenes().get(i));
            } else {
                childChromosome.append(this.getChromosome().mutatedGenes());
            }
        }

        return new StringIndividual((StringAlgorithm) this.getAlgorithm(),
                new StringChromosome(childChromosome.toString()));
    }

    @Override
    protected double computeFitness() {
        final List<Character> targetGenes = this.getAlgorithm().getTarget().getGenes();

        int fitness = 0;
        for (int i = 0; i < targetGenes.size(); ++i) {
            if (this.getChromosome().getGenes().get(i) != targetGenes.get(i)) {
                fitness++;
            }
        }

        return fitness;
    }
}
