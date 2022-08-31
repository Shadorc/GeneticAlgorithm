package com.shadorc.ai.path;

import com.shadorc.ai.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PathAlgorithm extends GeneticAlgorithm<City> {

    private static final float MUTATION_RATE = 0.015f;

    public PathAlgorithm() {
        super(100, new PathChromosome());
    }

    @Override
    public PathChromosome createChromosome() {
        final PathChromosome chromosome = new PathChromosome();
        for (int i = 0; i < this.getTarget().getGenes().size(); ++i) {
            chromosome.addCity(i, this.getTarget().mutatedGenes());
        }
        return chromosome;
    }

    @Override
    public void compute() {
        final PathFrame frame = new PathFrame();

        int generation = 0;
        final List<PathIndividual> population = new ArrayList<>(this.getPopulationSize());
        final List<PathIndividual> newGeneration = new ArrayList<>(this.getPopulationSize());

        for (int i = 0; i < this.getPopulationSize(); ++i) {
            population.add(new PathIndividual(this));
        }

        final int eliteCount = (10 * this.getPopulationSize()) / 100;
        final int topTier = this.getPopulationSize() / 2;
        while (true) {
            Collections.sort(population);

            if (population.get(0).getFitness() <= 0) {
                break;
            }

            for (int i = 0; i < eliteCount; ++i) {
                newGeneration.add(population.get(i));
            }

            for (int i = eliteCount; i < this.getPopulationSize(); ++i) {
                final PathIndividual parent1 = population.get(ThreadLocalRandom.current().nextInt(topTier));
                final PathIndividual parent2 = population.get(ThreadLocalRandom.current().nextInt(topTier));
                final PathIndividual offspring = parent1.mate(parent2);
                newGeneration.add(offspring);
            }

            for (int i = eliteCount; i < this.getPopulationSize(); ++i) {
                final PathIndividual path = newGeneration.get(i);
                for (int tourPos1 = 0; tourPos1 < path.getSize(); ++tourPos1) {
                    if (ThreadLocalRandom.current().nextFloat() < MUTATION_RATE) {
                        final int tourPos2 = (int) (path.getSize() * ThreadLocalRandom.current().nextFloat());

                        final City city1 = path.getCity(tourPos1);
                        final City city2 = path.getCity(tourPos2);

                        path.setCity(tourPos2, city1);
                        path.setCity(tourPos1, city2);
                    }
                }
            }

            population.clear();
            population.addAll(newGeneration);
            newGeneration.clear();

            if (generation % 10 == 0) {
                System.out.println("Generation: " + generation
                        + " | Fitness: " + population.get(0).getFitness()
                        + " | Path: " + population.get(0).getChromosome());

                frame.updateCities(population.get(0).getChromosome().getGenes());
            }

            generation++;
        }

        System.out.println("Generation: " + generation
                + " | Fitness: " + population.get(0).getFitness()
                + " | Path: " + population.get(0).getChromosome());
    }

}
