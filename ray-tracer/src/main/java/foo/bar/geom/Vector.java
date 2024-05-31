package foo.bar.geom;

public class Vector {
    private final Tuple t;

    public Vector(double x, double y, double z) {
        this.t = new Tuple(x, y, z, 0.0);
    }

    Vector(Tuple t) {
        this.t = t;
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

    public Vector add(Vector v) {
        return new Vector(t.add(v.t));
    }

    public Vector subtract(Vector v) {
        return new Vector(t.subtract(v.t));
    }

    public Vector negate() {
        return new Vector(-t.x(), -t.y(), -t.z());
    }

    public Vector multiply(double scalar) {
        return new Vector(t.x() * scalar, t.y() * scalar, t.z() * scalar);
    }

    public double magnitude() {
        return Math.sqrt(t.x() * t.x() + t.y() * t.y() + t.z() * t.z());
    }

    public Vector normalize() {
        var magnitude = magnitude();
        return new Vector(t.x() / magnitude, t.y() / magnitude, t.z() / magnitude);
    }

    public double dot(Vector v) {
        return t.x() * v.x() + t.y() * v.y() + t.z() * v.z();
    }

    public Vector cross(Vector v) {
        return new Vector(
                t.y() * v.z() - t.z() * v.y(),
                t.z() * v.x() - t.x() * v.z(),
                t.x() * v.y() - t.y() * v.x());
    }
}
