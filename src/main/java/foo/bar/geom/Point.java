package foo.bar.geom;

import java.util.Objects;

public class Point {
    public static final Point ORIGIN = new Point(0, 0, 0);

    private final Tuple t;

    public Point(double x, double y, double z) {
        t = new Tuple(x, y, z, 1.0);
    }

    public Point(Tuple t) {
        this.t = Objects.requireNonNull(t);
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

    double w() {
        return t.w();
    }

    @Override
    public String toString() {
        return "Point(" + x() + ", " + y() + ", " + z() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Point point)) {
            return false;
        }
        return t.equals(point.t);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(t);
    }

    public Point add(Vector v) {
        return new Point(x() + v.x(), y() + v.y(), z() + v.z());
    }

    public Vector subtract(Point p) {
        return new Vector(t.subtract(p.t));
    }

    public Point subtract(Vector v) {
        return new Point(x() - v.x(), y() - v.y(), z() - v.z());
    }

    public Tuple asTuple() {
        return t;
    }
}
