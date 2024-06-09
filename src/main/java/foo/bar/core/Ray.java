package foo.bar.core;

import foo.bar.geom.Point;
import foo.bar.geom.Sphere;
import foo.bar.geom.Vector;
import foo.bar.util.Pair;

public record Ray(Point origin, Vector direction) {
    public Point positionAt(double t) {
        return origin.add(direction.multiply(t));
    }

    public Pair<RayIntersection, RayIntersection> intersect(Sphere s) {
        var sphereToRay = origin.subtract(Point.ORIGIN);
        var a = direction.dot(direction);
        var b = 2 * direction.dot(sphereToRay);
        var c = sphereToRay.dot(sphereToRay) - 1;

        var discriminant = b*b - 4*a*c;

        if (discriminant < 0) {
            return new Pair<>(null, null);
        }

        var t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
        var t2 = (-b + Math.sqrt(discriminant)) / (2 * a);

        return new Pair<>(new RayIntersection(t1, s), new RayIntersection(t2, s));
    }
}
