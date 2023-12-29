package aoc2021;

import aoc2021.tools.CharMatrix;
import aoc2021.tools.Input;

import java.util.List;

public class Day25 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day25.class);
        System.out.println("part 1: " + getPart1(input));
    }

    public static long getPart1(List<String> lines) {
        CharMatrix situation = CharMatrix.valueOf(lines);
        int steps = 0;
        while (true) {
            CharMatrix newSituation = step(situation);
            steps++;
            if (newSituation.equals(situation)) {
                break;
            }
            situation = newSituation;
        }
        return steps;
    }

    public static CharMatrix step(CharMatrix situation) {
        CharMatrix result = new CharMatrix(situation);
        int width = result.getWidth();
        int height = result.getHeight();
        boolean[] moving = new boolean[width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (result.get(x, y) == '>' && result.get((x + 1) % width, y) == '.') {
                    moving[x] = true;
                }
            }
            for (int x = 0; x < width; x++) {
                if (moving[x]) {
                    result.set(x, y, '.');
                    result.set((x + 1) % width, y, '>');
                    moving[x] = false;
                }
            }
        }
        moving = new boolean[height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (result.get(x, y) == 'v' && result.get(x, (y + 1) % height) == '.') {
                    moving[y] = true;
                }
            }
            for (int y = 0; y < height; y++) {
                if (moving[y]) {
                    result.set(x, y, '.');
                    result.set(x, (y + 1) % height, 'v');
                    moving[y] = false;
                }
            }
        }
        return result;
    }
}