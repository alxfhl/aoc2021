package aoc2021.tools;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GridTest {

    @Test
    public void initializeAndRead() {
        Grid<Integer> grid = Grid.valueOf(List.of(List.of(1, 2, 3), List.of(4, 5, 6)));
        assertThat(grid.getWidth()).isEqualTo(3);
        assertThat(grid.getHeight()).isEqualTo(2);
        assertThat(grid.get(0, 0)).isEqualTo(1);
        assertThat(grid.get(1, 0)).isEqualTo(2);
        assertThat(grid.get(2, 0)).isEqualTo(3);
        assertThat(grid.get(0, 1)).isEqualTo(4);
        assertThat(grid.get(1, 1)).isEqualTo(5);
        assertThat(grid.get(2, 1)).isEqualTo(6);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> grid.get(-1, 0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> grid.get(3, 0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> grid.get(0, -1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> grid.get(2, 2));
    }

    @Test
    public void copyAndTranspose() {
        Grid<Integer> grid = Grid.valueOf(List.of(List.of(1, 2, 3), List.of(4, 5, 6)));
        grid = grid.transposed();
        Grid<Integer> copy = new Grid<>(grid);

        assertThat(copy.getWidth()).isEqualTo(2);
        assertThat(copy.getHeight()).isEqualTo(3);
        assertThat(copy.get(0, 0)).isEqualTo(1);
        assertThat(copy.get(1, 0)).isEqualTo(4);
        assertThat(copy.get(0, 1)).isEqualTo(2);
        assertThat(copy.get(1, 1)).isEqualTo(5);
        assertThat(copy.get(0, 2)).isEqualTo(3);
        assertThat(copy.get(1, 2)).isEqualTo(6);

        copy.set(0, 0, 7);
        assertThat(copy.get(0, 0)).isEqualTo(7);
        assertThat(grid.get(0, 0)).isEqualTo(1);
    }

    @Test
    public void getRowOrColumn() {
        Grid<Integer> grid = Grid.valueOf(List.of(List.of(1, 2, 3), List.of(4, 5, 6)));

        assertThat(grid.getRow(0)).isEqualTo(List.of(1, 2, 3));
        assertThat(grid.getRow(1)).isEqualTo(List.of(4, 5, 6));

        assertThat(grid.getColumn(0)).isEqualTo(List.of(1, 4));
        assertThat(grid.getColumn(1)).isEqualTo(List.of(2, 5));
        assertThat(grid.getColumn(2)).isEqualTo(List.of(3, 6));

        grid = grid.transposed();
        assertThat(grid.getRow(0)).isEqualTo(List.of(1, 4));
        assertThat(grid.getColumn(0)).isEqualTo(List.of(1, 2, 3));
    }

    @Test
    public void rotation() {
        Grid<Integer> grid = Grid.valueOf(List.of(List.of(1, 2, 3), List.of(4, 5, 6)));
        assertThat(grid.rotatedLeft()).isEqualTo(Grid.valueOf(List.of(List.of(3, 6), List.of(2, 5), List.of(1, 4))));
        assertThat(grid.rotatedRight()).isEqualTo(Grid.valueOf(List.of(List.of(4, 1), List.of(5, 2), List.of(6, 3))));
        assertThat(grid.rotated180()).isEqualTo(Grid.valueOf(List.of(List.of(6, 5, 4), List.of(3, 2, 1))));
    }

    @Test
    public void countAndReplace() {
        Grid<Integer> grid = new Grid<>(3, 3, 0);
        assertThat(grid.count(0)).isEqualTo(9);
        assertThat(grid.count(-1)).isEqualTo(0);
        grid.set(0, 0, 1);
        grid.set(0, 1, 1);
        grid.set(1, 0, 2);
        grid.set(1, 1, 2);
        grid.set(1, 2, 2);
        assertThat(grid.count(0)).isEqualTo(4);
        assertThat(grid.count(1)).isEqualTo(2);
        assertThat(grid.count(2)).isEqualTo(3);
        grid.replace(1, 3);
        assertThat(grid.count(1)).isEqualTo(0);
        assertThat(grid.count(3)).isEqualTo(2);
        grid.replace(2, 3);
        assertThat(grid.count(3)).isEqualTo(5);
        grid.replace(0, 3);
        assertThat(grid.count(3)).isEqualTo(9);
    }

    @Test
    public void insideAndOutside() {
        Grid<Integer> grid = new Grid<>(3, 3, 0);
        assertThat(grid.isInside(0, 0)).isTrue();
        assertThat(grid.isInside(2, 2)).isTrue();
        assertThat(grid.isInside(-1, 2)).isFalse();
        assertThat(grid.isInside(1, -1)).isFalse();
        assertThat(grid.isInside(1, 3)).isFalse();
        assertThat(grid.isInside(3, 1)).isFalse();

        assertThat(grid.isOutside(0, 0)).isFalse();
        assertThat(grid.isOutside(2, 2)).isFalse();
        assertThat(grid.isOutside(-1, 2)).isTrue();
        assertThat(grid.isOutside(1, -1)).isTrue();
        assertThat(grid.isOutside(1, 3)).isTrue();
        assertThat(grid.isOutside(3, 1)).isTrue();

        assertThat(grid.isInside(new Coord2D(0, 0))).isTrue();
        assertThat(grid.isInside(new Coord2D(2, 2))).isTrue();
        assertThat(grid.isInside(new Coord2D(-1, 2))).isFalse();
        assertThat(grid.isInside(new Coord2D(1, -1))).isFalse();
        assertThat(grid.isInside(new Coord2D(1, 3))).isFalse();
        assertThat(grid.isInside(new Coord2D(3, 1))).isFalse();

        assertThat(grid.isOutside(new Coord2D(0, 0))).isFalse();
        assertThat(grid.isOutside(new Coord2D(2, 2))).isFalse();
        assertThat(grid.isOutside(new Coord2D(-1, 2))).isTrue();
        assertThat(grid.isOutside(new Coord2D(1, -1))).isTrue();
        assertThat(grid.isOutside(new Coord2D(1, 3))).isTrue();
        assertThat(grid.isOutside(new Coord2D(3, 1))).isTrue();
    }
}