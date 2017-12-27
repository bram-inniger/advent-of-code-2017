package be.inniger.advent.solutions;

import static be.inniger.advent.util.Position.position;

import java.util.List;
import java.util.stream.IntStream;

import be.inniger.advent.DailyProblem;
import be.inniger.advent.util.Direction;
import be.inniger.advent.util.Position;

public class Day19 implements DailyProblem<List<String>, String> {

  @Override
  public String solveFirst(List<String> inputs) {
    return solve(inputs).lettersFound;
  }

  @Override
  public String solveSecond(List<String> inputs) {
    return String.format("%d", solve(inputs).stepCount);
  }

  private Solution solve(List<String> inputs) {
    char[][] grid = generateGrid(inputs);
    Position position = getStartingPoint(grid[0]);
    Direction direction = Direction.DOWN;
    boolean done = false;
    StringBuilder sb = new StringBuilder();
    int stepCount = 0;

    while (!done) {
      stepCount++;
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

    return new Solution(sb.toString(), stepCount);
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

  private static class Solution {

    private final String lettersFound;
    private final int stepCount;

    private Solution(String lettersFound, int stepCount) {
      this.lettersFound = lettersFound;
      this.stepCount = stepCount;
    }
  }


}
