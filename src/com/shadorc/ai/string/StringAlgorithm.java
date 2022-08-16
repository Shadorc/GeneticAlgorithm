package com.shadorc.ai.string;

import com.shadorc.ai.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StringAlgorithm extends GeneticAlgorithm<String, Character> {

    public static final String GENES = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ 1234567890, .-;:_!\"#%&/()=?@${[]}*";
    public static final int POPULATION_SIZE = 100;

    public StringAlgorithm() {
        super("My name is PUCHI *Drop the bass*");
    }

    @Override
    public Character mutatedGenes() {
        return GENES.charAt(ThreadLocalRandom.current().nextInt(GENES.length()));
    }

    @Override
    public String createGenome() {
        final StringBuilder gnome = new StringBuilder();
        for (int i = 0; i < this.getTarget().length(); i++) {
            gnome.append(this.mutatedGenes());
        }
        return gnome.toString();
    }

    @Override
    public void compute() {
        int generation = 0;
        final List<StringIndividual> population = new ArrayList<>(POPULATION_SIZE);
        final List<StringIndividual> newGeneration = new ArrayList<>(POPULATION_SIZE);

        for (int i = 0; i < POPULATION_SIZE; i++) {
            population.add(new StringIndividual(this, this.createGenome()));
        }

        final int eliteCount = (10 * POPULATION_SIZE) / 100;
        final int offspringCount = (90 * POPULATION_SIZE) * 100;
        final int topTier = POPULATION_SIZE / 2;
        while (true) {
            Collections.sort(population);

            if (population.get(0).getFitness() <= 0) {
                break;
            }

            for (int i = 0; i < eliteCount; i++) {
                newGeneration.add(population.get(i));
            }

            for (int i = 0; i < offspringCount; i++) {
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

        System.out.println("Generation: " + generation
                + " | String: " + population.get(0).getChromosome()
                + " | Fitness: " + population.get(0).getFitness());
    }

}
