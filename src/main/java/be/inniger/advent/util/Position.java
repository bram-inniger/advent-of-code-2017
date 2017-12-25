package be.inniger.advent.util;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class Position {

  private final int row;
  private final int col;

  private Position(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public static Position position(int row, int col) {
    return new Position(row, col);
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Position position = (Position) o;

    return new EqualsBuilder()
        .append(row, position.row)
        .append(col, position.col)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(row)
        .append(col)
        .toHashCode();
  }
}
