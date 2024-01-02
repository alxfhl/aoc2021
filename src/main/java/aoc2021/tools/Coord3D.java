package aoc2021.tools;

/**
 * Right hand rule:
 * If the thumb points in positive x direction and the index finger in positive y direction,
 * then the middle finger points in positive z direction. E.g. x to the right, y up and z out of the screen.
 * Concerning rotation:
 * When the thumb points in the positive direction of an axis, the fingers curl in the positive direction of rotation.
 */
@SuppressWarnings("SuspiciousNameCombination")
public record Coord3D(long x, long y, long z) {
    public Coord3D plus(long dx, long dy, long dz) {
        return new Coord3D(x + dx, y + dy, z + dz);
    }

    public Coord3D plus(Coord3D c) {
        return new Coord3D(x + c.x, y + c.y, z + c.z);
    }

    public Coord3D minus(long dx, long dy, long dz) {
        return new Coord3D(x - dx, y - dy, z - dz);
    }

    public Coord3D minus(Coord3D c) {
        return new Coord3D(x - c.x, y - c.y, z - c.z);
    }

    public Coord3D negate() {
        return new Coord3D(-x, -y, -z);
    }

    public Coord3D rotateX() {
        return new Coord3D(x, -z, y);
    }

    public Coord3D rotateY() {
        return new Coord3D(z, y, -x);
    }

    public Coord3D rotateZ() {
        return new Coord3D(-y, x, z);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }
}
