package foo.bar.math;

import java.util.Objects;

public class Matrix {
    private final double[] values;
    private final int dimension;

    public Matrix(double[] values) {
        Objects.requireNonNull(values);
        if (values.length != 4 && values.length != 9 && values.length != 16) {
            throw new IllegalArgumentException("Matrix can have 4, 9 or 16 elements");
        }
        this.values = values;
        this.dimension = (int) Math.sqrt(values.length);
    }

    public double at(int row, int col) {
        if (row >= dimension || col >= dimension) {
            throw new IllegalArgumentException("Row or column out of bounds");
        }
        return values[idx(row, col)];
    }

    private int idx(int row, int col) {
        return row * dimension + col;
    }
}
