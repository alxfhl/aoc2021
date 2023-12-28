package aoc2021.tools;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("SuspiciousNameCombination")
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Grid<T> {
    @Getter
    private final int width;
    @Getter
    private final int height;
    private final Object[] values;

    public Grid(Grid<T> original) {
        this.width = original.width;
        this.height = original.height;
        this.values = new Object[original.values.length];
        System.arraycopy(original.values, 0, this.values, 0, this.values.length);
    }

    public Grid(int width, int height, T fill) {
        this.width = width;
        this.height = height;
        this.values = new Object[width * height];
        Arrays.fill(this.values, fill);
    }

    public Grid<T> transposed() {
        Grid<T> newGrid = new Grid<>(height, width, new Object[this.values.length]);
        int index = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                newGrid.values[x * height + y] = values[index++];
            }
        }
        return newGrid;
    }

    public Grid<T> rotatedLeft() {
        Grid<T> newGrid = new Grid<>(height, width, new Object[this.values.length]);
        int index = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                newGrid.values[(width - 1 - x) * height + y] = values[index++];
            }
        }
        return newGrid;
    }

    public Grid<T> rotatedRight() {
        Grid<T> newGrid = new Grid<>(height, width, new Object[this.values.length]);
        int index = 0;
        for (int y = 0; y < height; y++) {
            int newY = height - 1 - y;
            for (int x = 0; x < width; x++) {
                newGrid.values[x * height + newY] = values[index++];
            }
        }
        return newGrid;
    }

    public Grid<T> rotated180() {
        Grid<T> newGrid = new Grid<>(width, height, new Object[this.values.length]);
        int index = 0;
        for (int y = 0; y < height; y++) {
            int newY = height - y;
            for (int x = 0; x < width; x++) {
                newGrid.values[newY * width - 1 - x] = values[index++];
            }
        }
        return newGrid;
    }

    public static <T> Grid<T> valueOf(List<List<T>> rows) {
        if (rows.isEmpty()) {
            return new Grid<>(0, 0, new Object[0]);
        }
        int width = rows.getFirst().size();
        int height = rows.size();
        Object[] values = new Object[width * height];
        Grid<T> grid = new Grid<>(width, height, values);
        int index = 0;
        for (List<T> row : rows) {
            if (row.size() != width) {
                throw new IllegalArgumentException("All rows must have the same length!");
            }
            for (T t : row) {
                values[index++] = t;
            }
        }
        return grid;
    }

    public void set(Coord2D coord2D, T t) {
        set((int) coord2D.x(), (int) coord2D.y(), t);
    }

    public void set(int x, int y, T t) {
        values[getIndex(x, y)] = t;
    }

    public T get(Coord2D coord2D) {
        return get((int) coord2D.x(), (int) coord2D.y());
    }

    @SuppressWarnings("unchecked")
    public T get(int x, int y) {
        return (T) values[getIndex(x, y)];
    }

    @SuppressWarnings("unchecked")
    public List<T> getRow(int y) {
        List<T> result = new ArrayList<>(width);
        int end = (y + 1) * width;
        for (int index = y * width; index < end; index++) {
            result.add((T) values[index]);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<T> getColumn(int x) {
        List<T> result = new ArrayList<>(height);
        int end = values.length;
        for (int index = x; index < end; index += width) {
            result.add((T) values[index]);
        }
        return result;
    }

    private int getIndex(int x, int y) {
        if (isOutside(x, y)) {
            throw new ArrayIndexOutOfBoundsException("(" + x + "," + y + ") is outside of (0.." + (width - 1) + ",0.." + (height - 1) + ")");
        }
        return y * width + x;
    }

    public boolean isInside(Coord2D coord2D) {
        return isInside((int) coord2D.x(), (int) coord2D.y());
    }

    public boolean isOutside(Coord2D coord2D) {
        return isOutside((int) coord2D.x(), (int) coord2D.y());
    }

    public boolean isInside(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public boolean isOutside(int x, int y) {
        return x < 0 || x >= width || y < 0 || y >= height;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("Grid ").append(width).append(":").append(height).append("\n");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x > 0) {
                    sb.append(" ");
                }
                sb.append(values[y * width + x]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public long count(T t) {
        long count = 0;
        for (Object value : values) {
            if (Objects.equals(value, t)) {
                count++;
            }
        }
        return count;
    }

    public void replace(T search, T replacement) {
        for (int i = 0; i < values.length; i++) {
            if (Objects.equals(values[i], search)) {
                values[i] = replacement;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<List<T>> getDiagonals() {
        if (width != height) {
            throw new IllegalStateException("Only works on squares, but " + width + " != " + height + "!");
        }
        List<T> dia1 = new ArrayList<>(width);
        List<T> dia2 = new ArrayList<>(width);
        for (int x = 0; x < width; x++) {
            dia1.add((T) values[x * (width + 1)]);
            dia2.add((T) values[(x + 1) * (width - 1)]);
        }
        return List.of(dia1, dia2);
    }
}
