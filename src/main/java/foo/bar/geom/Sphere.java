package foo.bar.geom;

import foo.bar.math.Matrix;
import foo.bar.util.Util;

import java.util.Objects;

public class Sphere {
    private final String id;
    private Matrix transformation;

    public Sphere() {
        this.id = Util.makeRandomId(10);
        this.transformation = Matrix.IDENTITY;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Sphere{" + "id='" + id + '\'' + '}';
    }

    public Matrix getTransformation() {
        return transformation;
    }

    public void setTransformation(Matrix transformation) {
        this.transformation = Objects.requireNonNull(transformation);
    }
}
