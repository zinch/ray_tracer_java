package foo.bar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import foo.bar.color.Color;

public class CanvasTest {
    @Test
    public void should_create_canvas() {
        // given
        var c = new Canvas(10, 20);

        // then
        assertThat(c.width()).isEqualTo(10);
        assertThat(c.height()).isEqualTo(20);
        for (int y = 0; y < c.height(); y++) {
            for (int x = 0; x < c.width(); x++) {
                var pixel = c.colorAt(x, y);
                assertThat(pixel).isEqualTo(Color.BLACK);
            }
        }
    }

    @Test
    public void should_write_pixel_to_canvas() {
        // given
        var c = new Canvas(10, 20);
        var red = new Color(1, 0, 0);

        // when
        c.writePixel(red, 2, 3);

        // then
        var pixel = c.colorAt(2, 3);
        assertThat(pixel).isEqualTo(new Color(1.0, 0, 0));
    }
}
