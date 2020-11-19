package com.shadorc.ai.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StringAlgorithm {

    public static final String GENES = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ 1234567890, .-;:_!\"#%&/()=?@${[]}*";
    public static final String TARGET = "My name is PUCHI *Drop the bass*";
    public static final int POPULATION_SIZE = 100;

    public static char mutatedGenes() {
        return StringAlgorithm.GENES.charAt(ThreadLocalRandom.current().nextInt(StringAlgorithm.GENES.length()));
    }

    public static String createGenome() {
        final StringBuilder gnome = new StringBuilder();
        for (int i = 0; i < StringAlgorithm.TARGET.length(); i++) {
            gnome.append(StringAlgorithm.mutatedGenes());
        }
        return gnome.toString();
    }

    public static void compute() {
        int generation = 0;
        final List<StringIndividual> population = new ArrayList<>(StringAlgorithm.POPULATION_SIZE);
        final List<StringIndividual> newGeneration = new ArrayList<>(StringAlgorithm.POPULATION_SIZE);

        for (int i = 0; i < StringAlgorithm.POPULATION_SIZE; i++) {
            population.add(new StringIndividual(StringAlgorithm.createGenome()));
        }

        final int eliteCount = (10 * StringAlgorithm.POPULATION_SIZE) / 100;
        final int offspringCount = (90 * StringAlgorithm.POPULATION_SIZE) * 100;
        final int topTier = StringAlgorithm.POPULATION_SIZE / 2;
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
