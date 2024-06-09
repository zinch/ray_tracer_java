package foo.bar.core;

public record RayIntersection(double t, Object object) {
    public static RayIntersection findHit(RayIntersection... intersections) {
        RayIntersection hit = null;
        for (RayIntersection intersection : intersections) {
            if (intersection.t < 0) {
                continue;
            }
            if (hit == null) {
                hit = intersection;
            } else if (intersection.t < hit.t) {
                hit = intersection;
            }
        }
        return hit;
    }
}
