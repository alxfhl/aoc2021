package aoc2021;

import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day10Test {

    public static final List<String> EXAMPLE1 = Input.fromString("""
            [({(<(())[]>[[{[]{<()<>>
            [(()[<>])]({[<{<<[]>>(
            {([(<{}[<>[]}>{[]{[(<()>
            (((({<>}<{<{<>}{[]{[]{}
            [[<[([]))<([[{}[[()]]]
            [{[{({}]{}}([{[{{{}}([]
            {<[[]]>}<{[{[{[]{()[[[]
            [<(<(<(<{}))><([]([]()
            <{([([[(<>()){}]>(<<{{
            <{([{{}}[<[[[<>{}]]]>[]]
            """);

    @Test
    public void part1() {
        assertThat(Day10.getPart1(EXAMPLE1)).isEqualTo(26397);
        assertThat(Day10.getPart1(Input.fromFile("input10"))).isEqualTo(288291);
    }

    @Test
    public void part2() {
        assertThat(Day10.getPart2(EXAMPLE1)).isEqualTo(288957);
        assertThat(Day10.getPart2(Input.fromFile("input10"))).isEqualTo(820045242);
    }
}