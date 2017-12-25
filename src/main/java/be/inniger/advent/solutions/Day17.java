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
      currentPos = (currentPos + input) % buffer.size() + 1;
      buffer.add(currentPos, i);
    }

    return buffer.get((currentPos + 1) % buffer.size());
  }

  @Override
  public Integer solveSecond(Integer input) {
    throw new UnsupportedOperationException();
  }
}
