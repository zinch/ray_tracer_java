package foo.bar.geom;

public class Geometry {
    private Geometry() {
    }

    public static double[] newPoint(double x, double y, double z) {
        return new double[] {x, y, z, 1.0};
    }

    public static double[] newVector(double x, double y, double z) {
        return new double[] {x, y, z, 0.0};
    }
}
