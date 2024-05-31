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

    @Test
    public void should_construct_ppm_header() {
        // given
        var c = new Canvas(5, 3);

        // when
        var ppm = c.toPpm();

        // then
        assertThat(ppm).startsWith("""
            P3
            5 3
            255""");
    }

    @Test
    public void should_construct_ppm_with_pixels() {
        // given
        var c = new Canvas(5, 3);
        c.writePixel(new Color(1.5, 0, 0), 0, 0);
        c.writePixel(new Color(0, 0.5, 0), 2, 1);
        c.writePixel(new Color(-0.5, 0, 1), 4, 2);

        // when
        var ppm = c.toPpm();

        // then
        assertThat(ppm).endsWith("""
            255 0 0 0 0 0 0 0 0 0 0 0 0 0 0
            0 0 0 0 0 0 0 128 0 0 0 0 0 0 0
            0 0 0 0 0 0 0 0 0 0 0 0 0 0 255
            """);
    }

    @Test
    public void should_split_long_lines_in_PPM() {
        // given
        var c = new Canvas(10, 2);

        var color = new Color(1, 0.8, 0.6);
        for (int y = 0; y < c.height(); y++) {
            for (int x = 0; x < c.width(); x++) {
                c.writePixel(color, x, y);
            }
        }

        // when
        var ppm = c.toPpm();

        // then
        assertThat(ppm).endsWith("""
            255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204
            153 255 204 153 255 204 153 255 204 153 255 204 153
            255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204
            153 255 204 153 255 204 153 255 204 153 255 204 153
            """);
    }
}
