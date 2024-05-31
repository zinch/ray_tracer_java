package foo.bar;

import foo.bar.color.Color;

import java.util.Arrays;
import java.util.Objects;

public final class Canvas {
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
}
