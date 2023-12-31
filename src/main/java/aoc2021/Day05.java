package aoc2021;

import aoc2021.tools.Input;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day05 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day05.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    public record Line(int x1, int y1, int x2, int y2) {

        public boolean contains(int x, int y) {
            int minY = Math.min(y1, y2);
            int maxY = Math.max(y1, y2);
            if (y < minY || y > maxY) {
                return false;
            }
            int minX = Math.min(x1, x2);
            int maxX = Math.max(x1, x2);
            if (x < minX || x > maxX) {
                return false;
            }
            if (x1 == x2 || y1 == y2) {
                return true;
            }
            if ((x1 < x2 && y1 < y2) || (x1 > x2 && y1 > y2)) {
                return x - minX == y - minY;
            }
            return x - minX == maxY - y;
        }
    }

    private static final Pattern pattern = Pattern.compile("(-?\\d+),(-?\\d+) -> (-?\\d+),(-?\\d+)");

    public static long getPart1(List<String> input) {
        List<Line> lines = parse(input);
        lines = lines.stream().filter(line -> line.x1 == line.x2 || line.y1 == line.y2).toList();
        return count(lines);
    }

    private static int count(List<Line> lines) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Line line : lines) {
            minX = Math.min(minX, Math.min(line.x1, line.x2));
            maxX = Math.max(maxX, Math.max(line.x1, line.x2));
            minY = Math.min(minY, Math.min(line.y1, line.y2));
            maxY = Math.max(maxY, Math.max(line.y1, line.y2));
        }
        int count = 0;
        for (int x = minX; x <= maxX; x++) {
            int finalX = x;
            List<Line> onX = lines.stream()
                    .filter(line -> Math.min(line.x1, line.x2) <= finalX && Math.max(line.x1, line.x2) >= finalX)
                    .toList();
            if (onX.isEmpty()) {
                continue;
            }
            for (int y = minY; y <= maxY; y++) {
                int finalY = y;
                List<Line> onY = onX.stream().filter(line -> line.contains(finalX, finalY)).toList();
                if (onY.size() > 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private static List<Line> parse(List<String> input) {
        return input.stream()
                .map(pattern::matcher)
                .filter(Matcher::matches)
                .map(matcher -> new Line(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
                        Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4))))
                .toList();
    }

    public static long getPart2(List<String> input) {
        return count(parse(input));
    }
}