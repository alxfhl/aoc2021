package aoc2021;

import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day09Test {

    public static final List<String> EXAMPLE1 = Input.fromString("""
            2199943210
            3987894921
            9856789892
            8767896789
            9899965678
            """);

    @Test
    public void part1() {
        assertThat(Day09.getPart1(EXAMPLE1)).isEqualTo(15);
        assertThat(Day09.getPart1(Input.fromFile("input09"))).isEqualTo(444);
    }

    @Test
    public void part2() {
        assertThat(Day09.getPart2(EXAMPLE1)).isEqualTo(1134);
        assertThat(Day09.getPart2(Input.fromFile("input09"))).isEqualTo(1168440);
    }
}