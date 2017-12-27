package be.inniger.advent.solutions;

import static be.inniger.advent.util.Direction.UP;
import static be.inniger.advent.util.Position.position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import be.inniger.advent.DailyProblem;
import be.inniger.advent.util.Direction;
import be.inniger.advent.util.Position;

public class Day22 implements DailyProblem<List<String>, Integer> {

  @Override
  public Integer solveFirst(List<String> inputs) {
    Set<Position> infected = getInitialState(inputs);
    int infectedCount = 0;

    Position virusPosition = position(0, 0);
    Direction virusDirection = UP;

    for (int i = 0; i < 10_000; i++) {
      if (infected.contains(virusPosition)) {
        virusDirection = virusDirection.rotateClockWise();
        infected.remove(virusPosition);
      }
      else {
        virusDirection = virusDirection.rotateCounterClockWise();
        infected.add(virusPosition);
        infectedCount++;
      }

      virusPosition = virusDirection.calculateNext(virusPosition);
    }

    return infectedCount;
  }

  @Override
  public Integer solveSecond(List<String> input) {
    throw new UnsupportedOperationException();
  }

  private Set<Position> getInitialState(List<String> inputs) {
    Set<Position> infected = new HashSet<>();
    int coordinatesOffset = inputs.size() / 2;

    for (int row = 0; row < inputs.size(); row++) {
      char[] input = inputs.get(row).toCharArray();

      for (int col = 0; col < input.length; col++) {
        if (input[col] == '#') {
          infected.add(position(row - coordinatesOffset, col - coordinatesOffset));
        }
      }
    }
    return infected;
  }

  // TODO - Remove after finishing part 2
  private void prettyPrint(Set<Position> infected, Position virusPosition, Direction virusDirection) {
    StringBuilder sb = new StringBuilder();

    for (int row = -4; row <= 4; row++) {
      for (int col = -4; col <= 4; col++) {
        sb
            .append(virusPosition.getRow() == row && virusPosition.getCol() == col ?
                '[' :
                ' '
            )
            .append(infected.contains(position(row, col)) ?
                '#' :
                '.'
            )
            .append(virusPosition.getRow() == row && virusPosition.getCol() == col ?
                ']' :
                ' '
            );
      }

      sb.append("\n");
    }

    System.out.println(sb);
    System.out.println(virusDirection);
  }
}
