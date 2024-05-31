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

    double w() {
        return t.w();
    }

    @Override
    public String toString() {
        return "Point(" + t.x() + ", " + t.y() + ", " + t.z() + ")";
    }

    public Point add(Vector v) {
        return new Point(t.x() + v.x(), t.y() + v.y(), t.z() + v.z());
    }

    public Vector subtract(Point p) {
        return new Vector(t.x() - p.x(), t.y() - p.y(), t.z() - p.z());
    }

    public Point subtract(Vector v) {
        return new Point(t.x() - v.x(), t.y() - v.y(), t.z() - v.z());
    }
}
