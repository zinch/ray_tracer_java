package foo.bar.math;

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
}