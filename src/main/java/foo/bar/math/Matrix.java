package foo.bar.math;

import java.util.Arrays;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Matrix matrix)) {
            return false;
        }
        if (dimension != matrix.dimension) {
            return false;
        }

        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                if (!MathUtils.areEqual(at(row, col), matrix.at(row, col))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(values), dimension);
    }

    @Override
    public String toString() {
        return "Matrix" + Arrays.toString(values);
    }

    public Matrix multiply(Matrix m) {
        if (dimension != m.dimension) {
            throw new IllegalArgumentException("Matrix dimensions don't match");
        }
        var result = new double[dimension * dimension];

        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                double value = 0;
                for (int k = 0; k < dimension; k++) {
                    value += at(row, k) * m.at(k, col);
                }
                result[idx(row, col)] = value;
            }
        }

        return new Matrix(result);
    }
}
