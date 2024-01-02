package aoc2021;

import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day08Test {

    public static final List<String> EXAMPLE1 = Input.fromString("""
            be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
            edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
            fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
            fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
            aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
            fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
            dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
            bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
            egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
            gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce
            """);

    @Test
    public void part1() {
        assertThat(Day08.getPart1(EXAMPLE1)).isEqualTo(26);
        assertThat(Day08.getPart1(Input.fromFile("input08"))).isEqualTo(421);
    }

    @Test
    public void part2() {
        assertThat(Day08.getPart2(EXAMPLE1)).isEqualTo(61229);
        assertThat(Day08.getPart2(Input.fromFile("input08"))).isEqualTo(986163);
    }

    @Test
    public void testOutput() {
        List<Integer> expected = List.of(8394, 9781, 1197, 9361, 4873, 8418, 4548, 1625, 8717, 4315);
        int i = 0;
        for (String line : EXAMPLE1) {
            int actual = Day08.getOutput(line);
            assertThat(actual).describedAs(line).isEqualTo(expected.get(i++));
        }
    }
}