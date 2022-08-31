package com.shadorc.ai.path;

import com.shadorc.ai.GeneticAlgorithm;
import com.shadorc.ai.Individual;

import java.util.concurrent.ThreadLocalRandom;

public class PathIndividual extends Individual<City> {

    public PathIndividual(final GeneticAlgorithm<City> algorithm, final PathChromosome chromosome) {
        super(algorithm, chromosome);
    }

    public PathIndividual(final GeneticAlgorithm<City> algorithm) {
        super(algorithm);
    }

    @Override
    public PathIndividual mate(final Individual<City> partner) {
        final PathChromosome chromosome = new PathChromosome();

        final int randPos1 = ThreadLocalRandom.current().nextInt(this.getSize());
        final int randPos2 = ThreadLocalRandom.current().nextInt(this.getSize());
        for (int i = Math.min(randPos1, randPos2); i < Math.max(randPos1, randPos2); ++i) {
            chromosome.addCity(i, this.getCity(i));
        }

        final PathIndividual other = ((PathIndividual) partner);
        for (int i = 0; i < other.getSize(); ++i) {
            final City city = other.getCity(i);
            if (!chromosome.contains(city)) {
                for (int o = 0; o < chromosome.getGenes().size(); ++o) {
                    if (chromosome.getGenes().get(o) == null) {
                        chromosome.addCity(o, city);
                        break;
                    }
                }
            }
        }

        return new PathIndividual(this.getAlgorithm(), chromosome);
    }

    @Override
    protected double computeFitness() {
        double fitness = 0.0;

        for (int cityIndex = 0; cityIndex < this.getSize(); ++cityIndex) {
            final City fromCity = this.getCity(cityIndex);
            final City destinationCity = this.getCity((cityIndex + 1) % this.getSize());
            fitness += fromCity.distance(destinationCity);
        }

        return fitness;
    }

    public City getCity(final int index) {
        return this.getChromosome().getGenes().get(index);
    }

    public void setCity(final int index, final City city) {
        this.getChromosome().getGenes().set(index, city);
    }

    public int getSize() {
        return this.getChromosome().getGenes().size();
    }

}
