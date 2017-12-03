package be.inniger.advent.solutions;

import java.util.function.BiPredicate;
import java.util.stream.IntStream;

import be.inniger.advent.DailyProblem;

public class Day01 implements DailyProblem<String> {

  @Override
  public int solveFirst(String input) {
    return solve(input,
        (str, i) -> str.charAt(i) == str.charAt((i + 1) % str.length()));
  }

  @Override
  public int solveSecond(String input) {
    return solve(input,
        (str, i) -> str.charAt(i) == str.charAt((i + str.length() / 2) % str.length()));
  }

  private int solve(String input, BiPredicate<String, Integer> predicate) {
    return IntStream.range(0, input.length())
        .filter(i -> predicate.test(input, i))
        .map(input::charAt)
        .map(Character::getNumericValue)
        .sum();
  }
}
