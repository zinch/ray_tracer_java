package foo.bar.core;

public record RayIntersections(RayIntersection first, RayIntersection second) {
    public boolean isEmpty() {
        return first == null || second == null;
    }
}
