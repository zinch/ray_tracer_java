package foo.bar.geom;

public class Vector {
    private final Tuple t;

    public Vector(double x, double y, double z) {
        this.t = new Tuple(x, y, z, 0.0);
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
        return "Vector(" + t.x() + ", " + t.y() + ", " + t.z() + ")";
    }
}
