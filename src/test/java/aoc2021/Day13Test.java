package aoc2021;

import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day13Test {

    public static final List<String> EXAMPLE1 = Input.fromString("""
            6,10
            0,14
            9,10
            0,3
            10,4
            4,11
            6,0
            6,12
            4,1
            0,13
            10,12
            3,4
            3,0
            8,4
            1,10
            2,14
            8,10
            9,0
                        
            fold along y=7
            fold along x=5
            """);

    @Test
    public void part1() {
        assertThat(Day13.getPart1(EXAMPLE1)).isEqualTo(17);
        assertThat(Day13.getPart1(Input.fromFile("input13"))).isEqualTo(763);
    }

    @Test
    public void part2() {
        // this is a visual test, you need to read the output "RHALRCRA"
    }
}