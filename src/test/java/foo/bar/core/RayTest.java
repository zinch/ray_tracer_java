package foo.bar.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

import foo.bar.geom.Point;
import foo.bar.geom.Sphere;
import foo.bar.geom.Vector;
import foo.bar.math.Matrix;
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

    @Test
    public void should_intersect_sphere_at_two_points() {
        // given
        var ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        var s = new Sphere();

        // when
        var intersections = ray.intersect(s);

        // then
        SoftAssertions.assertSoftly(it -> it.assertThat(intersections)
                .extracting(RayIntersection::t, RayIntersection::object)
                .containsExactly(tuple(4.0, s), tuple(6.0, s)));
    }

    @Test
    public void should_intersect_sphere_at_a_tangent() {
        // given
        var ray = new Ray(new Point(0, 1, -5), new Vector(0, 0, 1));
        var s = new Sphere();

        // when
        var intersections = ray.intersect(s);

        // then
        SoftAssertions.assertSoftly(it -> it.assertThat(intersections)
                .extracting(RayIntersection::t, RayIntersection::object)
                .containsExactly(tuple(5.0, s), tuple(5.0, s)));
    }

    @Test
    public void should_miss_a_sphere() {
        // given
        var ray = new Ray(new Point(0, 2, -5), new Vector(0, 0, 1));
        var s = new Sphere();

        // when
        var intersections = ray.intersect(s);

        // then
        assertThat(intersections).isEmpty();
    }

    @Test
    public void should_intersect_sphere_at_two_points_when_a_ray_originates_inside_of_a_sphere() {
        // given
        var ray = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        var s = new Sphere();

        // when
        var intersections = ray.intersect(s);

        // then
        SoftAssertions.assertSoftly(it -> it.assertThat(intersections)
                .extracting(RayIntersection::t, RayIntersection::object)
                .containsExactly(tuple(-1.0, s), tuple(1.0, s)));
    }

    @Test
    public void should_intersect_sphere_at_two_points_when_a_ray_is_ahead() {
        // given
        var ray = new Ray(new Point(0, 0, 5), new Vector(0, 0, 1));
        var s = new Sphere();

        // when
        var intersections = ray.intersect(s);

        // then
        SoftAssertions.assertSoftly(it -> it.assertThat(intersections)
                .extracting(RayIntersection::t, RayIntersection::object)
                .containsExactly(tuple(-6.0, s), tuple(-4.0, s)));
    }

    @Test
    public void should_intersect_scaled_sphere() {
        // given
        var r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        var s = new Sphere();
        s.setTransformation(Matrix.newScaling(2, 2, 2));

        // when
        var intersections = r.intersect(s);

        // then
        SoftAssertions.assertSoftly(it -> it.assertThat(intersections)
                .extracting(RayIntersection::t)
                .containsExactly(3.0, 7.0));
    }

    @Test
    public void should_intersect_translated_sphere() {
        // given
        var r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        var s = new Sphere();
        s.setTransformation(Matrix.newTranslation(5, 0, 0));

        // when
        var intersections = r.intersect(s);

        // then
        assertThat(intersections).isEmpty();
    }
}