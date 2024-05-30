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
}
