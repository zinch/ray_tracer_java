package foo.bar.geom;

import foo.bar.math.MathUtils;

public record Tuple(double x, double y, double z, double w) {
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Tuple t)) {
            return false;
        }
        return MathUtils.areEqual(x, t.x)
            && MathUtils.areEqual(y, t.y)
            && MathUtils.areEqual(z, t.z)
            && MathUtils.areEqual(w, t.w);
    }

    public Tuple add(Tuple t) {
        return new Tuple(x + t.x, y + t.y, z + t.z, w + t.w);
    }

    public Tuple subtract(Tuple t) {
        return new Tuple(x - t.x, y - t.y, z - t.z, w - t.w);
    }

    public Tuple multiply(double scalar) {
        return new Tuple(x * scalar, y * scalar, z * scalar, w * scalar);
    }
}
