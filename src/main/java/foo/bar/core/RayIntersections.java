package foo.bar.core;

public record RayIntersections<K, V>(K first, V second) {
    @SuppressWarnings("rawtypes")
    private static final RayIntersections EMPTY = new RayIntersections<>(null, null);

    @SuppressWarnings("unchecked")
    public static <K, V> RayIntersections<K, V> empty() {
        return (RayIntersections<K, V>) EMPTY;
    }

    public boolean isEmpty() {
        return first == null && second == null;
    }
}
