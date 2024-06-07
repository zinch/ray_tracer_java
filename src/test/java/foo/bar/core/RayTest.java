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

    @Test
    public void should_compute_point_from_a_distance() {
        // given
        var ray = new Ray(new Point(2, 3, 4), new Vector(1, 0, 0));

        // then
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(ray.positionAt(0)).isEqualTo(new Point(2, 3, 4));
            it.assertThat(ray.positionAt(1)).isEqualTo(new Point(3, 3, 4));
            it.assertThat(ray.positionAt(-1)).isEqualTo(new Point(1, 3, 4));
            it.assertThat(ray.positionAt(2.5)).isEqualTo(new Point(4.5, 3, 4));
        });
    }
}