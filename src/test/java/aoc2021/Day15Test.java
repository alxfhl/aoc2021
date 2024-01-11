package aoc2021;

import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day15Test {

    public static final List<String> EXAMPLE1 = Input.fromString("""
            1163751742
            1381373672
            2136511328
            3694931569
            7463417111
            1319128137
            1359912421
            3125421639
            1293138521
            2311944581
            """);

    @Test
    public void part1() {
        assertThat(Day15.getPart1(EXAMPLE1)).isEqualTo(40);
        assertThat(Day15.getPart1(Input.fromFile("input15"))).isEqualTo(652);
    }

    @Test
    public void part2() {
        assertThat(Day15.getPart2(EXAMPLE1)).isEqualTo(315);
        assertThat(Day15.getPart2(Input.fromFile("input15"))).isEqualTo(2938);
    }
}