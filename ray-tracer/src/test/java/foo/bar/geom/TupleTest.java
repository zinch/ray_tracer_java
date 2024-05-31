package foo.bar.geom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class TupleTest {
    @Test
    public void should_add_tuples() {
        // given
        var t1 = new Tuple(4.3, -4.2, 3.1, 0.0);
        var t2 = new Tuple(1.7, -1.8, 0.9, 1.0);

        // when
        var t = t1.add(t2);

        // then
        validateTupleComponents(t, 6.0, -6.0, 4.0, 1.0);
    }

    @Test
    public void should_subtract_tuples() {
        // given
        var t1 = new Tuple(4.3, -4.2, 3.1, 1.0);
        var t2 = new Tuple(1.7, -1.8, 0.9, 1.0);

        // when
        var t = t1.subtract(t2);

        // then
        validateTupleComponents(t, 2.6, -2.4, 2.2, 0.0);
    }

    @Test
    public void should_compare_two_tuples() {
        // given
        var epsilon = 1e-7;
        var t1 = new Tuple(1.2, 3.4, 5.6, 1.1);
        var t2 = new Tuple(1.2 + epsilon, 3.4 - epsilon, 5.6 + epsilon, 1.1 - epsilon);

        // then
        assertThat(t1).isEqualTo(t2);
    }

    static void validateTupleComponents(
            Tuple t, double expectedX, double expectedY, double expectedZ, double expectedW) {
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(t.x()).isEqualTo(expectedX, within(1e-5));
            it.assertThat(t.y()).isEqualTo(expectedY, within(1e-5));
            it.assertThat(t.z()).isEqualTo(expectedZ, within(1e-5));
            it.assertThat(t.w()).isEqualTo(expectedW, within(1e-5));
        });
    }
}