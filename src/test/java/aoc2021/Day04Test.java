package aoc2021;

import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day04Test {

    public static final List<String> EXAMPLE1 = Input.fromString("""
            7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1
                        
            22 13 17 11  0
             8  2 23  4 24
            21  9 14 16  7
             6 10  3 18  5
             1 12 20 15 19
                        
             3 15  0  2 22
             9 18 13 17  5
            19  8  7 25 23
            20 11 10 24  4
            14 21 16 12  6
                        
            14 21 17 24  4
            10 16 15  9 19
            18  8 23 26 20
            22 11 13  6  5
             2  0 12  3  7
            """);

    @Test
    public void part1() {
        assertThat(Day04.getPart1(EXAMPLE1)).isEqualTo(4512);
        assertThat(Day04.getPart1(Input.fromFile("input04"))).isEqualTo(54275);
    }

    @Test
    public void part2() {
        assertThat(Day04.getPart2(EXAMPLE1)).isEqualTo(1924);
        assertThat(Day04.getPart2(Input.fromFile("input04"))).isEqualTo(13158);
    }
}