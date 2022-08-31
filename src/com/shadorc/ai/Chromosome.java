package com.shadorc.ai;

import java.util.List;

public abstract class Chromosome<Gene> {

    private final List<Gene> genes;

    public Chromosome(final List<Gene> genes) {
        this.genes = genes;
    }

    public abstract Gene mutatedGenes();

    public List<Gene> getGenes() {
        return this.genes;
    }

    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder();
        for (final Gene gene : this.genes) {
            str.append(gene);
        }
        return str.toString();
    }
}
