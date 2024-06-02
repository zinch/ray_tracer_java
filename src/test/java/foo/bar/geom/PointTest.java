package foo.bar.geom;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class PointTest {
    @Test
    public void should_create_point() {
        // when
        var point = new Point(4.3, -4.2, 3.1);

        // then
        TestUtils.validatePointComponents(point, 4.3, -4.2, 3.1);
    }
}