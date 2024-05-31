package foo.bar.color;

import foo.bar.geom.Tuple;

public class Color {
    private final Tuple t;

    public Color(double red, double green, double blue) {
        t = new Tuple(red, green, blue, 0.0);
    }

    public double red() {
        return t.x();
    }

    public double green() {
        return t.y();
    }

    public double blue() {
        return t.z();
    }

    @Override
    public String toString() {
        return "Color(" + t.x() + ", " + t.y() + ", " + t.z() + ")";
    }
}
