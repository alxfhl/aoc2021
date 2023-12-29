package aoc2021;

import aoc2021.tools.CharMatrix;
import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day25Test {

    public static final List<String> EXAMPLE1 = Input.fromString("""
            v...>>.vv>
            .vv>>.vv..
            >>.>v>...v
            >>v>>.>.v.
            v>v.vv.v..
            >.>>..v...
            .vv..>.>v.
            v.v..>>v.v
            ....v..v.>
            """);

    @Test
    public void testStep() {
        assertThat(Day25.step(CharMatrix.valueOf(List.of("...>>>>>..."))))
                .isEqualTo(CharMatrix.valueOf(List.of("...>>>>.>..")));
        assertThat(Day25.step(CharMatrix.valueOf(List.of("...>>>>.>.."))))
                .isEqualTo(CharMatrix.valueOf(List.of("...>>>.>.>.")));

        assertThat(Day25.step(CharMatrix.valueOf(List.of("..........", ".>v....v..", ".......>..", ".........."))))
                .isEqualTo(CharMatrix.valueOf(List.of("..........", ".>........", "..v....v>.", "..........")));
    }

    @Test
    public void part1() {
        assertThat(Day25.getPart1(EXAMPLE1)).isEqualTo(58);
        assertThat(Day25.getPart1(Input.fromFile("input25"))).isEqualTo(458);
    }
}