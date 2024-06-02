package foo.bar.math;

public class MathUtils {
    private static final double EPSILON = 1e-6;

    private MathUtils() {
    }

    public static boolean areEqual(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }
}
