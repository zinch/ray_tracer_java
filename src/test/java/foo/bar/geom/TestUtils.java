package foo.bar.geom;

import org.assertj.core.api.SoftAssertions;

public class TestUtils {
    private TestUtils() {
    }

    static void validatePointComponents(Point p, double expectedX, double expectedY, double expectedZ) {
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(p.x()).isEqualTo(expectedX);
            it.assertThat(p.y()).isEqualTo(expectedY);
            it.assertThat(p.z()).isEqualTo(expectedZ);
            it.assertThat(p.w()).isEqualTo(1.0);
        });
    }

    static void validateVectorComponents(Vector v, double expectedX, double expectedY, double expectedZ) {
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(v.x()).isEqualTo(expectedX);
            it.assertThat(v.y()).isEqualTo(expectedY);
            it.assertThat(v.z()).isEqualTo(expectedZ);
            it.assertThat(v.w()).isEqualTo(0.0);
        });
    }
}
