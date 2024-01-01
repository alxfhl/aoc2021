package aoc2021;

import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day20Test {

    public static final List<String> EXAMPLE1 = Input.fromString("""
            ..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..##\
            #..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###\
            .######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#.\
            .#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#.....\
            .#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#..\
            ...####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#.....\
            ..##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#
                        
            #..#.
            #....
            ##..#
            ..#..
            ..###
            """);

    @Test
    public void part1() {
        assertThat(Day20.getPart1(EXAMPLE1, 2)).isEqualTo(35);
        assertThat(Day20.getPart1(Input.fromFile("input20"), 2)).isEqualTo(5486);
    }

    @Test
    public void part2() {
        assertThat(Day20.getPart1(EXAMPLE1, 50)).isEqualTo(3351);
        assertThat(Day20.getPart1(Input.fromFile("input20"), 50)).isEqualTo(20210);
    }
}