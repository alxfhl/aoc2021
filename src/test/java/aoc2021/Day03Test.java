package aoc2021;

import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day03Test {

    public static final List<String> EXAMPLE1 = Input.fromString("""
            00100
            11110
            10110
            10111
            10101
            01111
            00111
            11100
            10000
            11001
            00010
            01010
            """);

    @Test
    public void part1() {
        assertThat(Day03.getPart1(EXAMPLE1)).isEqualTo(198);
        assertThat(Day03.getPart1(Input.fromFile("input03"))).isEqualTo(2954600);
    }

    @Test
    public void part2() {
        assertThat(Day03.getPart2(EXAMPLE1)).isEqualTo(230);
        assertThat(Day03.getPart2(Input.fromFile("input03"))).isEqualTo(1662846);
    }
}