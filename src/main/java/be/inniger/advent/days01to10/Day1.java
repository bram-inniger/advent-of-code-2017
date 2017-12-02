package be.inniger.advent.days01to10;

import java.util.List;

import be.inniger.advent.DailyProblem;

public class Day1 implements DailyProblem {

  @Override
  public int solveFirst(List<String> inputs) {
    int sum = 0;

    String input = inputs.get(0);
    input = input + input.charAt(0);
    for (int i = 0; i < input.length() - 1; i++) {
      if (input.charAt(i) == input.charAt(i + 1)) {
        sum += Integer.parseInt("" + input.charAt(i));
      }
    }

    return sum;
  }

  @Override
  public int solveSecond(List<String> inputs) {
    int sum = 0;

    String input = inputs.get(0);
    for (int i = 0; i < input.length() / 2; i++) {
      if (input.charAt(i) == input.charAt(i + input.length() / 2)) {
        sum += Integer.parseInt("" + input.charAt(i));
      }
    }

    return 2 * sum;
  }
}
