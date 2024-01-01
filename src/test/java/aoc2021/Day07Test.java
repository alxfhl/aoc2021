package aoc2021;

import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day07Test {

    public static final List<String> EXAMPLE1 = Input.fromString("""
            16,1,2,0,4,2,7,1,2,14
            """);

    @Test
    public void part1() {
        assertThat(Day07.getPart1(EXAMPLE1)).isEqualTo(37);
        assertThat(Day07.getPart1(Input.fromFile("input07"))).isEqualTo(343605);
    }

    @Test
    public void part2() {
        assertThat(Day07.getPart2(EXAMPLE1)).isEqualTo(168);
        assertThat(Day07.getPart2(Input.fromFile("input07"))).isEqualTo(96744904);
    }
}