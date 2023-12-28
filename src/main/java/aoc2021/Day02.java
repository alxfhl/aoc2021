package aoc2021;

import aoc2021.tools.Input;
import aoc2021.tools.Parse;

import java.util.List;

public class Day02 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day02.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    public static long getPart1(List<String> lines) {
        long depth = 0;
        long position = 0;
        for (String line : lines) {
            if (line.startsWith("forward")) {
                position += Long.parseLong(line.substring(8));
            } else if (line.startsWith("down")) {
                depth += Long.parseLong(line.substring(5));
            } else if (line.startsWith("up")) {
                depth -= Long.parseLong(line.substring(3));
            }
        }
        return depth * position;
    }

    public static long getPart2(List<String> lines) {
        long aim = 0;
        long position = 0;
        long depth = 0;
        for (String line : lines) {
            long units = Parse.getLongs(line).getFirst();
            if (line.startsWith("forward")) {
                position += units;
                depth += aim * units;
            } else if (line.startsWith("down")) {
                aim += units;
            } else if (line.startsWith("up")) {
                aim -= units;
            }
        }
        return depth * position;
    }

}
