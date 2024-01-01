package aoc2021;

import aoc2021.tools.Input;
import aoc2021.tools.Parse;

import java.util.List;

public class Day07 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day07.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    interface FuelCost {
        int getFuel(List<Integer> positions, int i);
    }

    public static long getPart1(List<String> input) {
        return findBest(input, Day07::getFuel1);
    }

    private static int getFuel1(List<Integer> positions, int i) {
        return positions.stream().mapToInt(pos -> Math.abs(pos - i)).sum();
    }

    public static long getPart2(List<String> input) {
        return findBest(input, Day07::getFuel2);
    }

    private static int getFuel2(List<Integer> positions, int i) {
        return positions.stream().mapToInt(pos -> {
            int delta = Math.abs(pos - i);
            return delta * (delta + 1) / 2;
        }).sum();
    }

    private static int findBest(List<String> input, FuelCost fuelCost) {
        List<Integer> positions = Parse.getIntegers(input.getFirst());
        positions.sort(Integer::compareTo);
        int position = positions.get(positions.size() / 2);
        int left = fuelCost.getFuel(positions, position - 1);
        int middle = fuelCost.getFuel(positions, position);
        while (middle > left) {
            position--;
            middle = left;
            left = fuelCost.getFuel(positions, position - 1);
        }
        int right = fuelCost.getFuel(positions, position + 1);
        while (middle > right) {
            position++;
            middle = right;
            right = fuelCost.getFuel(positions, position + 1);
        }
        return middle;
    }
}