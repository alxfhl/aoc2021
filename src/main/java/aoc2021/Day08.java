package aoc2021;

import aoc2021.tools.Input;

import java.util.*;
import java.util.stream.Collectors;

public class Day08 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day08.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    record InputLine(List<Set<String>> patterns, List<Set<String>> output) {
        static InputLine valueOf(String line) {
            int i = line.indexOf('|');
            List<Set<String>> patterns = Arrays.stream(line.substring(0, i - 1).split(" "))
                    .map(InputLine::toSet).toList();
            List<Set<String>> output = Arrays.stream(line.substring(i + 2).split(" "))
                    .map(InputLine::toSet).toList();
            return new InputLine(patterns, output);
        }

        private static Set<String> toSet(String pattern) {
            return Arrays.stream(pattern.split("")).collect(Collectors.toSet());
        }
    }

    public static long getPart1(List<String> input) {
        long count = 0;
        for (InputLine line : input.stream().map(InputLine::valueOf).toList()) {
            count += line.output().stream()
                    .filter(set -> set.size() == 2 || set.size() == 3 || set.size() == 4 || set.size() == 7)
                    .count();
        }
        return count;
    }

    public static long getPart2(List<String> input) {
        long sum = 0;
        for (String line : input) {
            sum += getOutput(line);
        }
        return sum;
    }

    public static int getOutput(String line) {
        InputLine inputLine = InputLine.valueOf(line);
        Map<Set<String>, Integer> map = deduceDigits(inputLine);
        List<Integer> digits = inputLine.output().stream().map(map::get).toList();
        return digits.getFirst() * 1000 + digits.get(1) * 100 + digits.get(2) * 10 + digits.getLast();
    }

    private static Map<Set<String>, Integer> deduceDigits(InputLine line) {
        Map<Set<String>, Integer> pattern2digit = new HashMap<>();
        Set<String> one = line.patterns.stream().filter(p -> p.size() == 2).findAny().orElseThrow();
        Set<String> four = line.patterns.stream().filter(p -> p.size() == 4).findAny().orElseThrow();
        Set<String> seven = line.patterns.stream().filter(p -> p.size() == 3).findAny().orElseThrow();
        Set<String> eight = line.patterns.stream().filter(p -> p.size() == 7).findAny().orElseThrow();
        Set<String> six = line.patterns.stream().filter(p -> p.size() == 6 && !p.containsAll(one)).findAny().orElseThrow();
        Set<String> nine = line.patterns.stream().filter(p -> p.size() == 6 && p.containsAll(four)).findAny().orElseThrow();
        Set<String> zero = line.patterns.stream().filter(p -> p.size() == 6 && p != six && p != nine).findAny().orElseThrow();
        Set<String> three = line.patterns.stream().filter(p -> p.size() == 5 && p.containsAll(one)).findAny().orElseThrow();
        Set<String> five = line.patterns.stream().filter(p -> p.size() == 5 && p != three && nine.containsAll(p)).findAny().orElseThrow();
        Set<String> two = line.patterns.stream().filter(p -> p.size() == 5 && p != three && p != five).findAny().orElseThrow();
        pattern2digit.put(zero, 0);
        pattern2digit.put(one, 1);
        pattern2digit.put(two, 2);
        pattern2digit.put(three, 3);
        pattern2digit.put(four, 4);
        pattern2digit.put(five, 5);
        pattern2digit.put(six, 6);
        pattern2digit.put(seven, 7);
        pattern2digit.put(eight, 8);
        pattern2digit.put(nine, 9);
        return pattern2digit;
    }
}