package aoc2021.tools;

import java.util.ArrayList;
import java.util.List;

public record Cuboid(int x1, int y1, int z1, int x2, int y2, int z2) {
    boolean intersects(Cuboid c) {
        return x1 <= c.x2 && c.x1 <= x2
                && y1 <= c.y2 && c.y1 <= y2
                && z1 <= c.z2 && c.z1 <= z2;
    }

    boolean contains(Cuboid c) {
        return x1 <= c.x1 && x2 >= c.x2
                && y1 <= c.y1 && y2 >= c.y2
                && z1 <= c.z1 && z2 >= c.z2;
    }

    public List<Cuboid> minus(Cuboid cuboid) {
        if (!intersects(cuboid)) {
            return List.of(this);
        }
        if (cuboid.contains(this)) {
            return List.of();
        }

        if (x1 < cuboid.x1 || x2 > cuboid.x2) {
            List<Cuboid> result = new ArrayList<>();
            if (x1 < cuboid.x1) {
                result.addAll(new Cuboid(x1, y1, z1, cuboid.x1 - 1, y2, z2).minus(cuboid));
            }
            result.addAll(new Cuboid(Math.max(x1, cuboid.x1), y1, z1, Math.min(x2, cuboid.x2), y2, z2).minus(cuboid));
            if (x2 > cuboid.x2) {
                result.addAll(new Cuboid(cuboid.x2 + 1, y1, z1, x2, y2, z2).minus(cuboid));
            }
            return result;
        }

        if (y1 < cuboid.y1 || y2 > cuboid.y2) {
            List<Cuboid> result = new ArrayList<>();
            if (y1 < cuboid.y1) {
                result.addAll(new Cuboid(x1, y1, z1, x2, cuboid.y1 - 1, z2).minus(cuboid));
            }
            result.addAll(new Cuboid(x1, Math.max(y1, cuboid.y1), z1, x2, Math.min(y2, cuboid.y2), z2).minus(cuboid));
            if (y2 > cuboid.y2) {
                result.addAll(new Cuboid(x1, cuboid.y2 + 1, z1, x2, y2, z2).minus(cuboid));
            }
            return result;
        }

        List<Cuboid> result = new ArrayList<>();
        if (z1 < cuboid.z1) {
            result.addAll(new Cuboid(x1, y1, z1, x2, y2, cuboid.z1 - 1).minus(cuboid));
        }
        result.addAll(new Cuboid(x1, y1, Math.max(z1, cuboid.z1), x2, y2, Math.min(z2, cuboid.z2)).minus(cuboid));
        if (z2 > cuboid.z2) {
            result.addAll(new Cuboid(x1, y1, cuboid.z2 + 1, x2, y2, z2).minus(cuboid));
        }
        return result;
    }

    public long getVolume() {
        return (x2 - x1 + 1) * ((long) (y2 - y1 + 1)) * (z2 - z1 + 1);
    }
}
