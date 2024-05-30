package foo.bar.geom;

public class Geometry {
    private static final double EPSILON = 1e-5;

    private Geometry() {
    }

    public static double[] newPoint(double x, double y, double z) {
        return new double[] {x, y, z, 1.0};
    }

    public static double[] newVector(double x, double y, double z) {
        return new double[] {x, y, z, 0.0};
    }

    public static boolean equal(double[] p1, double[] p2) {
        return Math.abs(p1[0] - p2[0]) <= EPSILON
                && Math.abs(p1[1] - p2[1]) <= EPSILON
                && Math.abs(p1[2] - p2[2]) <= EPSILON;
    }

    public static double[] add(double[] t1, double[] t2) {
        if (!isVector(t1) && !isVector(t2)) {
            throw new IllegalArgumentException("Cannot add points");
        }
        return new double[] {t1[0] + t2[0], t1[1] + t2[1], t1[2] + t2[2], t1[3] + t2[3]};
    }

    public static double[] subtract(double[] t1, double[] t2) {
        if (isVector(t1) && !isVector(t2)) {
            throw new IllegalArgumentException("Cannot subtract point from vector");
        }
        return new double[] {t1[0] - t2[0], t1[1] - t2[1], t1[2] - t2[2], t1[3] - t2[3]};
    }

    public static double[] negate(double[] v) {
        if (!isVector(v)) {
            throw new IllegalArgumentException("Cannot negate point");
        }
        return new double[] {-v[0], -v[1], -v[2], 0.0};
    }

    public static double[] multiply(double[] v, double scalar) {
        if (!isVector(v)) {
            throw new IllegalArgumentException("Cannot multiply point by scalar");
        }
        return new double[] {v[0] * scalar, v[1] * scalar, v[2] * scalar, 0.0};
    }

    private static boolean isVector(double[] tuple) {
        return tuple[3] == 0.0;
    }

    public static double magnitude(double[] v) {
        if (!isVector(v)) {
            throw new IllegalArgumentException("Cannot calculate magnitude of a point");
        }
        return Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
    }

    public static double[] normalize(double[] v) {
        if (!isVector(v)) {
            throw new IllegalArgumentException("Cannot normalize a point");
        }
        double magnitude = magnitude(v);
        return new double[] {v[0] / magnitude, v[1] / magnitude, v[2] / magnitude, 0.0};
    }

    public static double dot(double[] v1, double[] v2) {
        if (!(isVector(v1) && isVector(v2))) {
            throw new IllegalArgumentException("Dot product is calculated between two vectors");
        }
        return v1[0] * v2[0] + v1[1] * v2[1] + v1[2] * v2[2];
    }
}
