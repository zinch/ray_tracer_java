package foo.bar.geom;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class TupleTest {
    @Test
    public void should_create_tuple() {
        var t = new Tuple(4.3, -4.2, 3.1, 0.0);
    }

    @Test
    public void should_compare_two_points() {
        // given
        var epsilon = 1e-7;
        var t1 = new Tuple(1.2, 3.4, 5.6, 1.1);
        var t2 = new Tuple(1.2 + epsilon, 3.4 - epsilon, 5.6 + epsilon, 1.1 - epsilon);

        // then
        assertThat(t1).isEqualTo(t2);
    }
}