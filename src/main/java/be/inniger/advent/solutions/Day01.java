package be.inniger.advent.solutions;

import java.util.stream.IntStream;

import be.inniger.advent.DailyProblem;

public class Day01 implements DailyProblem<String, Integer> {

  @Override
  public Integer solveFirst(String input) {
    return solve(input, 1);
  }

  @Override
  public Integer solveSecond(String input) {
    return solve(input, input.length() / 2);
  }

  private int solve(String input, int offset) {
    return IntStream.range(0, input.length())
        .filter(i -> input.charAt(i) == input.charAt((i + offset) % input.length()))
        .map(input::charAt)
        .map(Character::getNumericValue)
        .sum();
  }
}
