package foo.bar;

import foo.bar.color.Color;
import foo.bar.core.Ray;
import foo.bar.geom.Point;
import foo.bar.geom.Sphere;
import foo.bar.math.Matrix;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SphereSilhouetteTest {
    private static final int CANVAS_SIZE = 100;
    private final Color color = new Color(0.8549019607843137, 0.4392156862745098, 0.8392156862745098);
    private Canvas canvas;

    @Before
    public void setUp() {
        canvas = new Canvas(CANVAS_SIZE, CANVAS_SIZE);
    }

    @Test
    public void should_project_sphere_onto_canvas() throws IOException {
        Point rayOrigin = new Point(0, 0, -5);
        var s = new Sphere();
        s.setTransformation(Matrix
                .newScaling(1, 0.5, 1)
                .rotateZ(Math.PI / 4)
                .shear(0, 0, 0, 1, 0, 0)
                .rotateY(-Math.PI / 6));

        double canvasDistance = 10;
        double wallSize = 10;

        var pixelSize = wallSize / CANVAS_SIZE;
        var half = wallSize / 2;

        for (int y = 0; y < CANVAS_SIZE; y++) {
            var worldY = half - pixelSize * y;
            for (int x = 0; x < CANVAS_SIZE; x++) {
                var worldX = -half + pixelSize * x;
                var pointOnCanvas = new Point(worldX, worldY, canvasDistance);
                var rayDirection = pointOnCanvas.subtract(rayOrigin).normalize();
                var ray = new Ray(rayOrigin, rayDirection);
                var intersections = ray.intersect(s);
                if (intersections.length > 0) {
                    canvas.writePixel(color, x, y);
                }
            }
        }

        Files.writeString(Paths.get("sphere_silhouette.ppm"), canvas.toPpm());
    }
}
