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
        double w = t1[3] + t2[3];
        if (w > 1) {
            throw new IllegalArgumentException("Cannot add points");
        }
        return new double[] {t1[0] + t2[0], t1[1] + t2[1], t1[2] + t2[2], w};
    }

    public static double[] subtract(double[] t1, double[] t2) {
        double w = t1[3] - t2[3];
        if (w < 0) {
            throw new IllegalArgumentException("Cannot subtract point from vector");
        }
        return new double[] {t1[0] - t2[0], t1[1] - t2[1], t1[2] - t2[2], w};
    }

    public static double[] negate(double[] v) {
        if (v[3] > 0) {
            throw new IllegalArgumentException("Cannot negate point");
        }
        return new double[] {-v[0], -v[1], -v[2], 0.0};
    }
}
