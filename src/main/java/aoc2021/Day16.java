package aoc2021;

import aoc2021.tools.Input;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day16 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day16.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    @RequiredArgsConstructor
    static abstract class Packet {
        final int version;

        public long getVersionSum() {
            return version;
        }

        abstract long getValue();
    }

    @Getter
    static class LiteralValuePacket extends Packet {
        final long value;

        public LiteralValuePacket(int version, long value) {
            super(version);
            this.value = value;
        }
    }

    static class Operator extends Packet {
        final int type;
        final List<Packet> packets;

        public Operator(int version, int type, List<Packet> packets) {
            super(version);
            this.type = type;
            this.packets = packets;
        }

        @Override
        public long getVersionSum() {
            return super.getVersionSum() + packets.stream().mapToLong(Packet::getVersionSum).sum();
        }

        @Override
        long getValue() {
            return switch (type) {
                case 0 -> packets.stream().mapToLong(Packet::getValue).sum();
                case 1 -> packets.stream().mapToLong(Packet::getValue).reduce(1, (a, b) -> a * b);
                case 2 -> packets.stream().mapToLong(Packet::getValue).min().orElseThrow();
                case 3 -> packets.stream().mapToLong(Packet::getValue).max().orElseThrow();
                case 5 -> packets.get(0).getValue() > packets.get(1).getValue() ? 1 : 0;
                case 6 -> packets.get(0).getValue() < packets.get(1).getValue() ? 1 : 0;
                case 7 -> packets.get(0).getValue() == packets.get(1).getValue() ? 1 : 0;
                default -> throw new IllegalStateException();
            };
        }
    }

    public static long getPart1(List<String> input) {
        List<Packet> packets = input.stream().map(line -> readPacket(hexToBits(line))).toList();
        return packets.stream().mapToLong(Packet::getVersionSum).sum();
    }

    static Packet readPacket(List<Integer> line) {
        int version = readInt(line, 3);
        int type = readInt(line, 3);
        if (type == 4) {
            return parseLiteralValue(line, version);
        }
        return parseOperator(line, type, version);
    }

    static int readInt(List<Integer> line, int bits) {
        int result = 0;
        for (int i = 0; i < bits; i++) {
            result = (result << 1) + line.removeFirst();
        }
        return result;
    }

    static Packet parseOperator(List<Integer> line, int type, int version) {
        int lengthType = readInt(line, 1);
        List<Packet> subPackets = new ArrayList<>();
        if (lengthType == 0) {
            int totalLength = readInt(line, 15);
            List<Integer> subLine = new LinkedList<>();
            for (int i = 0; i < totalLength; i++) {
                subLine.addLast(line.removeFirst());
            }
            while (!subLine.isEmpty()) {
                subPackets.add(readPacket(subLine));
            }
        } else {
            int totalPackets = readInt(line, 11);
            for (int i = 0; i < totalPackets; i++) {
                subPackets.add(readPacket(line));
            }
        }
        return new Operator(version, type, subPackets);
    }

    static Packet parseLiteralValue(List<Integer> line, int version) {
        long value = 0;
        while (true) {
            int prefix = readInt(line, 1);
            int nextPart = readInt(line, 4);
            value = (value << 4) + nextPart;
            if (prefix == 0) {
                return new LiteralValuePacket(version, value);
            }
        }
    }

    public static long getPart2(List<String> input) {
        List<Packet> packets = input.stream().map(line -> readPacket(hexToBits(line))).toList();
        return packets.stream().mapToLong(Packet::getValue).sum();
    }

    public static List<Integer> hexToBits(String hex) {
        List<Integer> result = new LinkedList<>();
        for (char ch : hex.toCharArray()) {
            int i = Integer.parseInt(String.valueOf(ch), 16);
            result.add((i & 0x8) == 0 ? 0 : 1);
            result.add((i & 0x4) == 0 ? 0 : 1);
            result.add((i & 0x2) == 0 ? 0 : 1);
            result.add((i & 0x1) == 0 ? 0 : 1);
        }
        return result;
    }
}