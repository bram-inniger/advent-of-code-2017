package be.inniger.advent.days01to10;

import be.inniger.advent.util.DailyProblem;

public class Day1 extends DailyProblem {

  @Override
  public String solveFirst(String input) {
    long sum = 0L;

    input = input + input.charAt(0);
    for (int i = 0; i < input.length() - 1; i++) {
      if (input.charAt(i) == input.charAt(i + 1)) {
        sum += Integer.parseInt("" + input.charAt(i));
      }
    }

    return "" + sum;
  }

  @Override
  public String solveSecond(String input) {
    long sum = 0L;

    for (int i = 0; i < input.length() / 2; i++) {
      if (input.charAt(i) == input.charAt(i + input.length() / 2)) {
        sum += Integer.parseInt("" + input.charAt(i));
      }
    }

    return "" + 2 * sum;
  }
}
