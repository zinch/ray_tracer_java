package foo.bar;

import java.security.SecureRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Util {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final Character[] CHARS;

    static {
        Stream<Character> numberStream = IntStream.range(49, 58)
                .mapToObj(i -> (char) i);

        Stream<Character> letterStream = IntStream.range(97, 123)
                .mapToObj(i -> (char) i);

        CHARS = Stream.concat(numberStream, letterStream).toArray(Character[]::new);
    }

    private Util() {
    }

    public static String makeRandomId(int len) {
        char[] result = new char[len];
        for (int i = 0; i < len; i++) {
            result[i] = CHARS[RANDOM.nextInt(CHARS.length)];
        }
        return new String(result);
    }
}
