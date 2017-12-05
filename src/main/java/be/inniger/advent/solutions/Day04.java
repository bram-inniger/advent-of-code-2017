package be.inniger.advent.solutions;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

import be.inniger.advent.DailyProblem;

public class Day04 implements DailyProblem<List<String>> {

  @Override
  public int solveFirst(List<String> inputs) {
    return solve(inputs, identity());
  }

  @Override
  public int solveSecond(List<String> inputs) {
    return solve(inputs, this::sortLetters);
  }

  private int solve(List<String> passphrases, Function<String, String> mapper) {
    return (int) passphrases.stream()
        .filter(passphrase -> isValid(passphrase, mapper))
        .count();
  }

  private String sortLetters(String word) {
    return Pattern.compile("")
        .splitAsStream(word)
        .sorted()
        .collect(joining());
  }

  private boolean isValid(String passphrase, Function<String, String> mapper) {
    return Pattern.compile("\\s+")
        .splitAsStream(passphrase)
        .map(mapper)
        .collect(groupingBy(
            identity(),
            counting()))
        .values()
        .stream()
        .noneMatch(count -> count > 1);
  }
}
