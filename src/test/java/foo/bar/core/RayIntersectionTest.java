package foo.bar.core;

import foo.bar.geom.Sphere;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class RayIntersectionTest {
    @Test
    public void should_encapsulate_t_and_object_in_an_intersection() {
        // given
        var s = new Sphere();

        // when
        var i = new RayIntersection(3.5, s);

        // then
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(i.t()).isEqualTo(3.5);
            it.assertThat(i.object()).isSameAs(s);
        });
    }
}