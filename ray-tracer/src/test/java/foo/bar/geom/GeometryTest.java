package foo.bar.geom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class GeometryTest {
    @Test
    public void should_create_point() {
        // when
        var point = Geometry.newPoint(4.3, -4.2, 3.1);

        // then
        validatePointComponents(point, 4.3, -4.2, 3.1);
    }

    @Test
    public void should_create_vector() {
        // when
        var vector = Geometry.newVector(4.3, -4.2, 3.1);

        // then
        validateVectorComponents(vector, 4.3, -4.2, 3.1);
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
        validatePointComponents(p1, 1, 1, 6);
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

    @Test
    public void should_produce_vector_when_subtracting_two_points() {
        // given
        var p1 = Geometry.newPoint(3, 2, 1);
        var p2 = Geometry.newPoint(5, 6, 7);

        // when
        var v = Geometry.subtract(p1, p2);

        // then
        validateVectorComponents(v, -2, -4, -6);
    }

    @Test
    public void should_produce_point_when_subtracting_vector_from_a_point() {
        // given
        var p1 = Geometry.newPoint(3, 2, 1);
        var v = Geometry.newVector(5, 6, 7);

        // when
        var p2 = Geometry.subtract(p1, v);

        // then
        validatePointComponents(p2, -2, -4, -6);
    }

    @Test
    public void should_produce_vector_when_subtracting_two_vectors() {
        // given
        var v1 = Geometry.newVector(3, 2, 1);
        var v2 = Geometry.newVector(5, 6, 7);

        // when
        var v3 = Geometry.subtract(v1, v2);

        // then
        validateVectorComponents(v3, -2, -4, -6);
    }

    @Test
    public void should_never_subtract_point_from_a_vector() {
        // given
        var p1 = Geometry.newPoint(3, 2, 1);
        var v = Geometry.newVector(5, 6, 7);

        // then
        Assertions.assertThatThrownBy(() -> Geometry.subtract(v, p1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot subtract point from vector");
    }

    @Test
    public void should_subtract_vector_from_the_zero_vector() {
        // given
        var v = Geometry.newVector(1, -2, 3);

        // when
        var v1 = Geometry.subtract(Geometry.newVector(0, 0, 0), v);

        // then
        validateVectorComponents(v1, -1, 2, -3);
    }

    @Test
    public void should_negate_vector() {
        // given
        var v = Geometry.newVector(1, -2, 3);

        // then
        validateVectorComponents(Geometry.negate(v), -1, 2, -3);
    }

    @Test
    public void should_never_negate_point() {
        // given
        var p1 = Geometry.newPoint(3, 2, 1);

        // then
        Assertions.assertThatThrownBy(() -> Geometry.negate(p1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot negate point");
    }

    @Test
    public void should_multiply_vector_by_scalar() {
        // given
        var v = Geometry.newVector(1, -2, 3);

        // when
        var v1 = Geometry.multiply(v, 3.5);

        // then
        validateVectorComponents(v1, 3.5, -7, 10.5);
    }

    @Test
    public void should_multiply_vector_by_fraction() {
        // given
        var v = Geometry.newVector(1, -2, 3);

        // when
        var v1 = Geometry.multiply(v, 0.5);

        // then
        validateVectorComponents(v1, 0.5, -1, 1.5);
    }

    @Test
    public void should_never_multiply_point_by_scalar() {
        // given
        var p1 = Geometry.newPoint(3, 2, 1);

        // then
        Assertions.assertThatThrownBy(() -> Geometry.multiply(p1, 3.5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot multiply point by scalar");
    }

    @Test
    public void should_calculate_magnitude_of_unit_vector_x() {
        // given
        var v = Geometry.newVector(1, 0, 0);

        // then
        assertThat(Geometry.magnitude(v)).isEqualTo(1);
    }

    @Test
    public void should_calculate_magnitude_of_unit_vector_y() {
        // given
        var v = Geometry.newVector(0, 1, 0);

        // then
        assertThat(Geometry.magnitude(v)).isEqualTo(1);
    }

    @Test
    public void should_calculate_magnitude_of_unit_vector_z() {
        // given
        var v = Geometry.newVector(0, 0, 1);

        // then
        assertThat(Geometry.magnitude(v)).isEqualTo(1);
    }

    @Test
    public void should_calculate_magnitude_of_one_vector() {
        // given
        var v = Geometry.newVector(1, 2, 3);

        // then
        assertThat(Geometry.magnitude(v)).isEqualTo(Math.sqrt(14), within(1e-6));
    }

    @Test
    public void should_calculate_magnitude_of_another_vector() {
        // given
        var v = Geometry.newVector(-1, -2, -3);

        // then
        assertThat(Geometry.magnitude(v)).isEqualTo(Math.sqrt(14), within(1e-6));
    }

    @Test
    public void should_never_calculate_magnitude_of_a_point() {
        // given
        var p1 = Geometry.newPoint(3, 2, 1);

        // then
        Assertions.assertThatThrownBy(() -> Geometry.magnitude(p1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot calculate magnitude of a point");
    }

    @Test
    public void should_normalize_vector_x() {
        // given
        var v = Geometry.newVector(4, 0, 0);

        // when
        var u = Geometry.normalize(v);

        // then
        validateVectorComponents(u, 1, 0, 0);
    }

    @Test
    public void should_never_normalize_a_point() {
        // given
        var p1 = Geometry.newPoint(3, 2, 1);

        // then
        Assertions.assertThatThrownBy(() -> Geometry.normalize(p1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot normalize a point");
    }

    private static void validatePointComponents(double[] vector, double x, double y, double z) {
        validateTupleComponents(vector, x, y, z, 1.0);
    }

    private static void validateVectorComponents(double[] vector, double x, double y, double z) {
        validateTupleComponents(vector, x, y, z, 0.0);
    }

    private static void validateTupleComponents(double[] vector, double x, double y, double z, double w) {
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(vector[0]).isEqualTo(x);
            it.assertThat(vector[1]).isEqualTo(y);
            it.assertThat(vector[2]).isEqualTo(z);
            it.assertThat(vector[3]).isEqualTo(w);
        });
    }
}