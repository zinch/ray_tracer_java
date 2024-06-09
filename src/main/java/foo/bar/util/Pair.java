package foo.bar.util;

public record Pair<K, V>(K fst, V snd) {
    public boolean isEmpty() {
        return fst == null || snd == null;
    }
}
