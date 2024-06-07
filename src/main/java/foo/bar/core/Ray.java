package foo.bar.core;

import foo.bar.geom.Point;
import foo.bar.geom.Vector;

public record Ray(Point origin, Vector direction) {
}
