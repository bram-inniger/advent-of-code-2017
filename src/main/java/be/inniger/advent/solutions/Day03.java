package be.inniger.advent.solutions;

import static be.inniger.advent.solutions.Day03.Point.point;
import static java.lang.Math.abs;
import static java.lang.Math.floor;
import static java.lang.Math.sqrt;
import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import be.inniger.advent.DailyProblem;

@SuppressWarnings("PointlessArithmeticExpression")
public class Day03 implements DailyProblem<Integer, Integer> {

  private final List<Point> neighbours = asList(
      point(1, 0),
      point(1, 1),
      point(0, 1),
      point(-1, 1),
      point(-1, 0),
      point(-1, -1),
      point(0, -1),
      point(1, -1));

  /*
   * The easiest to spot recurring theme on the spiral is that the bottom-right diagonal contains all the odd-numbered squares.
   * The strategy to translate a spiral-position into X and Y coordinates is to start counting from the previous odd root (bottom-right).
   * All numbers between this odd-root and the next lie on a nice square.
   * From here on simply detect which of the 4 sides of the newly formed square the position is in, this fixes either the X or Y coordinate.
   * Finally calculate the other coordinate by counting where on the side you are:
   * pick for example position 25, it is at (2, -2). It is quickly clear the starting number (2) is calculated using ((root-1) / 2),
   * remark now that every value following 25 is on a square that is distance 1 further, so do starting+1 and you get the base number to start X and Y with.
   */
  @Override
  public Integer solveFirst(Integer input) {
    Point coordinates = getCoordinates(input);
    return abs(coordinates.x) + abs(coordinates.y);
  }

  /*
   * Simply iterate over all spiral-points, calculating and storing the new value, until the original input is surpassed.
   */
  @Override
  public Integer solveSecond(Integer input) {
    Map<Point, Integer> grid = new HashMap<>();
    grid.put(point(0, 0), 1);

    int position = 2;
    int value = 0;

    while (value <= input) {
      Point coordinate = getCoordinates(position++);
      value = getNeighboursSum(coordinate, grid);
      grid.put(coordinate, value);
    }

    return value;
  }

  private int getNeighboursSum(Point coordinate, Map<Point, Integer> grid) {
    return neighbours.stream()
        .map(neighbour -> point(
            coordinate.x + neighbour.x,
            coordinate.y + neighbour.y))
        .map(grid::get)
        .filter(Objects::nonNull)
        .reduce(Integer::sum)
        .orElseThrow(IllegalArgumentException::new);
  }

  private Point getCoordinates(int position) {
    int x, y;
    int root = calculateOddRoot(position);
    int base = (root - 1) / 2 + 1;

    // Exactly the square value -> special case (base - 1)
    if (root * root == position) {
      x = +base - 1;
      y = -base + 1;
    }
    // Vertical-right on the spiral
    else if (position <= root * root + 1 * (root + 1)) {
      x = +base;
      y = -base + (position - (root * root + 0 * (root + 1)));
    }
    // Horizontal-up on the spiral
    else if (position <= root * root + 2 * (root + 1)) {
      x = +base - (position - (root * root + 1 * (root + 1)));
      y = +base;
    }
    // Vertical-left on the spiral
    else if (position <= root * root + 3 * (root + 1)) {
      x = -base;
      y = +base - (position - (root * root + 2 * (root + 1)));
    }
    // Horizontal-down on the spiral
    else {
      x = -base + (position - (root * root + 3 * (root + 1)));
      y = -base;
    }

    return point(x, y);
  }

  private int calculateOddRoot(int input) {
    int root = (int) floor(sqrt(input));

    if (root % 2 == 0) {
      root--;
    }

    return root;
  }

  static class Point {

    private final int x;
    private final int y;

    private Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    static Point point(int x, int y) {
      return new Point(x, y);
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
}
