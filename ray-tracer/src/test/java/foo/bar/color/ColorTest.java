package foo.bar.color;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class ColorTest {
    @Test
    public void should_create_color() {
        // given
        var c = new Color(-0.5, 0.4, 1.7);

        // then
        validateColorComponents(c, -0.5, 0.4, 1.7);
    }

    public static void validateColorComponents(
            Color c, double expectedRed, double expectedGreen, double expectedBlue)
    {
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(c.red()).isEqualTo(expectedRed);
            it.assertThat(c.green()).isEqualTo(expectedGreen);
            it.assertThat(c.blue()).isEqualTo(expectedBlue);
        });
    }
}