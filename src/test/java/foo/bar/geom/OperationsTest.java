package foo.bar.geom;

import org.junit.Test;

public class OperationsTest {
    @Test
    public void should_create_new_point_when_adding_vector_to_a_point() {
        // given
        var p = new Point(3, -2, 5);
        var v = new Vector(-2, 3, 1);

        // when
        var p1 = p.add(v);

        // then
        TestUtils.validatePointComponents(p1, 1, 1, 6);
    }

    @Test
    public void should_produce_vector_when_subtracting_two_points() {
        // given
        var p1 = new Point(3, 2, 1);
        var p2 = new Point(5, 6, 7);

        // when
        var v = p1.subtract(p2);

        // then
        TestUtils.validateVectorComponents(v, -2, -4, -6);
    }

    @Test
    public void should_produce_point_when_subtracting_vector_from_a_point() {
        // given
        var p1 = new Point(3, 2, 1);
        var v = new Vector(5, 6, 7);

        // when
        var p2 = p1.subtract(v);

        // then
        TestUtils.validatePointComponents(p2, -2, -4, -6);
    }
}
