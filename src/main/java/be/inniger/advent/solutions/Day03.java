package be.inniger.advent.solutions;

import static java.lang.Math.abs;
import static java.lang.Math.floor;
import static java.lang.Math.sqrt;

import be.inniger.advent.DailyProblem;

@SuppressWarnings("PointlessArithmeticExpression")
public class Day03 implements DailyProblem<Integer> {

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
  public int solveFirst(Integer input) {
    int x, y;
    int root = calculateOddRoot(input);
    int base = (root - 1) / 2 + 1;

    // Exactly the square value -> special case (base - 1)
    if (root * root == input) {
      x = +base - 1;
      y = -base + 1;
    }
    // Vertical-right on the spiral
    else if (input <= root * root + 1 * (root + 1)) {
      x = +base;
      y = -base + (input - (root * root + 0 * (root + 1)));
    }
    // Horizontal-up on the spiral
    else if (input <= root * root + 2 * (root + 1)) {
      x = +base - (input - (root * root + 1 * (root + 1)));
      y = +base;
    }
    // Vertical-left on the spiral
    else if (input <= root * root + 3 * (root + 1)) {
      x = -base;
      y = +base - (input - (root * root + 2 * (root + 1)));
    }
    // Horizontal-down on the spiral
    else {
      x = -base + (input - (root * root + 3 * (root + 1)));
      y = -base;
    }

    return abs(x) + abs(y);
  }

  private int calculateOddRoot(int input) {
    int root = (int) floor(sqrt(input));

    if (root % 2 == 0) {
      root--;
    }

    return root;
  }

  @Override
  public int solveSecond(Integer input) {
    throw new UnsupportedOperationException();
  }
}
