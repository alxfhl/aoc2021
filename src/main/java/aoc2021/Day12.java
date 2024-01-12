package aoc2021;

import aoc2021.tools.Input;

import java.util.*;

import static java.util.stream.Collectors.joining;

public class Day12 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day12.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    record Cave(String name, boolean big, List<Cave> connections) {
        public Cave(String name) {
            this(name, Character.isUpperCase(name.charAt(0)), new ArrayList<>());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            return Objects.equals(name, ((Cave) o).name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "Cave{name: " + name + ", neighbors: "
                    + connections.stream().map(Cave::name).collect(joining(", ")) + "}";
        }
    }

    public static long getPart1(List<String> input) {
        Map<String, Cave> caves = parse(input);
        Cave start = caves.get("start");
        Cave end = caves.get("end");
        ArrayList<Cave> way = new ArrayList<>();
        way.add(start);
        Set<List<Cave>> ways = findWays(way, end);
        return ways.size();
    }

    private static Set<List<Cave>> findWays(List<Cave> prefixWay, Cave end) {
        Cave position = prefixWay.getLast();
        if (position == end) {
            return Set.of(List.copyOf(prefixWay));
        }
        Set<List<Cave>> ways = new HashSet<>();
        for (Cave neighbor : position.connections()) {
            if (!neighbor.big() && prefixWay.contains(neighbor)) {
                continue;
            }
            prefixWay.add(neighbor);
            ways.addAll(findWays(prefixWay, end));
            prefixWay.removeLast();
        }
        return ways;
    }

    private static Map<String, Cave> parse(List<String> input) {
        Map<String, List<String>> connections = new HashMap<>();
        for (String line : input) {
            int index = line.indexOf('-');
            String a = line.substring(0, index);
            String b = line.substring(index + 1);
            connections.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            connections.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }
        Map<String, Cave> caves = new HashMap<>();
        for (String name : connections.keySet()) {
            caves.put(name, new Cave(name));
        }
        for (var entry : connections.entrySet()) {
            caves.get(entry.getKey()).connections().addAll(entry.getValue().stream().map(caves::get).toList());
        }
        return caves;
    }

    public static long getPart2(List<String> input) {
        Map<String, Cave> caves = parse(input);
        Cave start = caves.get("start");
        Cave end = caves.get("end");
        ArrayList<Cave> way = new ArrayList<>();
        way.add(start);
        Set<List<Cave>> ways = findWays(way, end, true);
        return ways.size();
    }

    private static Set<List<Cave>> findWays(List<Cave> prefixWay, Cave end, boolean canDouble) {
        Cave position = prefixWay.getLast();
        if (position == end) {
            return Set.of(List.copyOf(prefixWay));
        }
        Set<List<Cave>> ways = new HashSet<>();
        for (Cave neighbor : position.connections()) {
            boolean isDouble = canDouble && !neighbor.big() && !neighbor.name().equals("start")
                    && prefixWay.contains(neighbor);
            if (isDouble || neighbor.big() || !prefixWay.contains(neighbor)) {
                prefixWay.add(neighbor);
                ways.addAll(findWays(prefixWay, end, canDouble && !isDouble));
                prefixWay.removeLast();
            }
        }
        return ways;
    }
}