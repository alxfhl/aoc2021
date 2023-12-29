package aoc2021;

import aoc2021.tools.Input;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Day24 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day24.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    record MysteriousOperation(long a, long b, long c) {
        long apply(long before, long w) {
            long x = before % 26 + b;
            long z = before / a;
            x = (x == w) ? 0 : 1;
            long y = 25 * x + 1;
            z *= y;
            y = (w + c) * x;
            z += y;
            return z;
        }
    }

    public static String getPart1(List<String> lines) {
        return getPartSmart(lines, true);
    }

    public static String getPart2(List<String> lines) {
        return getPartSmart(lines, false);
    }

    private static String getPartSmart(List<String> lines, boolean max) {
        Map<Long, String> states = new HashMap<>();
        states.put(0L, "");
        for (int i = 0; i < 14; i++) {
            MysteriousOperation operation = new MysteriousOperation(
                    Long.parseLong(lines.get(i * 18 + 4).substring(6)),
                    Long.parseLong(lines.get(i * 18 + 5).substring(6)),
                    Long.parseLong(lines.get(i * 18 + 15).substring(6)));

            Map<Long, String> newStates = new HashMap<>();
            // for an input instruction, the possible states multiply
            for (var entry : states.entrySet()) {
                for (int next = 1; next <= 9; next++) {
                    long newState = operation.apply(entry.getKey(), next);
                    String newInput = entry.getValue() + next;
                    if (max) {
                        newStates.compute(newState, (k, v) -> v == null || newInput.compareTo(v) > 0 ? newInput : v);
                    } else {
                        newStates.compute(newState, (k, v) -> v == null || newInput.compareTo(v) < 0 ? newInput : v);
                    }
                }
            }
            states = newStates;
            System.out.println("# states: " + newStates.size());
        }
        Stream<String> stringStream = states.entrySet().stream().filter(entry -> entry.getKey() == 0).map(Map.Entry::getValue);
        if (max) {
            return stringStream.max(String::compareTo).orElseThrow();
        }
        return stringStream.min(String::compareTo).orElseThrow();
    }
}