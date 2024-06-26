package foo.bar.geom;

import java.util.Objects;

public class Vector {
    private final Tuple t;

    public Vector(double x, double y, double z) {
        this.t = new Tuple(x, y, z, 0.0);
    }

    public Vector(Tuple t) {
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
        return "Vector(" + x() + ", " + y() + ", " + z() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vector vector)) {
            return false;
        }
        return t.equals(vector.t);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(t);
    }

    public Vector add(Vector v) {
        return new Vector(t.add(v.t));
    }

    public Vector subtract(Vector v) {
        return new Vector(t.subtract(v.t));
    }

    public Vector negate() {
        return new Vector(-x(), -y(), -z());
    }

    public Vector multiply(double scalar) {
        return new Vector(t.multiply(scalar));
    }

    public double magnitude() {
        return Math.sqrt(x() * x() + y() * y() + z() * z());
    }

    public Vector normalize() {
        var magnitude = magnitude();
        return new Vector(x() / magnitude, y() / magnitude, z() / magnitude);
    }

    public double dot(Vector v) {
        return x() * v.x() + y() * v.y() + z() * v.z();
    }

    public Vector cross(Vector v) {
        return new Vector(
                y() * v.z() - z() * v.y(),
                z() * v.x() - x() * v.z(),
                x() * v.y() - y() * v.x());
    }

    public Tuple asTuple() {
        return t;
    }
}
