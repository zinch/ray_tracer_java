package foo.bar.geom;

public record Tuple(double x, double y, double z, double w) {
    private static final double EPSILON = 1e-6;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Tuple t) {
            return Math.abs(x - t.x) <= EPSILON
                    && Math.abs(y - t.y) <= EPSILON
                    && Math.abs(z - t.z) <= EPSILON
                    && Math.abs(w - t.w) <= EPSILON;
        } else {
            return false;
        }
    }
}
