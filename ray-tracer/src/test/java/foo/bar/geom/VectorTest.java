package foo.bar.geom;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class VectorTest {
    @Test
    public void should_create_vector() {
        // when
        var vector = new Vector(4.3, -4.2, 3.1);

        // then
        validateVectorComponents(vector, 4.3, -4.2, 3.1);
    }

    private static void validateVectorComponents(Vector v, double expectedX, double expectedY, double expectedZ) {
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(v.x()).isEqualTo(expectedX);
            it.assertThat(v.y()).isEqualTo(expectedY);
            it.assertThat(v.z()).isEqualTo(expectedZ);
            it.assertThat(v.w()).isEqualTo(0.0);
        });
    }
}