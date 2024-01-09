package aoc2021;

import aoc2021.tools.Input;

import java.util.LinkedList;
import java.util.List;

public class Day10 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day10.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    public static long getPart1(List<String> input) {
        return input.stream().mapToInt(Day10::getPoints).sum();
    }

    private static final List<Character> opening = List.of('(', '{', '[', '<');
    private static final List<Character> closing = List.of(')', '}', ']', '>');

    private static int getPoints(String line) {
        final var stack = new LinkedList<Character>();
        for (int i = 0; i < line.length(); i++) {
            char next = line.charAt(i);
            if (opening.contains(next)) {
                stack.addLast(next);
                continue;
            }
            if (!closing.contains(next)) {
                throw new IllegalArgumentException("Illegal character " + next);
            }
            if (stack.isEmpty()) {
                return getPoints(next);
            }
            char expected = closing.get(opening.indexOf(stack.getLast()));
            if (expected == next) {
                stack.removeLast();
            } else {
                return getPoints(next);
            }
        }
        return 0;
    }

    private static int getPoints(char next) {
        return switch (next) {
            case ')' -> 3;
            case ']' -> 57;
            case '}' -> 1197;
            case '>' -> 25137;
            default -> 0;
        };
    }

    public static long getPart2(List<String> input) {
        long[] array = input.stream().mapToLong(Day10::getPoints2).filter(p -> p > 0).sorted().toArray();
        return array[array.length / 2];
    }

    private static long getPoints2(String line) {
        final var stack = new LinkedList<Character>();
        for (int i = 0; i < line.length(); i++) {
            char next = line.charAt(i);
            if (opening.contains(next)) {
                stack.addLast(next);
                continue;
            }
            if (!closing.contains(next)) {
                throw new IllegalArgumentException("Illegal character " + next);
            }
            if (stack.isEmpty()) {
                return 0;
            }
            char expected = closing.get(opening.indexOf(stack.getLast()));
            if (expected == next) {
                stack.removeLast();
            } else {
                return 0;
            }
        }
        long points = 0;
        while (!stack.isEmpty()) {
            points *= 5;
            points += switch (stack.removeLast()) {
                case '(' -> 1;
                case '[' -> 2;
                case '{' -> 3;
                case '<' -> 4;
                default -> throw new IllegalStateException();
            };
        }
        return points;
    }
}