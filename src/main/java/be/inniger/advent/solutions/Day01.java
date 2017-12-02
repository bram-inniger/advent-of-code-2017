package be.inniger.advent.solutions;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

import be.inniger.advent.DailyProblem;

public class Day01 implements DailyProblem {

  @Override
  public int solveFirst(List<String> inputs) {
    return solve(inputs.get(0),
        (input, i) -> input.charAt(i) == input.charAt((i + 1) % input.length()));
  }

  @Override
  public int solveSecond(List<String> inputs) {
    return solve(inputs.get(0),
        (input, i) -> input.charAt(i) == input.charAt((i + input.length() / 2) % input.length()));
  }

  private int solve(String input, BiPredicate<String, Integer> predicate) {
    return IntStream.range(0, input.length())
        .filter(i -> predicate.test(input, i))
        .map(input::charAt)
        .map(Character::getNumericValue)
        .sum();
  }
}
