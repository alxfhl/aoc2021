package aoc2021;

import aoc2021.tools.CharMatrix;
import aoc2021.tools.Coord2D;
import aoc2021.tools.Grid;
import aoc2021.tools.Input;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Day15 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day15.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    public static long getPart1(List<String> input) {
        CharMatrix matrix = CharMatrix.valueOf(input);
        return solve(matrix);
    }

    record Todo(Coord2D pos, int value) {

    }

    private static Integer solve(CharMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        Grid<Integer> best = new Grid<>(width, height, Integer.MAX_VALUE);
        best.set(0, 0, 0);
        var todos = new PriorityQueue<>(Comparator.comparing(Todo::value));
        todos.add(new Todo(new Coord2D(0, 0), 0));
        while (!todos.isEmpty()) {
            Todo todo = todos.poll();
            int value = best.get(todo.pos());
            for (Coord2D neighbor : todo.pos().getNeighbors()) {
                if (neighbor.isInGrid(width, height)) {
                    int newValue = value + (matrix.get(neighbor) - '0');
                    if (newValue < best.get(neighbor)) {
                        best.set(neighbor, newValue);
                        todos.add(new Todo(neighbor, newValue));
                    }
                }
            }
        }
        return best.get(width - 1, height - 1);
    }

    public static long getPart2(List<String> input) {
        CharMatrix matrix = CharMatrix.valueOf(input);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        CharMatrix big = new CharMatrix(width * 5, height * 5, ' ');
        for (int dx = 0; dx < 5; dx++) {
            int topX = dx * width;
            for (int dy = 0; dy < 5; dy++) {
                int topY = dy * height;
                int shift = dx + dy;
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        int oldCh = matrix.get(x, y) - '1';
                        int newCh = (oldCh + shift) % 9 + '1';
                        big.set(x + topX, y + topY, (char) newCh);
                    }
                }
            }
        }
        return solve(big);
    }
}