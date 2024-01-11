package aoc2021;

import aoc2021.tools.Grid;
import aoc2021.tools.Input;

import java.util.ArrayList;
import java.util.List;

public class Day11 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day11.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    public static long getPart1(List<String> input) {
        Grid<Integer> grid = parse(input);
        int flashes = 0;
        for (int step = 0; step < 100; step++) {
            flashes += step(grid);
        }
        return flashes;
    }

    private static Grid<Integer> parse(List<String> input) {
        List<List<Integer>> values = new ArrayList<>();
        for (String line : input) {
            List<Integer> lineValues = new ArrayList<>();
            for (char ch : line.toCharArray()) {
                lineValues.add(ch - '0');
            }
            values.add(lineValues);
        }
        return Grid.valueOf(values);
    }

    private static int step(Grid<Integer> grid) {
        int width = grid.getWidth();
        int height = grid.getHeight();
        Grid<Boolean> flashed = new Grid<>(width, height, false);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid.set(x, y, grid.get(x, y) + 1);
            }
        }
        boolean repeat = true;
        while (repeat) {
            repeat = false;
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (grid.get(x, y) > 9 && !flashed.get(x, y)) {
                        repeat = true;
                        flashed.set(x, y, true);
                        for (int x2 = x - 1; x2 <= x + 1; x2++) {
                            if (x2 < 0 || x2 == width) {
                                continue;
                            }
                            for (int y2 = y - 1; y2 <= y + 1; y2++) {
                                if (y2 >= 0 && y2 < height) {
                                    grid.set(x2, y2, grid.get(x2, y2) + 1);
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (grid.get(x, y) > 9) {
                    grid.set(x, y, 0);
                }
            }
        }
        return (int) flashed.count(true);
    }

    public static long getPart2(List<String> input) {
        Grid<Integer> grid = parse(input);
        int step = 0;
        while (true) {
            step++;
            int flashes = step(grid);
            if (flashes == grid.getWidth() * grid.getHeight()) {
                return step;
            }
        }
    }
}