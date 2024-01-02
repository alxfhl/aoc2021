package aoc2021;

import aoc2021.Day18.Snail;
import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class Day18Test {

    public static final List<String> EXAMPLE1 = Input.fromString("""
            [[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]
            [[[5,[2,8]],4],[5,[[9,9],0]]]
            [6,[[[6,2],[5,6]],[[7,6],[4,7]]]]
            [[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]
            [[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]
            [[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]
            [[[[5,4],[7,7]],8],[[8,3],8]]
            [[9,3],[[9,9],[6,[4,9]]]]
            [[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]
            [[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]
            """);

    @Test
    public void valueOf() {
        for (String number : EXAMPLE1) {
            Snail snail = Snail.valueOf(number);
            assertThat(snail.toString()).isEqualTo(number);
        }
    }

    @Test
    public void reduce() {
        Map<String, String> tests = Map.of("[[[[[9,8],1],2],3],4]", "[[[[0,9],2],3],4]",
                "[7,[6,[5,[4,[3,2]]]]]", "[7,[6,[5,[7,0]]]]",
                "[[6,[5,[4,[3,2]]]],1]", "[[6,[5,[7,0]]],3]",
                "[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]", "[[3,[2,[8,0]]],[9,[5,[7,0]]]]"
        );
        for (var test : tests.entrySet()) {
            Snail snail = Snail.valueOf(test.getKey());
            snail.reduce();
            assertThat(snail).isEqualTo(Snail.valueOf(test.getValue()));
        }
    }

    @Test
    public void part1() {
        assertThat(Day18.getPart1(EXAMPLE1)).isEqualTo(4140);
        assertThat(Day18.getPart1(Input.fromFile("input18"))).isEqualTo(4120);
    }

    @Test
    public void part2() {
        assertThat(Day18.getPart2(EXAMPLE1)).isEqualTo(3993);
        assertThat(Day18.getPart2(Input.fromFile("input18"))).isEqualTo(4725);
    }
}