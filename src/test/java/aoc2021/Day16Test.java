package aoc2021;

import aoc2021.tools.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day16Test {

    public static final List<String> EXAMPLE1 = Input.fromString("""
            8A004A801A8002F478
            620080001611562C8802118E34
            C0015000016115A2E0802F182340
            A0016C880162017C3686B18A3D4780
            """);
    public static final List<String> EXAMPLE2 = Input.fromString("""
            C200B40A82
            04005AC33890
            880086C3E88112
            CE00C43D881120
            D8005AC2A8F0
            F600BC2D8F
            9C005AC2F8F0
            9C0141080250320F1802104A08
            """);

    @Test
    public void part1() {
        assertThat(Day16.getPart1(EXAMPLE1)).isEqualTo(16 + 12 + 23 + 31);
        assertThat(Day16.getPart1(Input.fromFile("input16"))).isEqualTo(929);
    }

    @Test
    public void part2() {
        assertThat(Day16.getPart2(EXAMPLE2)).isEqualTo(3 + 54 + 7 + 9 + 1 + 1);
        assertThat(Day16.getPart2(Input.fromFile("input16"))).isEqualTo(911945136934L);
    }
}