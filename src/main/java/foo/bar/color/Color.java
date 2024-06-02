package foo.bar.color;

import foo.bar.geom.Tuple;

import java.util.Objects;

public class Color {
    public static final Color BLACK = new Color(0, 0, 0);

    private final Tuple t;

    public Color(double red, double green, double blue) {
        t = new Tuple(red, green, blue, 0.0);
    }

    protected Color(Tuple t) {
        this.t = t;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Color color) {
            return t.equals(color.t);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(t);
    }

    public Color add(Color c) {
        return new Color(t.add(c.t));
    }

    public Color subtract(Color c) {
        return new Color(t.subtract(c.t));
    }

    public Color multiply(double scalar) {
        return new Color(t.multiply(scalar));
    }

    public Color blend(Color c) {
        return new Color(red() * c.red(), green() * c.green(), blue() * c.blue());
    }
}
