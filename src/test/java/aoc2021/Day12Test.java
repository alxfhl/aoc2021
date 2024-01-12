package aoc2021;

import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day12Test {

    public static final List<String> EXAMPLE1 = Input.fromString("""
            start-A
            start-b
            A-c
            A-b
            b-d
            A-end
            b-end
            """);
    public static final List<String> EXAMPLE2 = Input.fromString("""
            dc-end
            HN-start
            start-kj
            dc-start
            dc-HN
            LN-dc
            HN-end
            kj-sa
            kj-HN
            kj-dc
            """);
    public static final List<String> EXAMPLE3 = Input.fromString("""
            fs-end
            he-DX
            fs-he
            start-DX
            pj-DX
            end-zg
            zg-sl
            zg-pj
            pj-he
            RW-he
            fs-DX
            pj-RW
            zg-RW
            start-pj
            he-WI
            zg-he
            pj-fs
            start-RW
            """);

    @Test
    public void part1() {
        assertThat(Day12.getPart1(EXAMPLE1)).isEqualTo(10);
        assertThat(Day12.getPart1(EXAMPLE2)).isEqualTo(19);
        assertThat(Day12.getPart1(EXAMPLE3)).isEqualTo(226);
        assertThat(Day12.getPart1(Input.fromFile("input12"))).isEqualTo(3485);
    }

    @Test
    public void part2() {
        assertThat(Day12.getPart2(EXAMPLE1)).isEqualTo(36);
        assertThat(Day12.getPart2(EXAMPLE2)).isEqualTo(103);
        assertThat(Day12.getPart2(EXAMPLE3)).isEqualTo(3509);
        assertThat(Day12.getPart2(Input.fromFile("input12"))).isEqualTo(85062);
    }
}