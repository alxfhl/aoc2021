package aoc2021;

import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day01Test {

    public static final List<String> EXAMPLE1 = Input.fromString("""
            199
            200
            208
            210
            200
            207
            240
            269
            260
            263
            """);

    @Test
    public void part1() {
        assertThat(Day01.getPart1(EXAMPLE1)).isEqualTo(7);
        assertThat(Day01.getPart1(Input.fromFile("input01"))).isEqualTo(1624);
    }

    @Test
    public void part2() {
        assertThat(Day01.getPart2(EXAMPLE1)).isEqualTo(5);
        assertThat(Day01.getPart2(Input.fromFile("input01"))).isEqualTo(1653);
    }
}