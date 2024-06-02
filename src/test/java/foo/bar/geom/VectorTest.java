package foo.bar.geom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import org.junit.Test;

public class VectorTest {
    @Test
    public void should_create_vector() {
        // when
        var vector = new Vector(4.3, -4.2, 3.1);

        // then
        TestUtils.validateVectorComponents(vector, 4.3, -4.2, 3.1);
    }

    @Test
    public void should_produce_vector_when_subtracting_two_vectors() {
        // given
        var v1 = new Vector(3, 2, 1);
        var v2 = new Vector(5, 6, 7);

        // when
        var v3 = v1.subtract(v2);

        // then
        TestUtils.validateVectorComponents(v3, -2, -4, -6);
    }

    @Test
    public void should_produce_vector_when_adding_two_vectors() {
        // given
        var v1 = new Vector(5, 6, 7);
        var v2 = new Vector(-2, -4, -6);

        // when
        var v3 = v1.add(v2);

        // then
        TestUtils.validateVectorComponents(v3, 3, 2, 1);
    }

    @Test
    public void should_subtract_vector_from_the_zero_vector() {
        // given
        var v = new Vector(1, -2, 3);

        // when
        var v1 = new Vector(0, 0, 0).subtract(v);

        // then
        TestUtils.validateVectorComponents(v1, -1, 2, -3);
    }

    @Test
    public void should_negate_vector() {
        // given
        var v = new Vector(1, -2, 3);

        // then
        TestUtils.validateVectorComponents(v.negate(), -1, 2, -3);
    }

    @Test
    public void should_multiply_vector_by_scalar() {
        // given
        var v = new Vector(1, -2, 3);

        // when
        var v1 = v.multiply(3.5);

        // then
        TestUtils.validateVectorComponents(v1, 3.5, -7, 10.5);
    }

    @Test
    public void should_multiply_vector_by_fraction() {
        // given
        var v = new Vector(1, -2, 3);

        // when
        var v1 = v.multiply(0.5);

        // then
        TestUtils.validateVectorComponents(v1, 0.5, -1, 1.5);
    }

    @Test
    public void should_calculate_magnitude_of_unit_vector_x() {
        // given
        var v = new Vector(1, 0, 0);

        // then
        assertThat(v.magnitude()).isEqualTo(1);
    }

    @Test
    public void should_calculate_magnitude_of_unit_vector_y() {
        // given
        var v = new Vector(0, 1, 0);

        // then
        assertThat(v.magnitude()).isEqualTo(1);
    }

    @Test
    public void should_calculate_magnitude_of_unit_vector_z() {
        // given
        var v = new Vector(0, 0, 1);

        // then
        assertThat(v.magnitude()).isEqualTo(1);
    }

    @Test
    public void should_calculate_magnitude_of_one_vector() {
        // given
        var v = new Vector(1, 2, 3);

        // then
        assertThat(v.magnitude()).isEqualTo(Math.sqrt(14), within(1e-6));
    }

    @Test
    public void should_calculate_magnitude_of_another_vector() {
        // given
        var v = new Vector(-1, -2, -3);

        // then
        assertThat(v.magnitude()).isEqualTo(Math.sqrt(14), within(1e-6));
    }

    @Test
    public void should_normalize_vector_x() {
        // given
        var v = new Vector(4, 0, 0);

        // when
        var u = v.normalize();

        // then
        TestUtils.validateVectorComponents(u, 1, 0, 0);
    }

    @Test
    public void should_calculate_dot_product() {
        // given
        var v1 = new Vector(1, 2, 3);
        var v2 = new Vector(2, 3, 4);

        // then
        assertThat(v1.dot(v2)).isEqualTo(20);
        assertThat(v2.dot(v1)).isEqualTo(20);
    }

    @Test
    public void should_calculate_cross_product() {
        // given
        var v1 = new Vector(1, 2, 3);
        var v2 = new Vector(2, 3, 4);

        // then
        TestUtils.validateVectorComponents(v1.cross(v2), -1, 2, -1);
        TestUtils.validateVectorComponents(v2.cross(v1), 1, -2, 1);
    }
}