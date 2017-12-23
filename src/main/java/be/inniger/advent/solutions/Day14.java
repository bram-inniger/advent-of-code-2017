package be.inniger.advent.solutions;

import static be.inniger.advent.util.Point.point;

import java.util.HashSet;
import java.util.Set;

import be.inniger.advent.DailyProblem;
import be.inniger.advent.util.KnotHash;
import be.inniger.advent.util.Point;

public class Day14 implements DailyProblem<String, Integer> {

  @Override
  public Integer solveFirst(String input) {
    Set<Point> grid = new HashSet<>();

    for (int i = 0; i < 128; i++) {
      String knotHash = KnotHash.hash(input + "-" + i);

      for (int j = 0; j < 32; j++) {
        int value = Character.getNumericValue(knotHash.charAt(j));

        // Check bit per bit of the single character (0 -> f) whether it is set or not
        for (int k = 0; k < 4; k++) {
          if ((value >> (3 - k) & 1) != 0) {
            grid.add(point(i, 4 * j + k));
          }
        }
      }
    }

    printGrid(grid, 8, 8);

    return grid.size();
  }

  @Override
  public Integer solveSecond(String input) {
    throw new UnsupportedOperationException();
  }

  // TODO remove method
  private void printGrid(Set<Point> grid, int xLim, int yLim) {
    for (int i = 0; i < xLim; i++) {

      for (int j = 0; j < yLim; j++) {
        System.out.print(grid.contains(point(i, j)) ? '#' : '.');
      }

      System.out.println();
    }
  }
}
