package foo.bar;

import static org.assertj.core.api.Assertions.within;

import foo.bar.color.Color;
import foo.bar.geom.Point;
import foo.bar.geom.Vector;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProjectileTest {
    private record Env(Vector gravity, Vector wind) {
    }

    private record Projectile(Point position, Vector velocity) {
        public Projectile tick(Env env) {
            var newPosition = position.add(velocity);
            var newVelocity = velocity.add(env.gravity()).add(env.wind());
            return new Projectile(newPosition, newVelocity);
        }
    }

    @Test
    public void launch_projectile() throws IOException {
        // given
        var env = new Env(new Vector(0, -0.1, 0), new Vector(-0.01, 0, 0));
        var projectile = new Projectile(new Point(0, 1, 0), new Vector(1, 1, 0));
        int height = 50;
        var canvas = new Canvas(50, height);
        var color = new Color(231/255.0, 76/255.0, 60/255.0);

        int canvasX = (int) projectile.position.x();
        var canvasY = (int) (height - projectile.position.y());
        canvas.writePixel(color, canvasX, canvasY);

        // when
        while (projectile.position().y()> 0) {
            projectile = projectile.tick(env);
        }

        var x = projectile.position().x();
        var y = projectile.position().y();
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(x).isEqualTo(19.7, within(0.1));
            it.assertThat(y).isEqualTo(0, within(0.1));
        });

        Files.writeString(Paths.get("projectile.ppm"), canvas.toPpm());
    }
}
