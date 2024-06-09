package foo.bar.core;

import foo.bar.geom.Point;
import foo.bar.geom.Sphere;
import foo.bar.geom.Vector;
import foo.bar.math.Matrix;

public record Ray(Point origin, Vector direction) {
    private static final RayIntersection[] EMPTY_INTERSECTION = new RayIntersection[0];
    public Point positionAt(double t) {
        return origin.add(direction.multiply(t));
    }

    public RayIntersection[] intersect(Sphere s) {
        Matrix worldToObject = s.getTransformation().inverse();
        var newOrigin = worldToObject.multiply(origin);
        var newDirection = worldToObject.multiply(direction);

        var sphereToRay = newOrigin.subtract(Point.ORIGIN);
        var a = newDirection.dot(newDirection);
        var b = 2 * newDirection.dot(sphereToRay);
        var c = sphereToRay.dot(sphereToRay) - 1;

        var discriminant = b*b - 4*a*c;

        if (discriminant < 0) {
            return EMPTY_INTERSECTION;
        }

        var t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
        var t2 = (-b + Math.sqrt(discriminant)) / (2 * a);

        return new RayIntersection[] {new RayIntersection(t1, s), new RayIntersection(t2, s)};
    }
}
