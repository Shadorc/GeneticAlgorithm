package com.shadorc.ai;

public abstract class Individual<T, R> implements Comparable<Individual<T, R>> {

    protected final GeneticAlgorithm<T, R> algorithm;
    private final T chromosome;
    private final long fitness;

    public Individual(final GeneticAlgorithm<T, R> algorithm, final T chromosome) {
        this.algorithm = algorithm;
        this.chromosome = chromosome;
        this.fitness = this.computeFitness();
    }

    public abstract Individual<T, R> mate(final Individual<T, R> partner);

    protected abstract long computeFitness();

    public long getFitness() {
        return this.fitness;
    }

    public T getChromosome() {
        return this.chromosome;
    }

    @Override
    public int compareTo(Individual<T, R> other) {
        return Long.compare(this.getFitness(), other.getFitness());
    }

}
