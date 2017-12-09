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
          i++;
          break;
        case '>':
          garbage = false;
          break;
        case '<':
          garbage = true;
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
      }
    }

    return sum;
  }

  @Override
  public Integer solveSecond(String input) {
    int count = 0;
    boolean garbage = false;

    for (int i = 0; i < input.length(); i++) {
      switch (input.charAt(i)) {
        case '!':
          i++;
          break;
        case '>':
          garbage = false;
          break;
        case '<':
          if (garbage) {
            count++;
          }
          garbage = true;
          break;
        case '{':
          if (garbage) {
            count++;
          }
          break;
        case '}':
          if (garbage) {
            count++;
          }
          break;
        default:
          if (garbage) {
            count++;
          }
      }
    }

    return count;
  }
}
