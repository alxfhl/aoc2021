package aoc2021;

import aoc2021.tools.Input;
import aoc2021.tools.MathUtils;
import aoc2021.tools.Parse;
import aoc2021.tools.Range;

import java.util.List;

public class Day17 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day17.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    public static long getPart1(List<String> input) {
        List<Long> numbers = Parse.getLongs(input.getFirst());
        long dy = Math.abs(numbers.get(2)) - 1;
        return MathUtils.triangular(dy);
    }

    public static long getPart2(List<String> input) {
        List<Long> numbers = Parse.getLongs(input.getFirst());
        Range rangeX = new Range(numbers.get(0), numbers.get(1) + 1);
        Range rangeY = new Range(numbers.get(2), numbers.get(3) + 1);
        int startX = (int) ((Math.sqrt(8 * rangeX.start() + 1) - 1) / 2.0);
        int startY = (int) Math.abs(rangeY.start()) - 1;
        long count = 0;
        for (int dx = startX; dx < rangeX.end(); dx++) {
            for (int dy = startY; dy >= rangeY.start(); dy--) {
                if (hits(dx, dy, rangeX, rangeY)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean hits(int dx, int dy, Range rangeX, Range rangeY) {
        int x = 0;
        int y = 0;
        while (true) {
            x += dx;
            y += dy;
            if (dx > 0) {
                dx--;
            }
            dy--;
            if (rangeX.contains(x) && rangeY.contains(y)) {
                return true;
            }
            if (x >= rangeX.end() || y <= rangeY.start()) {
                return false;
            }
        }
    }
}