package foo.bar.math;

import static org.assertj.core.api.Assertions.assertThat;

import foo.bar.geom.Tuple;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class MatrixTest {
    @Test
    public void should_create_4x4_matrix() {
        // given
        var matrix = new Matrix(
                new double[] {
                        1, 2, 3, 4,
                        5.5, 6.5, 7.5, 8.5,
                        9, 10, 11, 12,
                        13.5, 14.5, 15.5, 16.5
                }
        );

        // then
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(matrix.at(0, 0)).isEqualTo(1);
            it.assertThat(matrix.at(0, 3)).isEqualTo(4);
            it.assertThat(matrix.at(1, 2)).isEqualTo(7.5);
            it.assertThat(matrix.at(2, 2)).isEqualTo(11);
            it.assertThat(matrix.at(3, 0)).isEqualTo(13.5);
            it.assertThat(matrix.at(3, 2)).isEqualTo(15.5);
        });
    }

    @Test
    public void should_create_2x2_matrix() {
        // given
        var matrix = new Matrix(
                new double[] {
                        -3, 5,
                        1, -2
                }
        );

        // then
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(matrix.at(0, 0)).isEqualTo(-3);
            it.assertThat(matrix.at(0, 1)).isEqualTo(5);
            it.assertThat(matrix.at(1, 0)).isEqualTo(1);
            it.assertThat(matrix.at(1, 1)).isEqualTo(-2);
        });
    }

    @Test
    public void should_create_3x3_matrix() {
        // given
        var matrix = new Matrix(
                new double[] {
                        -3, 5, 0,
                        1, -2, -7,
                        0, 0, 1
                }
        );

        // then
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(matrix.at(0, 0)).isEqualTo(-3);
            it.assertThat(matrix.at(0, 1)).isEqualTo(5);
            it.assertThat(matrix.at(1, 0)).isEqualTo(1);
            it.assertThat(matrix.at(1, 1)).isEqualTo(-2);
            it.assertThat(matrix.at(2, 2)).isEqualTo(1);
        });
    }

    @Test
    public void should_test_matrices_for_equality() {
        // given
        var m1 = new Matrix(
                new double[] {
                        1, 2, 3, 4,
                        5.5, 6.5, 7.5, 8.5,
                        9, 10, 11, 12,
                        13.5, 14.5, 15.5, 16.5
                }
        );
        var m2 = new Matrix(
                new double[] {
                        1, 2, 3, 4 - 1e-7,
                        5.5, 6.5, 7.5, 8.5,
                        9, 10, 11, 12 + 1e-7,
                        13.5, 14.5, 15.5, 16.5
                }
        );

        // then
        assertThat(m1).isEqualTo(m2);
        assertThat(m2).isEqualTo(m1);
    }

    @Test
    public void should_detect_non_equal_matrices() {
        // given
        var m1 = new Matrix(
                new double[] {
                        1, 2, 3,
                        5.5, 6.5, 7.5,
                        9, 10, 11
                }
        );
        var m2 = new Matrix(
                new double[] {
                        2, 3, 4,
                        6.5, 7.5, 8.5,
                        10, 11, 12
                }
        );

        // then
        assertThat(m1).isNotEqualTo(m2);
        assertThat(m2).isNotEqualTo(m1);
    }

    @Test
    public void should_multiply_two_matrices() {
        // given
        var m1 = new Matrix(
                new double[] {
                        1, 2, 3, 4,
                        5, 6, 7, 8,
                        9, 8, 7, 6,
                        5, 4, 3, 2
                }
        );
        var m2 = new Matrix(
                new double[] {
                        -2, 1, 2, 3,
                        3, 2, 1, -1,
                        4, 3, 6, 5,
                        1, 2, 7, 8
                }
        );

        // when
        var m3 = m1.multiply(m2);

        // then
        assertThat(m3).isEqualTo(new Matrix(new double[] {
                20, 22, 50, 48,
                44, 54, 114, 108,
                40, 58, 110, 102,
                16, 26, 46, 42
        }));
    }

    @Test
    public void should_multiply_matrix_by_typle() {
        // given
        var m = new Matrix(
                new double[] {
                        1, 2, 3, 4,
                        2, 4, 4, 2,
                        8, 6, 4, 1,
                        0, 0, 0, 1
                }
        );
        var t = new Tuple(1, 2, 3, 1);

        // when
        var t2 = m.multiply(t);

        // then
        assertThat(t2).isEqualTo(new Tuple(18, 24, 33, 1));
    }

    @Test
    public void should_not_change_original_matrix_after_multiplying_by_identity_matrix() {
        // given
        var m1 = new Matrix(
                new double[] {
                        0, 1, 2, 4,
                        1, 2, 4, 8,
                        2, 4, 8, 16,
                        4, 8, 16, 32
                }
        );

        // when
        var m2 = m1.multiply(Matrix.IDENTITY);

        // then
        assertThat(m2).isEqualTo(new Matrix(new double[] {
                0, 1, 2, 4,
                1, 2, 4, 8,
                2, 4, 8, 16,
                4, 8, 16, 32
        }));
    }

    @Test
    public void should_not_change_original_tuple_after_multiplying_by_identity_matrix() {
        // given
        var t1 = new Tuple(1, 2, 3, 4);

        // when
        var t2 = Matrix.IDENTITY.multiply(t1);

        // then
        assertThat(t2).isEqualTo(new Tuple(1, 2, 3, 4));
    }

    @Test
    public void should_transpose_matrix() {
        // given
        var m1 = new Matrix(
                new double[] {
                        0, 9, 3, 0,
                        9, 8, 0, 8,
                        1, 8, 5, 3,
                        0, 0, 5, 8
                }
        );

        // when
        var m2 = m1.transpose();

        // then
        assertThat(m2).isEqualTo(new Matrix(new double[] {
                0, 9, 1, 0,
                9, 8, 8, 0,
                3, 0, 5, 5,
                0, 8, 3, 8
        }));
    }

    @Test
    public void should_not_affect_identity_matrix_by_transposing() {
        assertThat(Matrix.IDENTITY.transpose()).isEqualTo(Matrix.IDENTITY);
    }

    @Test
    public void should_calculate_determinant_of_2x2_matrix() {
        // given
        var m = new Matrix(new double[] {
                1, 5,
                -3, 2
        });

        // then
        assertThat(m.determinant()).isEqualTo(17);
    }

    @Test
    public void should_calculate_submatrix_of_4x4_matrix() {
        // given
        var m = new Matrix(new double[] {
                -6, 1, 1, 6,
                -8, 5, 8, 6,
                -1, 0, 8, 2,
                -7, 1, -1, 1
        });

        // when
        var s = m.submatrix(2, 1);

        // then
        assertThat(s).isEqualTo(new Matrix(new double[] {
                -6, 1, 6,
                -8, 8, 6,
                -7, -1, 1
        }));
    }

    @Test
    public void should_calculate_submatrix_of_3x3_matrix() {
        // given
        var m = new Matrix(new double[] {
                1, 5, 0,
                -3, 2, 7,
                0, 6, -3
        });

        // when
        var s = m.submatrix(0, 2);

        // then
        assertThat(s).isEqualTo(new Matrix(new double[] {
                -3, 2,
                0, 6
        }));
    }

    @Test
    public void should_calculate_minor_of_3x3_matrix() {
        // given
        var m = new Matrix(new double[] {
                3, 5, 0,
                2, -1, 7,
                6, -1, 5
        });

        // when
        var m1 = m.submatrix(1, 0);

        // then
        assertThat(m1.determinant()).isEqualTo(25);
        assertThat(m.minor(1, 0)).isEqualTo(25);
    }

    @Test
    public void should_calculate_cofactor_of_3x3_matrix() {
        // given
        var m = new Matrix(new double[] {
                3, 5, 0,
                2, -1, -7,
                6, -1, 5
        });

        // then
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(m.minor(0, 0)).isEqualTo(-12);
            it.assertThat(m.cofactor(0, 0)).isEqualTo(-12);
            it.assertThat(m.minor(1, 0)).isEqualTo(25);
            it.assertThat(m.cofactor(1, 0)).isEqualTo(-25);
        });
    }

    @Test
    public void should_calculate_determinant_of_3x3_matrix() {
        // given
        var m = new Matrix(new double[] {
                1, 2, 6,
                -5, 8, -4,
                2, 6, 4
        });

        // then
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(m.cofactor(0, 0)).isEqualTo(56);
            it.assertThat(m.cofactor(0, 1)).isEqualTo(12);
            it.assertThat(m.cofactor(0, 2)).isEqualTo(-46);
            it.assertThat(m.determinant()).isEqualTo(-196);
        });
    }

    @Test
    public void should_calculate_determinant_of_4x4_matrix() {
        // given
        var m = new Matrix(new double[] {
                -2, -8, 3, 5,
                -3, 1, 7, 3,
                1, 2, -9, 6,
                -6, 7, 7, -9
        });

        // then
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(m.cofactor(0, 0)).isEqualTo(690);
            it.assertThat(m.cofactor(0, 1)).isEqualTo(447);
            it.assertThat(m.cofactor(0, 2)).isEqualTo(210);
            it.assertThat(m.cofactor(0, 3)).isEqualTo(51);
            it.assertThat(m.determinant()).isEqualTo(-4071);
        });
    }

    @Test
    public void should_determine_invertible_matrix() {
        // given
        var m = new Matrix(new double[] {
                6, 4, 4, 4,
                5, 5, 7, 6,
                4, -9, 3, -7,
                9, 1, 7, -6
        });

        // then
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(m.determinant()).isEqualTo(-2120);
            it.assertThat(m.isInvertible()).isTrue();
        });
    }

    @Test
    public void should_determine_non_invertible_matrix() {
        // given
        var m = new Matrix(new double[] {
                -4, 2, -2, -3,
                9, 6, 2, 6,
                0, -5, 1, -5,
                0, 0, 0, 0
        });

        // then
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(m.determinant()).isEqualTo(0);
            it.assertThat(m.isInvertible()).isFalse();
        });
    }

    @Test
    public void should_calculate_inverse_of_a_matrix() {
        // given
        var m = new Matrix(new double[] {
                -5, 2, 6, -8,
                1, -5, 1, 8,
                7, 7, -6, -7,
                1, -3, 7, 4
        });

        // when
        var m1 = m.inverse();

        // then
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(m.determinant()).isEqualTo(532);
            it.assertThat(m.cofactor(2, 3)).isEqualTo(-160);
            it.assertThat(m.cofactor(3, 2)).isEqualTo(105);
            it.assertThat(m1.at(2, 3)).isEqualTo(105.0 / 532.0);
            it.assertThat(m.cofactor(3, 2)).isEqualTo(105);
            it.assertThat(m1.at(3, 2)).isEqualTo(-160.0 / 532.0);
            it.assertThat(m1).isEqualTo(new Matrix(new double[] {
                0.21805, 0.45113, 0.24060, -0.04511,
                -0.80827, -1.45677, -0.44361, 0.52068,
                -0.07895, -0.22368, -0.05263, 0.19737,
                -0.52256, -0.81391, -0.30075, 0.30639
            }));
        });
    }

    @Test
    public void should_calculate_inverse_of_other_matrices() {
        assertThat(new Matrix(new double[] {
            8, -5, 9, 2,
            7, 5, 6, 1,
            -6, 0, 9, 6,
            -3, 0, -9, -4
        }).inverse()).isEqualTo(new Matrix(new double[] {
            -0.15385, -0.15385, -0.28205, -0.53846,
            -0.07692, 0.12308, 0.02564, 0.03077,
            0.35897, 0.35897, 0.43590, 0.92308,
            -0.69231, -0.69231, -0.76923, -1.92308
        }));

        assertThat(new Matrix(new double[] {
            9, 3, 0, 9,
            -5, -2, -6, -3,
            -4, 9, 6, 4,
            -7, 6, 6, 2,
        }).inverse()).isEqualTo(new Matrix(new double[] {
            -0.04074, -0.07778, 0.14444, -0.22222,
            -0.07778, 0.03333, 0.36667, -0.33333,
            -0.02901, -0.14630, -0.10926, 0.12963,
            0.17778, 0.06667, -0.26667, 0.33333
        }));
    }

    @Test
    public void should_multiply_product_by_matrix_inverse() {
        // given
        var m1 = new Matrix(new double[] {
            3, -9, 7, 3,
            3, -8, 2, -9,
            -4, 4, 4, 1,
            -6, 5, -1, 1
        });

        var m2 = new Matrix(new double[] {
            8, 2, 2, 2,
            3, -1, 7, 0,
            7, 0, 5, 4,
            6, -2, 0, 5
        });

        // when
        var m3 = m1.multiply(m2);
        assertThat(m3.multiply(m2.inverse())).isEqualTo(m1);
    }
}
