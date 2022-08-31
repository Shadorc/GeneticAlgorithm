package com.shadorc.ai.string;

import com.shadorc.ai.Chromosome;
import com.shadorc.ai.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StringAlgorithm extends GeneticAlgorithm<Character> {

    public StringAlgorithm(final Chromosome<Character> target) {
        super(100, target);
    }

    @Override
    public StringChromosome createChromosome() {
        final StringBuilder gnome = new StringBuilder();
        for (int i = 0; i < this.getTarget().getGenes().size(); ++i) {
            gnome.append(this.getTarget().mutatedGenes());
        }
        return new StringChromosome(gnome.toString());
    }

    @Override
    public void compute() {
        int generation = 0;
        final List<StringIndividual> population = new ArrayList<>(this.getPopulationSize());
        final List<StringIndividual> newGeneration = new ArrayList<>(this.getPopulationSize());

        for (int i = 0; i < this.getPopulationSize(); i++) {
            population.add(new StringIndividual(this));
        }

        final int eliteCount = (10 * this.getPopulationSize()) / 100;
        final int topTier = this.getPopulationSize() / 2;
        while (true) {
            Collections.sort(population);

            if (population.get(0).getFitness() <= 0) {
                break;
            }

            for (int i = 0; i < eliteCount; i++) {
                newGeneration.add(population.get(i));
            }

            for (int i = eliteCount; i < this.getPopulationSize(); i++) {
                final StringIndividual parent1 = population.get(ThreadLocalRandom.current().nextInt(topTier));
                final StringIndividual parent2 = population.get(ThreadLocalRandom.current().nextInt(topTier));
                final StringIndividual offspring = parent1.mate(parent2);
                newGeneration.add(offspring);
            }

            population.clear();
            population.addAll(newGeneration);
            newGeneration.clear();

            System.out.println("Generation: " + generation
                    + " | String: " + population.get(0).getChromosome()
                    + " | Fitness: " + population.get(0).getFitness());

            generation++;
        }

        System.out.println("Final generation: " + generation
                + " | String: " + population.get(0).getChromosome()
                + " | Fitness: " + population.get(0).getFitness());
    }

}
