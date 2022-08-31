package com.shadorc.ai;

public abstract class Individual<Gene> implements Comparable<Individual<Gene>> {

    private final GeneticAlgorithm<Gene> algorithm;
    private final Chromosome<Gene> chromosome;
    private double fitness;

    public Individual(final GeneticAlgorithm<Gene> algorithm, final Chromosome<Gene> chromosome) {
        this.algorithm = algorithm;
        this.chromosome = chromosome;
    }

    public Individual(final GeneticAlgorithm<Gene> algorithm) {
        this(algorithm, algorithm.createChromosome());
    }

    public abstract Individual<Gene> mate(final Individual<Gene> partner);

    protected abstract double computeFitness();

    public double getFitness() {
        if (this.fitness == 0.0) {
            this.fitness = this.computeFitness();
        }
        return this.fitness;
    }

    public GeneticAlgorithm<Gene> getAlgorithm() {
        return this.algorithm;
    }

    public Chromosome<Gene> getChromosome() {
        return this.chromosome;
    }

    @Override
    public int compareTo(final Individual<Gene> other) {
        return Double.compare(this.getFitness(), other.getFitness());
    }

}
