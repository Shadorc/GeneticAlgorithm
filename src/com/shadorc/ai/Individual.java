package com.shadorc.ai;

public abstract class Individual<T> implements Comparable<Individual<T>> {

    private final T chromosome;
    private final long fitness;

    public Individual(final T chromosome) {
        this.chromosome = chromosome;
        this.fitness = this.computeFitness();
    }

    public abstract Individual<T> mate(final Individual<T> partner);

    protected abstract long computeFitness();

    public long getFitness() {
        return this.fitness;
    }

    public T getChromosome() {
        return this.chromosome;
    }

    @Override
    public int compareTo(Individual<T> other) {
        return Long.compare(this.getFitness(), other.getFitness());
    }

}
