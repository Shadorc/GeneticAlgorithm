package com.shadorc.ai.string;

import com.shadorc.ai.Chromosome;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StringChromosome extends Chromosome<Character> {

    private static final List<Character> DEFAULT_GENES = new ArrayList<>();

    static {
        for (int i = 'a'; i <= 'z'; ++i) {
            DEFAULT_GENES.add((char) i);
            DEFAULT_GENES.add(Character.toUpperCase((char) i));
        }
        for (int i = '0'; i <= '9'; ++i) {
            DEFAULT_GENES.add((char) i);
        }
        DEFAULT_GENES.addAll(List.of(',', '.', ' ', '!', '?'));
    }

    public StringChromosome(final String chromosome) {
        super(chromosome.chars()
                .boxed()
                .map(it -> (char) it.intValue())
                .toList());
    }

    @Override
    public Character mutatedGenes() {
        return DEFAULT_GENES.get(ThreadLocalRandom.current().nextInt(DEFAULT_GENES.size()));
    }

}
