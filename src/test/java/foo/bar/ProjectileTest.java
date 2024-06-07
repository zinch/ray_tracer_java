package foo.bar;

import static org.assertj.core.api.Assertions.within;

import foo.bar.color.Color;
import foo.bar.geom.Point;
import foo.bar.geom.Vector;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProjectileTest {
    private static final int CANVAS_WIDTH = 900;
    private static final int CANVAS_HEIGHT = 550;
    private Env env;

    private record Env(Vector gravity, Vector wind) {
    }

    private record Projectile(Point position, Vector velocity) {
        public Projectile tick(Env env) {
            var newPosition = position.add(velocity);
            var newVelocity = velocity.add(env.gravity()).add(env.wind());
            return new Projectile(newPosition, newVelocity);
        }
    }

    private final Color color = new Color(231 / 255.0, 76 / 255.0, 60 / 255.0);
    private Canvas canvas;

    @Before
    public void setUp() {
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);

        var gravity = new Vector(0, -0.1, 0);
        var wind = new Vector(-0.01, 0, 0);
        env = new Env(gravity, wind);
    }

    @Test
    public void launch_projectile() throws IOException {
        // given
        var position = new Point(0, 1, 0);
        var velocity = new Vector(1, 1.8, 0).normalize().multiply(11.25);
        var projectile = new Projectile(position, velocity);

        drawPixel(projectile);

        // when
        while (projectile.position().y() > 0) {
            projectile = projectile.tick(env);
            drawPixel(projectile);

        }

        Files.writeString(Paths.get("projectile.ppm"), canvas.toPpm());

        var x = projectile.position().x();
        var y = projectile.position().y();

        SoftAssertions.assertSoftly(it -> {
            it.assertThat(x).isEqualTo(886.7, within(0.1));
            it.assertThat(y).isEqualTo(-2.1, within(0.1));
        });
    }

    private void drawPixel(Projectile projectile) {
        int canvasX = (int) projectile.position.x();
        var canvasY = (int) (CANVAS_HEIGHT - projectile.position.y() - 1);
        try {
            canvas.writePixel(color, canvasX, canvasY);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
