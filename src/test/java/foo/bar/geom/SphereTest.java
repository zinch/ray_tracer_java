package foo.bar.geom;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class SphereTest {
    @Test
    public void should_assign_id_to_a_new_sphere() {
        // given
        var s = new Sphere();

        // then
        assertThat(s.getId()).hasSize(10);
    }
}