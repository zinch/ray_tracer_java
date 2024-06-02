package foo.bar.math;

import java.util.Objects;

public class Matrix {
    private final static int DIMENSION = 4;
    private final double[] values;

    public Matrix(double[] values) {
        if (Objects.requireNonNull(values).length != 16) {
            throw new IllegalArgumentException("Matrix should have 16 elements");
        }
        this.values = values;
    }

    public double at(int row, int col) {
        return values[idx(row, col)];
    }

    private int idx(int row, int col) {
        return row * DIMENSION + col;
    }
}
