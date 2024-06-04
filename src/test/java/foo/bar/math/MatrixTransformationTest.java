package foo.bar.math;

import static org.assertj.core.api.Assertions.assertThat;

import foo.bar.geom.Point;
import foo.bar.geom.Vector;
import org.junit.Test;

public class MatrixTransformationTest {
    @Test
    public void should_multiply_point_by_translation_matrix() {
        // given
        var m = Matrix.newTranslation(5, -3, 2);
        var p = new Point(-3, 4, 5);

        // then
        assertThat(m.multiply(p)).isEqualTo(new Point(2, 1, 7));
    }

    @Test
    public void should_multiply_point_by_inverse_of_translation_matrix() {
        // given
        var m = Matrix.newTranslation(5, -3, 2).inverse();
        var p = new Point(-3, 4, 5);

        // then
        assertThat(m.multiply(p)).isEqualTo(new Point(-8, 7, 3));
    }

    @Test
    public void should_not_change_vectory_when_multiplying_by_translation_matrix() {
        // given
        var m = Matrix.newTranslation(5, -3, 2).inverse();
        var v = new Vector(-3, 4, 5);

        // then
        assertThat(m.multiply(v)).isEqualTo(new Vector(-3, 4, 5));
    }

    @Test
    public void should_multiply_point_by_a_scaling_matrix() {
        // given
        var m = Matrix.newScaling(2, 3, 4);
        var p = new Point(-4, 6, 8);

        // then
        assertThat(m.multiply(p)).isEqualTo(new Point(-8, 18, 32));
    }

    @Test
    public void should_multiply_vector_by_a_scaling_matrix() {
        // given
        var m = Matrix.newScaling(2, 3, 4);
        var v = new Vector(-4, 6, 8);

        // then
        assertThat(m.multiply(v)).isEqualTo(new Vector(-8, 18, 32));
    }

    @Test
    public void should_multiply_vector_by_inverse_of_a_scaling_matrix() {
        // given
        var m = Matrix.newScaling(2, 3, 4).inverse();
        var v = new Vector(-4, 6, 8);

        // then
        assertThat(m.multiply(v)).isEqualTo(new Vector(-2, 2, 2));
    }

    @Test
    public void should_reflect_point_across_x_axis_with_a_scaling_matrix() {
        // given
        var m = Matrix.newScaling(-1, 1, 1);
        var p = new Point(2, 3, 4);

        // then
        assertThat(m.multiply(p)).isEqualTo(new Point(-2, 3, 4));
    }
}
