package foo.bar.geom;

import foo.bar.util.Util;

public class Sphere {
    private final String id;

    public Sphere() {
        this.id = Util.makeRandomId(10);
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Sphere{" + "id='" + id + '\'' + '}';
    }
}
