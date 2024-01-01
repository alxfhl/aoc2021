package aoc2021;

import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day06Test {

    public static final List<String> EXAMPLE1 = Input.fromString("3,4,3,1,2");

    @Test
    public void part1() {
        assertThat(Day06.getPart1(EXAMPLE1, 18)).isEqualTo(26);
        assertThat(Day06.getPart1(EXAMPLE1, 80)).isEqualTo(5934);
        assertThat(Day06.getPart1(Input.fromFile("input06"), 80)).isEqualTo(360268);
    }

    @Test
    public void part2() {
        assertThat(Day06.getPart1(EXAMPLE1, 256)).isEqualTo(26984457539L);
        assertThat(Day06.getPart1(Input.fromFile("input06"), 256)).isEqualTo(1632146183902L);
    }
}