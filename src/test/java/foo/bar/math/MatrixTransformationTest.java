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
}
