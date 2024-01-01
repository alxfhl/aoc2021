package aoc2021;

import aoc2021.tools.Input;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day06 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day06.class);
        System.out.println("part 1: " + getPart1(input, 80));
        System.out.println("part 2: " + getPart1(input, 256));
    }

    public static long getPart1(List<String> input, int days) {
        Map<Integer, Long> counts = new HashMap<>();
        for (String fish : input.getFirst().split(",")) {
            counts.compute(Integer.parseInt(fish), (k, v) -> v == null ? 1 : v + 1);
        }
        for (int day = 1; day <= days; day++) {
            Map<Integer, Long> newCounts = new HashMap<>();
            for (var entry : counts.entrySet()) {
                int age = entry.getKey();
                long count = entry.getValue();
                if (age == 0) {
                    newCounts.compute(6, (k, v) -> v == null ? count : v + count);
                    newCounts.compute(8, (k, v) -> v == null ? count : v + count);
                } else {
                    newCounts.compute(age - 1, (k, v) -> v == null ? count : v + count);
                }
            }
            counts = newCounts;
        }
        return counts.values().stream().mapToLong(i -> i).sum();
    }
}