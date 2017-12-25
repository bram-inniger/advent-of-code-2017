package be.inniger.advent.solutions;

import java.util.ArrayList;
import java.util.List;

import be.inniger.advent.DailyProblem;

public class Day17 implements DailyProblem<Integer, Integer> {

  @Override
  public Integer solveFirst(Integer input) {
    List<Integer> buffer = new ArrayList<>();
    int currentPos = 0;

    buffer.add(currentPos, 0);

    for (int i = 1; i <= 2017; i++) {
      currentPos = (currentPos + input) % i + 1;
      buffer.add(currentPos, i);
    }

    return buffer.get((currentPos + 1) % 2017);
  }

  /**
   * The trick is to see that the lowest possible index to insert a value *after* is 0.
   * Thus value 0 itself will always remain at index 0.
   * The solution is to find the last value inserted in index 1.
   * <p>
   * Simply loop similarly as done in Part 1, but completely disregard building the buffer.
   * Every time an element would have been inserted in index 1 record it.
   * The last one inserted is guaranteed to be the solution.
   */
  @Override
  public Integer solveSecond(Integer input) {
    int currentPos = 0;
    int lastInsertedAfterZero = 0;

    for (int i = 1; i <= 50_000_000; i++) {
      currentPos = (currentPos + input) % i + 1;

      if (currentPos == 1) {
        lastInsertedAfterZero = i;
      }
    }

    return lastInsertedAfterZero;
  }
}
