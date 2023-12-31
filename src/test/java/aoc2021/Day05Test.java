package aoc2021;

import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day05Test {

    public static final List<String> EXAMPLE1 = Input.fromString("""
            0,9 -> 5,9
            8,0 -> 0,8
            9,4 -> 3,4
            2,2 -> 2,1
            7,0 -> 7,4
            6,4 -> 2,0
            0,9 -> 2,9
            3,4 -> 1,4
            0,0 -> 8,8
            5,5 -> 8,2
            """);

    @Test
    public void part1() {
        assertThat(Day05.getPart1(EXAMPLE1)).isEqualTo(5);
        assertThat(Day05.getPart1(Input.fromFile("input05"))).isEqualTo(5294);
    }

    @Test
    public void part2() {
        assertThat(Day05.getPart2(EXAMPLE1)).isEqualTo(12);
        assertThat(Day05.getPart2(Input.fromFile("input05"))).isEqualTo(21698);
    }
}