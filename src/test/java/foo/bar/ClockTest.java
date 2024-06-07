package foo.bar;

import foo.bar.color.Color;
import foo.bar.geom.Point;
import foo.bar.math.Matrix;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ClockTest {
    private static final int CANVAS_SIZE = 400;
    private final Color color = new Color(1, 191 / 255.0, 0);
    private Canvas canvas;

    @Before
    public void setUp() {
        canvas = new Canvas(CANVAS_SIZE, CANVAS_SIZE);
    }

    @Test
    public void should_draw_clock_face() throws IOException {
        var twelve = new Point(0, CANVAS_SIZE / 5.0, 0);
        drawPixel(twelve);

        for (int i = 0; i < 12; i++) {
            var rad = i * Math.PI / 6;
            var transform = Matrix.newRotationZ(-rad);
            var p = transform.multiply(twelve);
            drawPixel(p);
        }

        Files.writeString(Paths.get("clock.ppm"), canvas.toPpm());
    }

    private void drawPixel(Point p) {
        int canvasX = (int) p.x() + CANVAS_SIZE / 2;
        var canvasY = (int) (CANVAS_SIZE / 2 - p.y() - 1);
        try {
            canvas.writePixel(color, canvasX, canvasY);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
