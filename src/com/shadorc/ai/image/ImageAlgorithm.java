package com.shadorc.ai.image;

import com.shadorc.ai.GeneticAlgorithm;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class ImageAlgorithm extends GeneticAlgorithm<ImageData, Integer[]> {

    private static final Executor THREAD_POOL = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static final int POPULATION_SIZE = 50;
    public static final Integer[] GENES = {255, 255, 255}; // RGB

    public ImageAlgorithm(final String imagePath) throws IOException {
        super(new ImageData(ImageIO.read(new File(imagePath))));
    }

    @Override
    public Integer[] mutatedGenes() {
        final Integer[] genes = new Integer[GENES.length];
        for (int i = 0; i < genes.length; i++) {
            genes[i] = ThreadLocalRandom.current().nextInt(GENES[i]);
        }
        return genes;
    }

    @Override
    public ImageData createGenome() {
        final ImageData genome = new ImageData(this.target.getWidth(), this.target.getHeight());
        for (int x = 0; x < genome.getWidth(); x++) {
            for (int y = 0; y < genome.getHeight(); y++) {
                genome.setColor(x, y, this.mutatedGenes());
            }
        }
        return genome;
    }

    @Override
    public void compute() {
        final ImageFrame frame = new ImageFrame(this.target.toBufferedImage());

        int generation = 0;
        final List<ImageIndividual> population = Collections.synchronizedList(new ArrayList<>(ImageAlgorithm.POPULATION_SIZE));
        final List<ImageIndividual> newGeneration = Collections.synchronizedList(new ArrayList<>(ImageAlgorithm.POPULATION_SIZE));

        System.out.println("Generating initial population");
        final CountDownLatch initialLatch = new CountDownLatch(ImageAlgorithm.POPULATION_SIZE);
        for (int i = 0; i < ImageAlgorithm.POPULATION_SIZE; i++) {
            THREAD_POOL.execute(() -> {
                population.add(new ImageIndividual(this, this.createGenome()));
                initialLatch.countDown();
            });
        }

        try {
            initialLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        final int eliteCount = (10 * POPULATION_SIZE) / 100;
        final int offspringCount = (90 * POPULATION_SIZE) / 100;
        final int topTier = POPULATION_SIZE / 2;

        long start;
        while (true) {
            start = System.currentTimeMillis();

            Collections.sort(population);

            if (population.get(0).getFitness() <= 0) {
                break;
            }

            newGeneration.addAll(population.subList(0, eliteCount));

            final CountDownLatch latch = new CountDownLatch(offspringCount);
            for (int i = 0; i < offspringCount; i++) {
                THREAD_POOL.execute(() -> {
                    final ImageIndividual parent1 = population.get(ThreadLocalRandom.current().nextInt(topTier));
                    final ImageIndividual parent2 = population.get(ThreadLocalRandom.current().nextInt(topTier));
                    final ImageIndividual offspring = parent1.mate(parent2);
                    newGeneration.add(offspring);
                    latch.countDown();
                });
            }

            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            population.clear();
            population.addAll(newGeneration);
            newGeneration.clear();

            if (generation % 500 == 0) {
                frame.updateImage(population.get(0).getChromosome().toBufferedImage());
                System.out.println("Generation: " + generation
                        + " | Fitness: " + population.get(0).getFitness()
                        + " | Elapsed: " + (System.currentTimeMillis() - start));
            }

            generation++;
        }

        System.out.println("Generation: " + generation + " | Fitness: " + population.get(0).getFitness());
    }

}
