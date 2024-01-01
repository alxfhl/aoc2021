package aoc2021;

import aoc2021.tools.CharMatrix;
import aoc2021.tools.Input;

import java.util.List;

public class Day20 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day20.class);
        System.out.println("part 1: " + getPart1(input, 2));
        System.out.println("part 2: " + getPart1(input, 50));
    }

    public static long getPart1(List<String> lines, int steps) {
        CharMatrix algorithm = CharMatrix.valueOf(lines.subList(0, 1));
        CharMatrix matrix = CharMatrix.valueOf(lines.subList(2, lines.size()));
        char background = '.';
        for (int step = 1; step <= steps; step++) {
            char newBackground = background == '.' ? algorithm.get(0, 0) : algorithm.get(511, 0);
            CharMatrix newMatrix = new CharMatrix(matrix.getWidth() + 2, matrix.getHeight() + 2, 'X');
            for (int x = 0; x < newMatrix.getWidth(); x++) {
                for (int y = 0; y < newMatrix.getHeight(); y++) {
                    int index = get(matrix, background, x + 1, y + 1)
                            + 2 * get(matrix, background, x, y + 1)
                            + 4 * get(matrix, background, x - 1, y + 1)
                            + 8 * get(matrix, background, x + 1, y)
                            + 16 * get(matrix, background, x, y)
                            + 32 * get(matrix, background, x - 1, y)
                            + 64 * get(matrix, background, x + 1, y - 1)
                            + 128 * get(matrix, background, x, y - 1)
                            + 256 * get(matrix, background, x - 1, y - 1);
                    newMatrix.set(x, y, algorithm.get(index, 0));
                }
            }
            background = newBackground;
            matrix = newMatrix;
        }
        return matrix.count('#');
    }

    private static int get(CharMatrix matrix, char background, int x, int y) {
        char ch = (x < 1 || y < 1 || x > matrix.getWidth() || y > matrix.getHeight()) ? background
                : matrix.get(x - 1, y - 1);
        return ch == '.' ? 0 : 1;
    }
}