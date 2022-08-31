package com.shadorc.ai;

public abstract class GeneticAlgorithm<Gene> {

    private final int populationSize;
    protected final Chromosome<Gene> target;

    public GeneticAlgorithm(final int populationSize, final Chromosome<Gene> target) {
        this.populationSize = populationSize;
        this.target = target;
    }

    public int getPopulationSize() {
        return this.populationSize;
    }

    public Chromosome<Gene> getTarget() {
        return this.target;
    }

    public abstract void compute();

    public abstract Chromosome<Gene> createChromosome();

}
