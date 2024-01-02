package aoc2021;

import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day17Test {

    public static final List<String> EXAMPLE1 = Input.fromString("""
            target area: x=20..30, y=-10..-5
            """);

    @Test
    public void part1() {
        assertThat(Day17.getPart1(EXAMPLE1)).isEqualTo(45);
        assertThat(Day17.getPart1(Input.fromFile("input17"))).isEqualTo(5565);
    }

    @Test
    public void part2() {
        assertThat(Day17.getPart2(EXAMPLE1)).isEqualTo(112);
        assertThat(Day17.getPart2(Input.fromFile("input17"))).isEqualTo(2118);
    }
}