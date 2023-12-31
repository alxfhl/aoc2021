package aoc2021;

import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day21Test {

    public static final List<String> EXAMPLE1 = Input.fromString("""
            Player 1 starting position: 4
            Player 2 starting position: 8
            """);

    @Test
    public void part1() {
        assertThat(Day21.getPart1(EXAMPLE1)).isEqualTo(739785);
        assertThat(Day21.getPart1(Input.fromFile("input21"))).isEqualTo(888735);
    }

    @Test
    public void part2() {
        assertThat(Day21.getPart2(EXAMPLE1)).isEqualTo(444356092776315L);
        assertThat(Day21.getPart2(Input.fromFile("input21"))).isEqualTo(647608359455719L);
    }
}