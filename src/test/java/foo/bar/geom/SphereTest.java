package foo.bar.geom;

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
}