package foo.bar.math;

import foo.bar.geom.Point;
import foo.bar.geom.Tuple;
import foo.bar.geom.Vector;

import java.util.Arrays;
import java.util.Objects;

public class Matrix {
    public static final Matrix IDENTITY = new Matrix(new double[] {
        1, 0, 0, 0,
        0, 1, 0, 0,
        0, 0, 1, 0,
        0, 0, 0, 1
    });

    private final double[] values;
    private final int dimension;
    private Matrix inverse;
    private Matrix transpose;

    public Matrix(double[] values) {
        Objects.requireNonNull(values);
        if (values.length != 4 && values.length != 9 && values.length != 16) {
            throw new IllegalArgumentException("Matrix can have 4, 9 or 16 elements");
        }
        this.values = values;
        this.dimension = (int) Math.sqrt(values.length);
    }

    public static Matrix newTranslation(double x, double y, double z) {
        return new Matrix(new double[] {
            1, 0, 0, x,
            0, 1, 0, y,
            0, 0, 1, z,
            0, 0, 0, 1
        });
    }

    public static Matrix newScaling(double a, double b, double c) {
        return new Matrix(new double[] {
            a, 0, 0, 0,
            0, b, 0, 0,
            0, 0, c, 0,
            0, 0, 0, 1
        });
    }

    public static Matrix newRotationX(double rad) {
        return new Matrix(new double[] {
            1, 0, 0, 0,
            0, Math.cos(rad), -Math.sin(rad), 0,
            0, Math.sin(rad), Math.cos(rad), 0,
            0, 0, 0, 1
        });
    }

    public static Matrix newRotationY(double rad) {
        return new Matrix(new double[] {
            Math.cos(rad), 0, Math.sin(rad), 0,
            0, 1, 0, 0,
            -Math.sin(rad), 0, Math.cos(rad), 0,
            0, 0, 0, 1
        });
    }

    public static Matrix newRotationZ(double rad) {
        return new Matrix(new double[] {
            Math.cos(rad), -Math.sin(rad), 0, 0,
            Math.sin(rad), Math.cos(rad), 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
        });
    }

    public static Matrix newShearing(double xy, double xz, double yx, double yz, double zx, double zy) {
        return new Matrix(new double[] {
            1, xy, xz, 0,
            yx, 1, yz, 0,
            zx, zy, 1, 0,
            0, 0, 0, 1
        });
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

    public Tuple multiply(Tuple t) {
        if (dimension != 4) {
            throw new IllegalArgumentException("Can multiply only 4x4 matrix by tuple");
        }
        double x = at(0, 0) * t.x() + at(0, 1) * t.y() + at(0, 2) * t.z() + at(0, 3) * t.w();
        double y = at(1, 0) * t.x() + at(1, 1) * t.y() + at(1, 2) * t.z() + at(1, 3) * t.w();
        double z = at(2, 0) * t.x() + at(2, 1) * t.y() + at(2, 2) * t.z() + at(2, 3) * t.w();
        double w = at(3, 0) * t.x() + at(3, 1) * t.y() + at(3, 2) * t.z() + at(3, 3) * t.w();

        return new Tuple(x, y, z, w);
    }

    public Point multiply(Point p) {
        var t = multiply(p.asTuple());
        return new Point(t);
    }

    public Vector multiply(Vector v) {
        var t = multiply(v.asTuple());
        return new Vector(t);
    }

    public Matrix transpose() {
        if (transpose != null) {
            return transpose;
        }
        var result = new double[dimension * dimension];
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                result[idx(row, col)] = at(col, row);
            }
        }
        transpose = new Matrix(result);
        return transpose;
    }

    public double determinant() {
        if (dimension == 2) {
            return values[0] * values[3] - values[1] * values[2];
        }
        double det = 0;
        for (int col = 0; col < dimension; col++) {
            det += at(0, col) * cofactor(0, col);
        }
        return det;
    }

    public Matrix submatrix(int excludedRow, int excludedCol) {
        if (dimension == 2) {
            throw new IllegalStateException("Cannot get of submatrix 2x2 matrix");
        }
        int newDimension = dimension - 1;
        var result = new double[newDimension * newDimension];

        for (int row = 0, newRow = 0; row < dimension; row++) {
            if (row == excludedRow) {
                continue;
            }
            for (int col = 0, newCol = 0; col < dimension; col++) {
                if (col == excludedCol) {
                    continue;
                }
                result[newRow * newDimension + newCol] = at(row, col);
                ++newCol;
            }
            ++newRow;
        }
        return new Matrix(result);
    }

    public double minor(int row, int col) {
        return submatrix(row, col).determinant();
    }

    public double cofactor(int row, int col) {
        var sign = (row + col) % 2 == 0 ? 1 : -1;
        return sign * submatrix(row, col).determinant();
    }

    public boolean isInvertible() {
        return !MathUtils.areEqual(determinant(), 0);
    }

    public Matrix inverse() {
        if (inverse != null) {
            return inverse;
        }

        if (!isInvertible()) {
            throw new IllegalStateException("Matrix is not invertible");
        }
        var determinant = determinant();
        var cofactors = new double[dimension * dimension];
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                cofactors[idx(col, row)] = cofactor(row, col) / determinant;
            }
        }
        inverse = new Matrix(cofactors);
        return inverse;
    }

    public Matrix translate(int x, int y, int z) {
        return Matrix.newTranslation(x, y, z).multiply(this);
    }

    public Matrix scale(double a, double b, double c) {
        return Matrix.newScaling(a, b, c).multiply(this);
    }

    public Matrix rotateX(double rad) {
        return Matrix.newRotationX(rad).multiply(this);
    }

    public Matrix rotateY(double rad) {
        return Matrix.newRotationY(rad).multiply(this);
    }

    public Matrix rotateZ(double rad) {
        return Matrix.newRotationZ(rad).multiply(this);
    }

    public Matrix shear(int xy, int xz, int yx, int yz, int zx, int zy) {
        return Matrix.newShearing(xy, xz, yx, yz, zx, zy).multiply(this);
    }
}
