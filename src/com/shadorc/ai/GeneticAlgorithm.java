package com.shadorc.ai;

public abstract class GeneticAlgorithm<T, R> {

    protected final T target;

    public GeneticAlgorithm(final T target) {
        this.target = target;
    }

    public T getTarget() {
        return this.target;
    }

    public abstract R mutatedGenes();

    public abstract T createGenome();

    public abstract void compute();

}
