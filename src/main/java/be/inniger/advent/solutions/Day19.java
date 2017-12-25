package be.inniger.advent.solutions;

import static be.inniger.advent.util.Position.position;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import be.inniger.advent.DailyProblem;
import be.inniger.advent.util.Position;

public class Day19 implements DailyProblem<List<String>, String> {

  @Override
  public String solveFirst(List<String> inputs) {
    char[][] grid = generateGrid(inputs);
    Position position = getStartingPoint(grid[0]);
    Direction direction = Direction.DOWN;
    boolean done = false;
    StringBuilder sb = new StringBuilder();

    while (!done) {
      position = direction.calculateNext(position);
      char c = grid[position.getRow()][position.getCol()];

      switch (c) {
        case '|':
        case '-':
          // Do nothing, keep going
          break;
        case '+':
          direction = findNewDirection(grid, position, direction);
          break;
        default:
          if (Character.isLetter(c)) {
            // Found a letter
            sb.append(c);
          }
          else if (Character.isWhitespace(c)) {
            // Found the end of the path
            done = true;
          }
          else {
            throw new IllegalArgumentException("Caught invalid character");
          }
      }
    }

    return sb.toString();
  }

  @Override
  public String solveSecond(List<String> inputs) {
    throw new UnsupportedOperationException();
  }

  private char[][] generateGrid(List<String> inputs) {
    return inputs.stream()
        .map(String::toCharArray)
        .toArray(char[][]::new);
  }

  private Position getStartingPoint(char[] firstRow) {
    return IntStream.range(0, firstRow.length)
        .filter(i -> firstRow[i] == '|')
        .mapToObj(i -> position(0, i))
        .findAny()
        .orElseThrow(IllegalArgumentException::new);
  }

  private Direction findNewDirection(char[][] grid, Position position, Direction direction) {
    if (direction == Direction.UP || direction == Direction.DOWN) {
      if (position.getCol() - 1 >= 0 &&
          !Character.isWhitespace(grid[position.getRow()][position.getCol() - 1])) {
        return Direction.LEFT;
      }

      return Direction.RIGHT;
    }
    else {
      if (position.getRow() - 1 >= 0 &&
          !Character.isWhitespace(grid[position.getRow() - 1][position.getCol()])) {
        return Direction.UP;
      }

      return Direction.DOWN;
    }
  }

  private enum Direction {
    UP(p -> position(p.getRow() - 1, p.getCol())),
    RIGHT(p -> position(p.getRow(), p.getCol() + 1)),
    DOWN(p -> position(p.getRow() + 1, p.getCol())),
    LEFT(p -> position(p.getRow(), p.getCol() - 1));

    private final Function<Position, Position> nextCalculator;

    Direction(Function<Position, Position> nextCalculator) {
      this.nextCalculator = nextCalculator;
    }

    private Position calculateNext(Position position) {
      return nextCalculator.apply(position);
    }
  }
}
