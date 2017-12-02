package be.inniger.advent.days01to10;

import static java.util.Comparator.naturalOrder;

import java.util.Arrays;
import java.util.List;

import be.inniger.advent.DailyProblem;

public class Day02 implements DailyProblem {

  @Override
  public int solveFirst(List<String> inputs) {
    return inputs.stream()
        .map(string -> string.split("\\s+"))
        .map(this::minMaxDifference)
        .reduce(Integer::sum)
        .orElseThrow(IllegalArgumentException::new);
  }

  @Override
  public int solveSecond(List<String> inputs) {
    throw new UnsupportedOperationException();
  }

  private int minMaxDifference(String[] values) {
    return Arrays.stream(values)
        .map(Integer::parseInt)
        .max(naturalOrder())
        .orElseThrow(IllegalArgumentException::new) -
        Arrays.stream(values)
            .map(Integer::parseInt)
            .min(naturalOrder())
            .orElseThrow(IllegalArgumentException::new);
  }
}
