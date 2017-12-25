package be.inniger.advent.util;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class Point {

  private final int x;
  private final int y;

  private Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public static Point point(int x, int y) {
    return new Point(x, y);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Point point = (Point) o;

    return new EqualsBuilder()
        .append(x, point.x)
        .append(y, point.y)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(x)
        .append(y)
        .toHashCode();
  }
}