package be.inniger.advent.solutions;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.regex.Pattern;

import be.inniger.advent.DailyProblem;

public class Day04 implements DailyProblem<List<String>> {

  @Override
  public int solveFirst(List<String> input) {
    return (int) input.stream()
        .filter(this::isValid)
        .count();
  }

  @Override
  public int solveSecond(List<String> input) {
    throw new UnsupportedOperationException();
  }

  private boolean isValid(String passphrase) {
    return Pattern.compile("\\s+")
        .splitAsStream(passphrase)
        .collect(groupingBy(
            identity(),
            counting()))
        .values()
        .stream()
        .noneMatch(count -> count > 1);
  }
}
