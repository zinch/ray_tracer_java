package foo.bar.geom;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class PointTest {
    @Test
    public void should_create_point() {
        // when
        var point = new Point(4.3, -4.2, 3.1);

        // then
        validatePointComponents(point, 4.3, -4.2, 3.1);
    }

    private static void validatePointComponents(Point p, double expectedX, double expectedY, double expectedZ) {
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(p.x()).isEqualTo(expectedX);
            it.assertThat(p.y()).isEqualTo(expectedY);
            it.assertThat(p.z()).isEqualTo(expectedZ);
        });
    }
}