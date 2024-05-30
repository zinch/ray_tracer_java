package foo.bar.geom;


import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class GeometryTest {
    @Test
    public void should_create_point() {
        // when
        var point = Geometry.newPoint(4.3, -4.2, 3.1);

        // then
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(point[0]).isEqualTo(4.3);
            it.assertThat(point[1]).isEqualTo(-4.2);
            it.assertThat(point[2]).isEqualTo(3.1);
            it.assertThat(point[3]).isEqualTo(1.0);
        });
    }

    @Test
    public void should_create_vector() {
        // when
        var vector = Geometry.newVector(4.3, -4.2, 3.1);

        // then
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(vector[0]).isEqualTo(4.3);
            it.assertThat(vector[1]).isEqualTo(-4.2);
            it.assertThat(vector[2]).isEqualTo(3.1);
            it.assertThat(vector[3]).isEqualTo(0.0);
        });
    }

    @Test
    public void should_compare_two_points() {
        // given
        var epsilon = 1e-6;
        var p1 = Geometry.newPoint(1.2, 3.4, 5.6);
        var p2 = Geometry.newPoint(1.2 + epsilon, 3.4 - epsilon, 5.6 + epsilon);

        // then
        assertThat(Geometry.equal(p1, p2)).isTrue();
    }

    @Test
    public void should_add_two_vector_to_a_point() {
        // given
        var p = Geometry.newPoint(3, -2, 5);
        var v = Geometry.newVector(-2, 3, 1);

        // when
        var p1 = Geometry.add(p, v);

        // then
        assertThat(Geometry.equal(p1, Geometry.newPoint(1, 1, 6))).isTrue();
    }

    @Test
    public void should_never_add_two_points() {
        // given
        var p1 = Geometry.newPoint(3, -2, 5);
        var p2 = Geometry.newPoint(-2, 3, 1);

        // then
        Assertions.assertThatThrownBy(() -> Geometry.add(p1, p2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot add points");
    }
}