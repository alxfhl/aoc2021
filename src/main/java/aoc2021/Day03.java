package aoc2021;

import aoc2021.tools.CharMatrix;
import aoc2021.tools.Input;

import java.util.ArrayList;
import java.util.List;

public class Day03 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day03.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    public static long getPart1(List<String> lines) {
        CharMatrix matrix = CharMatrix.valueOf(lines);
        StringBuilder gammaBits = new StringBuilder();
        StringBuilder epsilonBits = new StringBuilder();
        for (int x = 0; x < matrix.getWidth(); x++) {
            char[] column = matrix.getColumn(x);
            int zeroes = 0;
            int ones = 0;
            for (char ch : column) {
                if (ch == '0') {
                    zeroes++;
                } else if (ch == '1') {
                    ones++;
                }
            }
            if (zeroes > ones) {
                gammaBits.append('0');
                epsilonBits.append('1');
            } else {
                gammaBits.append('1');
                epsilonBits.append('0');
            }
        }
        long gamma = Long.parseLong(gammaBits.toString(), 2);
        long epsilon = Long.parseLong(epsilonBits.toString(), 2);
        return gamma * epsilon;
    }

    public static long getPart2(List<String> lines) {
        List<String> remaining = new ArrayList<>(lines);
        int bit = 0;
        while (remaining.size() > 1) {
            int finalBit = bit;
            long zeroes = remaining.stream().map(s -> s.charAt(finalBit)).filter(ch -> ch == '0').count();
            long ones = remaining.size() - zeroes;
            char keep = zeroes > ones ? '0' : '1';
            remaining = remaining.stream().filter(s -> s.charAt(finalBit) == keep).toList();
            bit++;
        }
        long oxygen = Long.parseLong(remaining.getFirst(), 2);
        remaining = new ArrayList<>(lines);
        bit = 0;
        while (remaining.size() > 1) {
            int finalBit = bit;
            long zeroes = remaining.stream().map(s -> s.charAt(finalBit)).filter(ch -> ch == '0').count();
            long ones = remaining.size() - zeroes;
            char keep = zeroes > ones ? '1' : '0';
            remaining = remaining.stream().filter(s -> s.charAt(finalBit) == keep).toList();
            bit++;
        }
        long co2 = Long.parseLong(remaining.getFirst(), 2);
        return oxygen * co2;
    }
}