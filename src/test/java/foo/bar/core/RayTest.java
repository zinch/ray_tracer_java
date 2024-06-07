package foo.bar.core;

import foo.bar.geom.Point;
import foo.bar.geom.Vector;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class RayTest {
    @Test
    public void should_create_ray() {
        // given
        var origin = new Point(1, 2, 3);
        var direction = new Vector(4, 5, 6);

        // when
        var ray = new Ray(origin, direction);

        // then
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(ray.origin()).isSameAs(origin);
            it.assertThat(ray.direction()).isSameAs(direction);
        });
    }
}