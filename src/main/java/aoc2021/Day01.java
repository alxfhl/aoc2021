package aoc2021;

import aoc2021.tools.Input;

import java.util.List;

public class Day01 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day01.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    public static long getPart1(List<String> lines) {
        return countIncreasesWithOffset(lines, 1);
    }

    public static long getPart2(List<String> lines) {
        return countIncreasesWithOffset(lines, 3);
    }

    private static long countIncreasesWithOffset(List<String> lines, int offset) {
        List<Integer> list = lines.stream().map(Integer::parseInt).toList();
        long count = 0;
        for (int i = offset; i < list.size(); i++) {
            if (list.get(i) > list.get(i - offset)) {
                count++;
            }
        }
        return count;
    }

}
