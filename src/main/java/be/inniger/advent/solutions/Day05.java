package be.inniger.advent.solutions;

import java.util.List;
import java.util.function.IntUnaryOperator;

import be.inniger.advent.DailyProblem;

public class Day05 implements DailyProblem<List<Integer>, Integer> {

  @Override
  public Integer solveFirst(List<Integer> offsets) {
    return solve(offsets, __ -> 1);
  }

  @Override
  public Integer solveSecond(List<Integer> offsets) {
    return solve(offsets, offset -> offset < 3 ? 1 : -1);
  }

  private int solve(List<Integer> offsets, IntUnaryOperator offsetIncreaser) {
    int index = 0;
    int steps = 0;

    while (index >= 0 && index < offsets.size()) {
      int offset = offsets.get(index);
      offsets.set(index, offset + offsetIncreaser.applyAsInt(offset));
      index += offset;
      steps++;
    }

    return steps;
  }
}
