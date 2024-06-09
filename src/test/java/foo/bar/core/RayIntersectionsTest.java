package foo.bar.core;

import static org.assertj.core.api.Assertions.assertThat;

import foo.bar.geom.Sphere;
import org.junit.Test;

public class RayIntersectionsTest {
    @Test
    public void should_find_the_hit_when_all_intersections_have_positive_t() {
        // given
        var s = new Sphere();
        var i1 = new RayIntersection(1, s);
        var i2 = new RayIntersection(2, s);

        var xs = new RayIntersection[] {i1, i2};

        // when
        var hit = RayIntersection.findHit(xs);

        // then
        assertThat(hit).isSameAs(i1);
    }

    @Test
    public void should_find_the_hit_when_some_intersections_have_positive_t() {
        // given
        var s = new Sphere();
        var i1 = new RayIntersection(-1, s);
        var i2 = new RayIntersection(1, s);

        var xs = new RayIntersection[] {i1, i2};

        // when
        var hit = RayIntersection.findHit(xs);

        // then
        assertThat(hit).isSameAs(i2);
    }

    @Test
    public void should_not_find_the_hit_when_all_intersections_have_negative_t() {
        // given
        var s = new Sphere();
        var i1 = new RayIntersection(-2, s);
        var i2 = new RayIntersection(-1, s);

        var xs = new RayIntersection[] {i1, i2};

        // when
        var hit = RayIntersection.findHit(xs);

        // then
        assertThat(hit).isNull();
    }

    @Test
    public void should_find_hit_when_intersections_are_not_ordered() {
        // given
        var s = new Sphere();
        var i1 = new RayIntersection(5, s);
        var i2 = new RayIntersection(7, s);
        var i3 = new RayIntersection(-3, s);
        var i4 = new RayIntersection(2, s);

        var xs = new RayIntersection[] {i1, i2, i3, i4};

        // when
        var hit = RayIntersection.findHit(xs);

        // then
        assertThat(hit).isSameAs(i4);
    }
}