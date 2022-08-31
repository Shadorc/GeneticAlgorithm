package com.shadorc.ai.path;

import com.shadorc.ai.Chromosome;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PathChromosome extends Chromosome<City> {

    private static final List<City> DEFAULT_GENES = List.of(
            new City(60, 200),
            new City(180, 200),
            new City(80, 180),
            new City(140, 180),
            new City(20, 160),
            new City(100, 160),
            new City(200, 160),
            new City(140, 140),
            new City(40, 120),
            new City(100, 120),
            new City(180, 100),
            new City(60, 80),
            new City(120, 80),
            new City(180, 60),
            new City(20, 40),
            new City(100, 40),
            new City(200, 40),
            new City(20, 20),
            new City(60, 20),
            new City(160, 20));
    private static final List<City> TEMP_LIST = new ArrayList<>(DEFAULT_GENES.size());

    public PathChromosome() {
        super(new ArrayList<>(DEFAULT_GENES.size()));

        for (int i = 0; i < DEFAULT_GENES.size(); ++i) {
            this.getGenes().add(null);
        }
    }

    public void addCity(final int index, final City city) {
        if (this.contains(city)) {
            throw new RuntimeException("Path cannot contain twice the same city");
        }

        this.getGenes().set(index, city);
    }

    public boolean contains(final City city) {
        return this.getGenes().contains(city);
    }

    @Override
    public City mutatedGenes() {
        if (TEMP_LIST.isEmpty()) {
            TEMP_LIST.addAll(DEFAULT_GENES);
        }

        final int rand = ThreadLocalRandom.current().nextInt(TEMP_LIST.size());
        final City city = TEMP_LIST.get(rand);
        TEMP_LIST.remove(city);
        return city;
    }
}
