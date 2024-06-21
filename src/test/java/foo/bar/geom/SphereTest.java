package foo.bar.geom;

import static org.assertj.core.api.Assertions.assertThat;

import foo.bar.math.Matrix;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class SphereTest {
    @Test
    public void should_assign_id_to_a_new_sphere() {
        // given
        var s = new Sphere();

        // then
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(s.getId()).hasSize(10);
            it.assertThat(s.getTransformation()).isSameAs(Matrix.IDENTITY);
        });
    }

    @Test
    public void should_allow_changing_transformation() {
        // given
        var s = new Sphere();
        Matrix m = Matrix.newTranslation(2, 3, 4);

        // when
        s.setTransformation(m);

        // then
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(s.getId()).hasSize(10);
            it.assertThat(s.getTransformation()).isSameAs(m);
        });
    }

    @Test
    public void should_compute_normal_of_a_sphere_on_x_axis() {
        // given
        var s = new Sphere();

        // when
        var n = s.normalAt(new Point(1, 0, 0));

        // then
        assertThat(n).isEqualTo(new Vector(1, 0, 0));
    }

    @Test
    public void should_compute_normal_of_a_sphere_on_y_axis() {
        // given
        var s = new Sphere();

        // when
        var n = s.normalAt(new Point(0, 1, 0));

        // then
        assertThat(n).isEqualTo(new Vector(0, 1, 0));
    }

    @Test
    public void should_compute_normal_of_a_sphere_on_z_axis() {
        // given
        var s = new Sphere();

        // when
        var n = s.normalAt(new Point(0, 0, 1));

        // then
        assertThat(n).isEqualTo(new Vector(0, 0, 1));
    }

    @Test
    public void should_compute_normal_of_a_sphere_on_non_axial_axis() {
        // given
        var s = new Sphere();

        // when
        var n = s.normalAt(new Point(Math.sqrt(3) / 3, Math.sqrt(3) / 3, Math.sqrt(3) / 3));

        // then
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(n).isEqualTo(new Vector(Math.sqrt(3) / 3, Math.sqrt(3) / 3, Math.sqrt(3) / 3));
            it.assertThat(n).isEqualTo(n.normalize());
        });
    }
}