package be.inniger.advent.days01to10;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import be.inniger.advent.DailyProblem;

public class Day02 implements DailyProblem {

  @Override
  public int solveFirst(List<String> inputs) {
    return inputs.stream()
        .map(this::tokenize)
        .map(this::maxMinDifference)
        .reduce(Integer::sum)
        .orElseThrow(IllegalArgumentException::new);
  }

  @Override
  public int solveSecond(List<String> inputs) {
    throw new UnsupportedOperationException();
  }

  private List<Integer> tokenize(String line) {
    return Pattern.compile("\\s+")
        .splitAsStream(line)
        .map(Integer::parseInt)
        .collect(toList());
  }

  private int maxMinDifference(List<Integer> values) {
    return toIntStream(values).max().orElseThrow(IllegalArgumentException::new) -
        toIntStream(values).min().orElseThrow(IllegalArgumentException::new);
  }

  private IntStream toIntStream(List<Integer> values) {
    return values.stream()
        .mapToInt(Integer::intValue);
  }
}
