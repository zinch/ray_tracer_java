package foo.bar;

import foo.bar.color.Color;

import java.util.Arrays;
import java.util.Objects;

public final class Canvas {
    private static final String PPM_HEADER_TEMPLATE = """
            P3
            %d %d
            255
            """;
    private static final int MAX_LINE_LENGTH = 70;
    private final int width;
    private final int height;
    private final Color[] canvas;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.canvas = new Color[width * height];
        Arrays.fill(canvas, Color.BLACK);
    }

    public Color colorAt(int x, int y) {
        return canvas[idx(x, y)];
    }

    private int idx(int x, int y) {
        return (y * width) + x;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (Canvas) obj;
        return this.width == that.width &&
                this.height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    @Override
    public String toString() {
        return "Canvas[" +
                "width=" + width + ", " +
                "height=" + height + ']';
    }

    public void writePixel(Color color, int x, int y) {
        this.canvas[idx(x, y)] = color;
    }

    public String toPpm() {
        var sb = new StringBuilder(PPM_HEADER_TEMPLATE.formatted(width, height));

        for (int y = 0; y < height; y++) {
            var line = new StringBuilder();

            for (int x = 0; x < width; x++) {
                var c = colorAt(x, y);
                line = appendOrStartNewLine(normalize(c.red()), line, sb);
                line = appendOrStartNewLine(normalize(c.green()), line, sb);
                line = appendOrStartNewLine(normalize(c.blue()), line, sb);
            }

            sb.append(line).append('\n');
        }

        return sb.toString();
    }

    private int normalize(double value) {
        return (int) Math.max(0, Math.min(255, Math.round(value * 255)));
    }

    private static StringBuilder appendOrStartNewLine(int value, StringBuilder line, StringBuilder sb) {
        if (line.length() + 4 > MAX_LINE_LENGTH) {
            sb.append(line).append('\n');
            line = new StringBuilder();
        }

        if (!line.isEmpty()) {
            line.append(' ');
        }
        line.append(value);

        return line;
    }
}
