package foo.bar.math;

import static org.assertj.core.api.Assertions.assertThat;

import foo.bar.geom.Point;
import foo.bar.geom.Vector;
import org.assertj.core.api.SoftAssertions;
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

    @Test
    public void should_rotate_point_around_x_axis() {
        // given
        var p = new Point(0, 1, 0);
        var halfQuarter = Matrix.newRotationX(Math.PI / 4);
        var fullQuarter = Matrix.newRotationX(Math.PI / 2);

        // then
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(halfQuarter.multiply(p)).isEqualTo(new Point(0, Math.sqrt(2) / 2, Math.sqrt(2) / 2));
            it.assertThat(fullQuarter.multiply(p)).isEqualTo(new Point(0, 0, 1));
        });
    }

    @Test
    public void should_rotate_point_around_x_axis_in_opposite_direction_with_inverse() {
        // given
        var p = new Point(0, 1, 0);
        var halfQuarter = Matrix.newRotationX(Math.PI / 4).inverse();

        // then
        assertThat(halfQuarter.multiply(p)).isEqualTo(new Point(0, Math.sqrt(2) / 2, -Math.sqrt(2) / 2));
    }

    @Test
    public void should_rotate_point_around_y_axis() {
        // given
        var p = new Point(0, 0, 1);
        var halfQuarter = Matrix.newRotationY(Math.PI / 4);
        var fullQuarter = Matrix.newRotationY(Math.PI / 2);

        // then
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(halfQuarter.multiply(p)).isEqualTo(new Point(Math.sqrt(2) / 2, 0, Math.sqrt(2) / 2));
            it.assertThat(fullQuarter.multiply(p)).isEqualTo(new Point(1, 0, 0));
        });
    }

    @Test
    public void should_rotate_point_around_y_axis_in_opposite_direction_with_inverse() {
        // given
        var p = new Point(0, 0, 1);
        var halfQuarter = Matrix.newRotationY(Math.PI / 4).inverse();

        // then
        assertThat(halfQuarter.multiply(p)).isEqualTo(new Point(-Math.sqrt(2) / 2, 0, Math.sqrt(2) / 2));
    }

    @Test
    public void should_rotate_point_around_z_axis() {
        // given
        var p = new Point(0, 1, 0);
        var halfQuarter = Matrix.newRotationZ(Math.PI / 4);
        var fullQuarter = Matrix.newRotationZ(Math.PI / 2);

        // then
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(halfQuarter.multiply(p)).isEqualTo(new Point(-Math.sqrt(2) / 2, Math.sqrt(2) / 2, 0));
            it.assertThat(fullQuarter.multiply(p)).isEqualTo(new Point(-1, 0, 0));
        });
    }

    @Test
    public void should_rotate_point_around_z_axis_in_opposite_direction_with_inverse() {
        // given
        var p = new Point(0, 1, 0);
        var halfQuarter = Matrix.newRotationZ(Math.PI / 4).inverse();

        // then
        assertThat(halfQuarter.multiply(p)).isEqualTo(new Point(Math.sqrt(2) / 2, Math.sqrt(2) / 2, 0));
    }

    @Test
    public void should_move_x_in_proportion_to_y_with_shearing_transformation() {
        // given
        var m = Matrix.newShearing(1, 0, 0, 0, 0, 0);
        var p = new Point(2, 3, 4);

        // then
        assertThat(m.multiply(p)).isEqualTo(new Point(5, 3, 4));
    }

    @Test
    public void should_move_x_in_proportion_to_z_with_shearing_transformation() {
        // given
        var m = Matrix.newShearing(0, 1, 0, 0, 0, 0);
        var p = new Point(2, 3, 4);

        // then
        assertThat(m.multiply(p)).isEqualTo(new Point(6, 3, 4));
    }

    @Test
    public void should_move_y_in_proportion_to_x_with_shearing_transformation() {
        // given
        var m = Matrix.newShearing(0, 0, 1, 0, 0, 0);
        var p = new Point(2, 3, 4);

        // then
        assertThat(m.multiply(p)).isEqualTo(new Point(2, 5, 4));
    }

    @Test
    public void should_move_y_in_proportion_to_z_with_shearing_transformation() {
        // given
        var m = Matrix.newShearing(0, 0, 0, 1, 0, 0);
        var p = new Point(2, 3, 4);

        // then
        assertThat(m.multiply(p)).isEqualTo(new Point(2, 7, 4));
    }

    @Test
    public void should_move_z_in_proportion_to_x_with_shearing_transformation() {
        // given
        var m = Matrix.newShearing(0, 0, 0, 0, 1, 0);
        var p = new Point(2, 3, 4);

        // then
        assertThat(m.multiply(p)).isEqualTo(new Point(2, 3, 6));
    }

    @Test
    public void should_move_z_in_proportion_to_y_with_shearing_transformation() {
        // given
        var m = Matrix.newShearing(0, 0, 0, 0, 0, 1);
        var p = new Point(2, 3, 4);

        // then
        assertThat(m.multiply(p)).isEqualTo(new Point(2, 3, 7));
    }

    @Test
    public void should_apply_individual_transformations() {
        // given
        var p1 = new Point(1, 0, 1);

        var A = Matrix.newRotationX(Math.PI / 2);
        var B = Matrix.newScaling(5, 5, 5);
        var C = Matrix.newTranslation(10, 5, 7);

        // then
        var p2 = A.multiply(p1);
        assertThat(p2).isEqualTo(new Point(1, -1, 0));

        var p3 = B.multiply(p2);
        assertThat(p3).isEqualTo(new Point(5, -5, 0));

        var p4 = C.multiply(p3);
        assertThat(p4).isEqualTo(new Point(15, 0, 7));
    }

    @Test
    public void should_chain_multiple_transformations() {
        // given
        var p1 = new Point(1, 0, 1);

        var A = Matrix.newRotationX(Math.PI / 2);
        var B = Matrix.newScaling(5, 5, 5);
        var C = Matrix.newTranslation(10, 5, 7);

        var T = C.multiply(B.multiply(A));

        // then
        var p2 = T.multiply(p1);
        assertThat(p2).isEqualTo(new Point(15, 0, 7));
    }
}
