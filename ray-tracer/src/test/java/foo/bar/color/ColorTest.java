package foo.bar.color;

import static org.assertj.core.api.Assertions.within;

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

    @Test
    public void should_add_colors() {
        // given
        var c1 = new Color(0.9, 0.6, 0.75);
        var c2 = new Color(0.7, 0.1, 0.25);

        // when
        var c = c1.add(c2);

        // then
        validateColorComponents(c, 1.6, 0.7, 1.0);
    }

    @Test
    public void should_subtract_colors() {
        // given
        var c1 = new Color(0.9, 0.6, 0.75);
        var c2 = new Color(0.7, 0.1, 0.25);

        // when
        var c = c1.subtract(c2);

        // then
        validateColorComponents(c, 0.2, 0.5, 0.5);
    }

    @Test
    public void should_multiply_color_by_scalar() {
        // given
        var c1 = new Color(0.2, 0.3, 0.4);

        // when
        var c2 = c1.multiply(2);

        // then
        validateColorComponents(c2, 0.4, 0.6, 0.8);
    }

    public static void validateColorComponents(
            Color c, double expectedRed, double expectedGreen, double expectedBlue)
    {
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(c.red()).isEqualTo(expectedRed, within(1e-5));
            it.assertThat(c.green()).isEqualTo(expectedGreen, within(1e-5));
            it.assertThat(c.blue()).isEqualTo(expectedBlue, within(1e-5));
        });
    }
}