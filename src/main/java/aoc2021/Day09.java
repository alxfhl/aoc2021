package aoc2021;

import aoc2021.tools.CharMatrix;
import aoc2021.tools.Coord2D;
import aoc2021.tools.Input;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Day09 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day09.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    public static long getPart1(List<String> input) {
        CharMatrix map = CharMatrix.valueOf(input);
        long sum = 0;
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Coord2D coord2D = new Coord2D(x, y);
                char ch = map.get(coord2D);
                if (coord2D.getNeighbors().stream()
                        .allMatch(neighbor -> map.isOutside(neighbor) || map.get(neighbor) > ch)) {
                    sum += (ch - '0') + 1;
                }
            }
        }
        return sum;
    }

    public static long getPart2(List<String> input) {
        CharMatrix map = CharMatrix.valueOf(input);
        List<Long> basins = new ArrayList<>();
        while (true) {
            Coord2D coord = map.findFirst(ch -> ch != '9');
            if (coord == null) {
                break;
            }
            var todo = new LinkedHashSet<Coord2D>();
            todo.add(coord);
            long count = 0;
            while (!todo.isEmpty()) {
                Coord2D next = todo.removeLast();
                map.set(next, '9');
                count++;
                for (Coord2D neighbor : next.getNeighbors()) {
                    if (map.isInside(neighbor) && map.get(neighbor) != '9') {
                        todo.add(neighbor);
                    }
                }
            }
            basins.add(count);
        }
        basins.sort(Long::compare);
        return basins.removeLast() * basins.removeLast() * basins.removeLast();
    }
}