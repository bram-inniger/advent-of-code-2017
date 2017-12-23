package be.inniger.advent.solutions;

import static be.inniger.advent.util.Point.point;

import java.util.HashSet;
import java.util.Set;

import be.inniger.advent.DailyProblem;
import be.inniger.advent.util.KnotHash;
import be.inniger.advent.util.Point;
import be.inniger.advent.util.UnionFind;

public class Day14 implements DailyProblem<String, Integer> {

  private static final int GRID_DIMENSION = 128;

  @Override
  public Integer solveFirst(String input) {
    return calculateGrid(input).size();
  }

  @Override
  public Integer solveSecond(String input) {
    Set<Point> grid = calculateGrid(input);
    UnionFind uf = new UnionFind(GRID_DIMENSION * GRID_DIMENSION);

    grid.forEach(point -> {
      unionIfContains(grid, uf, point, point(point.getX() - 1, point.getY()));
      unionIfContains(grid, uf, point, point(point.getX(), point.getY() - 1));
    });

    return (int) grid.stream()
        .map(this::toUfIndex)
        .map(uf::find)
        .distinct()
        .count();
  }

  private Set<Point> calculateGrid(String input) {
    Set<Point> grid = new HashSet<>();

    for (int i = 0; i < GRID_DIMENSION; i++) {
      String knotHash = KnotHash.hash(input + "-" + i);

      for (int j = 0; j < GRID_DIMENSION / 4; j++) {
        int value = Character.getNumericValue(knotHash.charAt(j));

        // Check bit per bit of the single character (0 -> f) whether it is set or not
        for (int k = 0; k < 4; k++) {
          if ((value >> (3 - k) & 1) != 0) {
            grid.add(point(i, 4 * j + k));
          }
        }
      }
    }

    return grid;
  }

  private void unionIfContains(Set<Point> grid, UnionFind uf, Point point, Point other) {
    if (grid.contains(other)) {
      uf.union(toUfIndex(point), toUfIndex(other));
    }
  }

  private int toUfIndex(Point point) {
    return point.getX() + point.getY() * GRID_DIMENSION;
  }
}
