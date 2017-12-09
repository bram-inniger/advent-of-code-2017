package be.inniger.advent.solutions;

import be.inniger.advent.DailyProblem;

public class Day09 implements DailyProblem<String, Integer> {

  @Override
  public Integer solveFirst(String input) {
    int sum = 0;
    int depth = 0;
    boolean garbage = false;

    for (int i = 0; i < input.length(); i++) {
      switch (input.charAt(i)) {
        case '!':
          i++; // Skip te next character
          break;
        case '>':
          garbage = false; // Leave garbage mode
          break;
        case '<':
          garbage = true; // Enter garbage mode, or NOP if already in garbage mode
          break;
        case '{':
          if (!garbage) {
            depth++;
            sum += depth;
          }
          break;
        case '}':
          if (!garbage) {
            depth--;
          }
          break;
        default:
          // Do nothing
      }
    }

    return sum;
  }

  @Override
  public Integer solveSecond(String input) {
    throw new UnsupportedOperationException();
  }
}
