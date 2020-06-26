package com.shadorc.ai;

import java.util.Comparator;

public abstract class Individual<T> {

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

    public static class IndividualComparator implements Comparator<Individual<?>> {

        @Override
        public int compare(final Individual<?> individual1, final Individual<?> individual2) {
            return Long.compare(individual1.getFitness(), individual2.getFitness());
        }
    }


}
