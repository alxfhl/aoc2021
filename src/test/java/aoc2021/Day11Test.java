package aoc2021;

import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day11Test {

    public static final List<String> EXAMPLE1 = Input.fromString("""
            5483143223
            2745854711
            5264556173
            6141336146
            6357385478
            4167524645
            2176841721
            6882881134
            4846848554
            5283751526
            """);

    @Test
    public void part1() {
        assertThat(Day11.getPart1(EXAMPLE1)).isEqualTo(1656);
        assertThat(Day11.getPart1(Input.fromFile("input11"))).isEqualTo(1747);
    }

    @Test
    public void part2() {
        assertThat(Day11.getPart2(EXAMPLE1)).isEqualTo(195);
        assertThat(Day11.getPart2(Input.fromFile("input11"))).isEqualTo(505);
    }
}