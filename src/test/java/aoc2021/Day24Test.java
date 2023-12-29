package aoc2021;

import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day24Test {

    @Test
    public void part1() {
        assertThat(Day24.getPart1(Input.fromFile("input24"))).isEqualTo("69914999975369");
    }

    @Test
    public void part2() {
        assertThat(Day24.getPart2(Input.fromFile("input24"))).isEqualTo("14911675311114");
    }
}