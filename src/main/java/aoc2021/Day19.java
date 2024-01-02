package aoc2021;

import aoc2021.tools.Coord3D;
import aoc2021.tools.Input;
import aoc2021.tools.Parse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day19 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day19.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    @RequiredArgsConstructor
    @Getter
    @Setter
    static class Scanner {
        final int number;
        final List<Coord3D> beacons;
        Coord3D location = new Coord3D(0, 0, 0);
    }

    public static long getPart1(List<String> input) {
        List<Scanner> scanners = parse(input);
        Set<Coord3D> beacons = new HashSet<>(scanners.removeFirst().getBeacons());
        while (!scanners.isEmpty()) {
            int sizeBefore = scanners.size();
            Iterator<Scanner> iterator = scanners.iterator();
            while (iterator.hasNext()) {
                Scanner scanner = iterator.next();
                List<Coord3D> newBeacons = scanner.getBeacons();
                List<List<Coord3D>> orientations = getOrientations(newBeacons);
                for (List<Coord3D> orientation : orientations) {
                    Coord3D offset = getOffset(beacons, orientation);
                    if (offset != null) {
                        for (Coord3D newBeacon : orientation) {
                            beacons.add(newBeacon.plus(offset));
                        }
                        System.out.println("mapped scanner " + scanner.getNumber());
                        iterator.remove();
                        break;
                    }
                }
            }
            if (scanners.size() == sizeBefore) {
                System.out.println("error: could not map all scanners!");
                break;
            }
        }
        return beacons.size();
    }

    public static List<List<Coord3D>> getOrientations(List<Coord3D> newBeacons) {
        List<List<Coord3D>> result = new ArrayList<>();
        List<List<Coord3D>> rotations = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            rotations.add(newBeacons);
            if (i < 3) {
                newBeacons = newBeacons.stream().map(Coord3D::rotateX).toList();
            }
        }
        for (List<Coord3D> rotation : rotations) {
            result.add(rotation);
            rotation = rotation.stream().map(Coord3D::rotateY).toList();
            result.add(rotation);
            rotation = rotation.stream().map(Coord3D::rotateY).toList();
            result.add(rotation);
            rotation = rotation.stream().map(Coord3D::rotateY).toList();
            result.add(rotation);
            rotation = rotation.stream().map(Coord3D::rotateY).map(Coord3D::rotateZ).toList();
            result.add(rotation);
            rotation = rotation.stream().map(Coord3D::rotateZ).map(Coord3D::rotateZ).toList();
            result.add(rotation);
        }
        return result;
    }

    private static Coord3D getOffset(Set<Coord3D> fixed, List<Coord3D> newBeacons) {
        for (int n = 0; n < newBeacons.size() - 11; n++) {
            Coord3D newBeacon = newBeacons.get(n);
            for (Coord3D f : fixed) {
                Coord3D offset = f.minus(newBeacon);
                int count = 1;
                for (int r = n + 1; r < newBeacons.size(); r++) {
                    if (fixed.contains(newBeacons.get(r).plus(offset))) {
                        count++;
                    }
                }
                if (count >= 12) {
                    return offset;
                }
            }
        }
        return null;
    }

    private static List<Scanner> parse(List<String> input) {
        List<Scanner> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("--- scanner (\\d+) ---");
        int scannerNumber = 0;
        List<Coord3D> beacons = new ArrayList<>();
        for (String line : input) {
            if (line.isBlank()) {
                continue;
            }
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                if (!beacons.isEmpty()) {
                    result.add(new Scanner(scannerNumber, beacons));
                    beacons = new ArrayList<>();
                }
                scannerNumber = Integer.parseInt(matcher.group(1));
                continue;
            }
            List<Integer> integers = Parse.getIntegers(line);
            beacons.add(new Coord3D(integers.get(0), integers.get(1), integers.get(2)));
        }
        result.add(new Scanner(scannerNumber, beacons));
        return result;
    }

    public static long getPart2(List<String> input) {
        List<Scanner> scanners = parse(input);
        Set<Coord3D> beacons = new HashSet<>(scanners.removeFirst().getBeacons());
        List<Coord3D> offsets = new ArrayList<>();
        offsets.add(new Coord3D(0, 0, 0));
        while (!scanners.isEmpty()) {
            int sizeBefore = scanners.size();
            Iterator<Scanner> iterator = scanners.iterator();
            while (iterator.hasNext()) {
                Scanner scanner = iterator.next();
                List<Coord3D> newBeacons = scanner.getBeacons();
                List<List<Coord3D>> orientations = getOrientations(newBeacons);
                for (List<Coord3D> orientation : orientations) {
                    Coord3D offset = getOffset(beacons, orientation);
                    if (offset != null) {
                        scanner.setLocation(offset);
                        offsets.add(offset);
                        for (Coord3D newBeacon : orientation) {
                            beacons.add(newBeacon.plus(offset));
                        }
                        iterator.remove();
                        break;
                    }
                }
            }
            if (scanners.size() == sizeBefore) {
                System.out.println("error: could not map all scanners!");
                break;
            }
        }
        return maxDistance(offsets);
    }

    private static long maxDistance(List<Coord3D> offsets) {
        long max = 0;
        for (int i = 0; i < offsets.size() - 1; i++) {
            Coord3D o1 = offsets.get(i);
            for (int j = i + 1; j < offsets.size(); j++) {
                Coord3D o2 = offsets.get(j);
                max = Math.max(max, Math.abs(o1.x() - o2.x()) + Math.abs(o1.y() - o2.y()) + Math.abs(o1.z() - o2.z()));
            }
        }
        return max;
    }
}