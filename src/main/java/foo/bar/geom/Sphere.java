package foo.bar.geom;

import foo.bar.Util;

public class Sphere {
    private final String id;

    public Sphere() {
        this.id = Util.makeRandomId(10);
    }

    public String getId() {
        return id;
    }
}
