package foo.bar;

import static org.assertj.core.api.Assertions.within;

import foo.bar.geom.Geometry;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class ProjectileTest {
    private record Env(double[] gravity, double[] wind) {
    }

    private record Projectile(double[] position, double[] velocity) {
        public Projectile tick(Env env) {
            var newPosition = Geometry.add(position, velocity);
            var newVelocity = Geometry.add(Geometry.add(velocity, env.gravity()), env.wind());
            return new Projectile(newPosition, newVelocity);
        }
    }

    @Test
    public void launch_projectile() {
        // given
        var env = new Env(Geometry.newVector(0, -0.1, 0), Geometry.newVector(-0.01, 0, 0));
        var projectile = new Projectile(Geometry.newPoint(0, 1, 0), Geometry.newVector(1, 1, 0));

        // when
        while (projectile.position()[1] > 0) {
            projectile = projectile.tick(env);
        }

        var x = projectile.position()[0];
        var y = projectile.position()[1];
        SoftAssertions.assertSoftly(it -> {
            it.assertThat(x).isEqualTo(19.7, within(0.1));
            it.assertThat(y).isEqualTo(0, within(0.1));
        });
    }
}
