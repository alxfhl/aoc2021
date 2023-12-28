package aoc2021;

import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day02Test {

    public static final List<String> EXAMPLE1 = Input.fromString("""
            forward 5
            down 5
            forward 8
            up 3
            down 8
            forward 2
            """);

    @Test
    public void part1() {
        assertThat(Day02.getPart1(EXAMPLE1)).isEqualTo(150);
        assertThat(Day02.getPart1(Input.fromFile("input02"))).isEqualTo(1924923);
    }

    @Test
    public void part2() {
        assertThat(Day02.getPart2(EXAMPLE1)).isEqualTo(900);
        assertThat(Day02.getPart2(Input.fromFile("input02"))).isEqualTo(1982495697);
    }
}