package ua.dymohlo.ThePossibilityArchitects.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class Randomizer {
    private final Random random = new Random();

    public Object randomize(List<?> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("The list cannot be null or empty.");
        }
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    public List<Integer> generateUniqueRandomNumbers(int size) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers, random);
        return numbers;
    }
}