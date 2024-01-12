package aoc2021;

import aoc2021.tools.CharMatrix;
import aoc2021.tools.Coord2D;
import aoc2021.tools.Input;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day13 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day13.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    public static long getPart1(List<String> input) {
        Set<Coord2D> points = parsePoints(input);
        for (String line : input) {
            if (line.startsWith("fold along x=")) {
                points = foldPointsAlongX(points, Integer.parseInt(line.substring(13)));
                return points.size();
            }
            if (line.startsWith("fold along y=")) {
                points = foldPointsAlongY(points, Integer.parseInt(line.substring(13)));
                return points.size();
            }
        }
        return points.size();
    }

    public static long getPart2(List<String> input) {
        Set<Coord2D> points = parsePoints(input);
        for (String line : input) {
            if (line.startsWith("fold along x=")) {
                points = foldPointsAlongX(points, Integer.parseInt(line.substring(13)));
            }
            if (line.startsWith("fold along y=")) {
                points = foldPointsAlongY(points, Integer.parseInt(line.substring(13)));
            }
        }
        int width = points.stream().mapToInt(p -> (int) p.x()).max().orElseThrow() + 1;
        int height = points.stream().mapToInt(p -> (int) p.y()).max().orElseThrow() + 1;
        CharMatrix matrix = new CharMatrix(width, height, ' ');
        for (Coord2D point : points) {
            matrix.set(point, '#');
        }
        System.out.println(matrix);
        return 0;
    }

    private static Set<Coord2D> foldPointsAlongX(Set<Coord2D> points, long foldX) {
        Set<Coord2D> result = new HashSet<>();
        for (Coord2D point : points) {
            if (point.x() > foldX) {
                result.add(new Coord2D(foldX * 2 - point.x(), point.y()));
            } else {
                result.add(point);
            }
        }
        return result;
    }

    private static Set<Coord2D> foldPointsAlongY(Set<Coord2D> points, long foldY) {
        Set<Coord2D> result = new HashSet<>();
        for (Coord2D point : points) {
            if (point.y() > foldY) {
                result.add(new Coord2D(point.x(), foldY * 2 - point.y()));
            } else {
                result.add(point);
            }
        }
        return result;
    }

    private static Set<Coord2D> parsePoints(List<String> input) {
        Set<Coord2D> points = new HashSet<>();
        for (String line : input) {
            int comma = line.indexOf(",");
            if (comma > 0) {
                points.add(new Coord2D(
                        Integer.parseInt(line.substring(0, comma)),
                        Integer.parseInt(line.substring(comma + 1))));
            }
        }
        return points;
    }
}