package aoc2021.tools;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Coord3DTest {

    @Test
    public void testRotateX() {
        Coord3D coord3D = new Coord3D(3, 2, 1);
        coord3D = coord3D.rotateX();
        assertThat(coord3D).isEqualTo(new Coord3D(3, -1, 2));
        coord3D = coord3D.rotateX();
        assertThat(coord3D).isEqualTo(new Coord3D(3, -2, -1));
        coord3D = coord3D.rotateX();
        assertThat(coord3D).isEqualTo(new Coord3D(3, 1, -2));
    }

    @Test
    public void testRotateY() {
        Coord3D coord3D = new Coord3D(3, 2, 1);
        coord3D = coord3D.rotateY();
        assertThat(coord3D).isEqualTo(new Coord3D(1, 2, -3));
        coord3D = coord3D.rotateY();
        assertThat(coord3D).isEqualTo(new Coord3D(-3, 2, -1));
        coord3D = coord3D.rotateY();
        assertThat(coord3D).isEqualTo(new Coord3D(-1, 2, 3));
    }

    @Test
    public void testRotateZ() {
        Coord3D coord3D = new Coord3D(3, 2, 1);
        coord3D = coord3D.rotateZ();
        assertThat(coord3D).isEqualTo(new Coord3D(-2, 3, 1));
        coord3D = coord3D.rotateZ();
        assertThat(coord3D).isEqualTo(new Coord3D(-3, -2, 1));
        coord3D = coord3D.rotateZ();
        assertThat(coord3D).isEqualTo(new Coord3D(2, -3, 1));
    }

    @Test
    public void testNegate() {
        Coord3D coord3D = new Coord3D(3, 2, 1);
        assertThat(coord3D.negate()).isEqualTo(new Coord3D(-3, -2, -1));
        assertThat(coord3D.negate().negate()).isEqualTo(coord3D);
    }
}