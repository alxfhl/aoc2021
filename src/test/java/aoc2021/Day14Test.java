package aoc2021;

import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day14Test {

    public static final List<String> EXAMPLE1 = Input.fromString("""
            NNCB
                        
            CH -> B
            HH -> N
            CB -> H
            NH -> C
            HB -> C
            HC -> B
            HN -> C
            NN -> C
            BH -> H
            NC -> B
            NB -> B
            BN -> B
            BB -> N
            BC -> B
            CC -> N
            CN -> C
            """);

    @Test
    public void part1() {
        assertThat(Day14.getPart1(EXAMPLE1)).isEqualTo(1588);
        // assertThat(Day14.getPart1(Input.fromFile("input14"))).isEqualTo(0);
    }

    @Test
    public void part2() {
        assertThat(Day14.getPart2(EXAMPLE1)).isEqualTo(2188189693529L);
        assertThat(Day14.getPart2(Input.fromFile("input14"))).isEqualTo(2911561572630L);
    }
}