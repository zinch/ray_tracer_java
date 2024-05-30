package foo.bar.geom;


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
}