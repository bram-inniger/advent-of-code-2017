package be.inniger.advent.util;

import static java.lang.Math.abs;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class Point3D {

  private static final Point3D ORIGIN = point3D(0, 0, 0);

  private final long x;
  private final long y;
  private final long z;

  private Point3D(long x, long y, long z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public static Point3D point3D(long x, long y, long z) {
    return new Point3D(x, y, z);
  }

  public long getX() {
    return x;
  }

  public long getY() {
    return y;
  }

  public long getZ() {
    return z;
  }

  public long manhattanDistanceToOrigin() {
    return manhattanDistanceTo(ORIGIN);
  }

  private long manhattanDistanceTo(Point3D other) {
    return abs(this.x - other.x) + abs(this.y - other.y) + abs(this.z - other.z);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Point3D point3D = (Point3D) o;

    return new EqualsBuilder()
        .append(x, point3D.x)
        .append(y, point3D.y)
        .append(z, point3D.z)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(x)
        .append(y)
        .append(z)
        .toHashCode();
  }

  @Override
  public String toString() {
    return "Point3D{" +
        "x=" + x +
        ", y=" + y +
        ", z=" + z +
        '}';
  }
}
