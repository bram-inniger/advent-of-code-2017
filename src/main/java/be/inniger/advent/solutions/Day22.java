package be.inniger.advent.solutions;

import static be.inniger.advent.util.Direction.UP;
import static be.inniger.advent.util.Position.position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

import be.inniger.advent.DailyProblem;
import be.inniger.advent.util.Direction;
import be.inniger.advent.util.Position;

public class Day22 implements DailyProblem<List<String>, Integer> {

  @Override
  public Integer solveFirst(List<String> inputs) {
    return solve(inputs, 10_000, status -> {
      switch (status) {
        case CLEAN:
          return Status.INFECTED;
        case INFECTED:
          return Status.CLEAN;
        case WEAKENED:
        case FLAGGED:
        default:
          throw new IllegalArgumentException();
      }
    });
  }

  @Override
  public Integer solveSecond(List<String> inputs) {
    return solve(inputs, 10_000_000, status -> {
      switch (status) {
        case CLEAN:
          return Status.WEAKENED;
        case WEAKENED:
          return Status.INFECTED;
        case INFECTED:
          return Status.FLAGGED;
        case FLAGGED:
          return Status.CLEAN;
        default:
          throw new IllegalArgumentException();
      }
    });
  }

  public int solve(List<String> inputs, int nrIterations, UnaryOperator<Status> statusChanger) {
    Map<Position, Status> grid = getInitialState(inputs);
    int infectedCount = 0;

    Position virusPosition = position(0, 0);
    Direction virusDirection = UP;

    for (int i = 0; i < nrIterations; i++) {
      Status oldStatus = grid.getOrDefault(virusPosition, Status.CLEAN);
      Status newStatus = statusChanger.apply(oldStatus);
      grid.put(virusPosition, newStatus);

      if (newStatus == Status.INFECTED) {
        infectedCount++;
      }

      virusDirection = getNewDirection(virusDirection, oldStatus);
      virusPosition = virusDirection.calculateNext(virusPosition);
    }

    return infectedCount;
  }

  private Map<Position, Status> getInitialState(List<String> inputs) {
    Map<Position, Status> grid = new HashMap<>();
    int coordinatesOffset = inputs.size() / 2;

    for (int row = 0; row < inputs.size(); row++) {
      char[] input = inputs.get(row).toCharArray();

      for (int col = 0; col < input.length; col++) {
        Position node = position(row - coordinatesOffset, col - coordinatesOffset);

        switch (input[col]) {
          case '.':
            grid.put(node, Status.CLEAN);
            break;
          case '#':
            grid.put(node, Status.INFECTED);
            break;
          case 'W':
            grid.put(node, Status.WEAKENED);
            break;
          case 'F':
            grid.put(node, Status.FLAGGED);
            break;
        }
      }
    }

    return grid;
  }

  private Direction getNewDirection(Direction virusDirection, Status oldStatus) {
    switch (oldStatus) {
      case CLEAN:
        virusDirection = virusDirection.rotateCounterClockWise();
        break;
      case INFECTED:
        virusDirection = virusDirection.rotateClockWise();
        break;
      case WEAKENED:
        break;
      case FLAGGED:
        virusDirection = virusDirection.reverse();
        break;
      default:
        throw new IllegalArgumentException();
    }
    return virusDirection;
  }

  private enum Status {

    CLEAN,
    INFECTED,
    WEAKENED,
    FLAGGED
  }
}
