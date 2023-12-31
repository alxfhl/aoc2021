package aoc2021;

import aoc2021.tools.Cuboid;
import aoc2021.tools.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day22 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day22.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    private static final Pattern pattern =
            Pattern.compile("(on|off) x=(-?\\d+)..(-?\\d+),y=(-?\\d+)..(-?\\d+),z=(-?\\d+)..(-?\\d+)");

    public static long getPart1(List<String> lines) {
        List<Cuboid> volumes = new ArrayList<>();
        for (String line : lines) {
            volumes = apply(volumes, line, -50, 50);
        }
        return getTotalVolume(volumes);
    }

    public static long getTotalVolume(List<Cuboid> volumes) {
        return volumes.stream().mapToLong(Cuboid::getVolume).sum();
    }

    public static List<Cuboid> apply(List<Cuboid> volumes, String line, int min, int max) {
        Matcher matcher = pattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid input: " + line);
        }
        boolean add = matcher.group(1).equals("on");
        int x1 = Integer.parseInt(matcher.group(2));
        int y1 = Integer.parseInt(matcher.group(4));
        int z1 = Integer.parseInt(matcher.group(6));
        int x2 = Integer.parseInt(matcher.group(3));
        int y2 = Integer.parseInt(matcher.group(5));
        int z2 = Integer.parseInt(matcher.group(7));
        if (x2 < min || x1 > max || y2 < min || y1 > max || z2 < min || z1 > max) {
            return volumes;
        }
        Cuboid cuboid = new Cuboid(
                Math.max(x1, min), Math.max(y1, min), Math.max(z1, min), Math.min(x2, max),
                Math.min(y2, max),
                Math.min(z2, max));
        volumes = volumes.stream().flatMap(volume -> volume.minus(cuboid).stream()).collect(Collectors.toList());
        if (add) {
            volumes.add(cuboid);
        }
        return volumes;
    }

    public static long getPart2(List<String> lines) {
        List<Cuboid> volumes = new ArrayList<>();
        for (String line : lines) {
            volumes = apply(volumes, line, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        return getTotalVolume(volumes);
    }
}