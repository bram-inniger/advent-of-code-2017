package be.inniger.advent.util;

import static be.inniger.advent.util.Position.position;

import java.util.function.Function;

public enum Direction {

  UP(p -> position(p.getRow() - 1, p.getCol())),
  RIGHT(p -> position(p.getRow(), p.getCol() + 1)),
  DOWN(p -> position(p.getRow() + 1, p.getCol())),
  LEFT(p -> position(p.getRow(), p.getCol() - 1));

  private final Function<Position, Position> nextCalculator;

  Direction(Function<Position, Position> nextCalculator) {
    this.nextCalculator = nextCalculator;
  }

  public Position calculateNext(Position position) {
    return nextCalculator.apply(position);
  }

  public Direction rotateClockWise() {
    switch (this) {
      case UP:
        return RIGHT;
      case RIGHT:
        return DOWN;
      case DOWN:
        return LEFT;
      case LEFT:
        return UP;
      default:
        throw new IllegalArgumentException();
    }
  }

  public Direction rotateCounterClockWise() {
    switch (this) {
      case UP:
        return LEFT;
      case LEFT:
        return DOWN;
      case DOWN:
        return RIGHT;
      case RIGHT:
        return UP;
      default:
        throw new IllegalArgumentException();
    }
  }

  public Direction reverse() {
    switch (this) {
      case UP:
        return DOWN;
      case RIGHT:
        return LEFT;
      case DOWN:
        return UP;
      case LEFT:
        return RIGHT;
      default:
        throw new IllegalArgumentException();
    }
  }
}
