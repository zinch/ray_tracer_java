package foo.bar.geom;

public class Point {
    private final Tuple t;

    public Point(double x, double y, double z) {
        t = new Tuple(x, y, z, 1.0);
    }

    public double x() {
        return t.x();
    }

    public double y() {
        return t.y();
    }

    public double z() {
        return t.z();
    }
}
