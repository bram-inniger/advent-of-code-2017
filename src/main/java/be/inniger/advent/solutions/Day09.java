package be.inniger.advent.solutions;

import be.inniger.advent.DailyProblem;

public class Day09 implements DailyProblem<String, Integer> {

  @Override
  public Integer solveFirst(String input) {
    return solve(input).groupSum;
  }

  @Override
  public Integer solveSecond(String input) {
    return solve(input).garbageCount;
  }

  private StreamInfo solve(String stream) {
    int groupSum = 0;
    int depth = 0;
    int garbageCount = 0;
    boolean garbage = false;

    for (int i = 0; i < stream.length(); i++) {
      switch (stream.charAt(i)) {
        case '!':
          i++;
          break;
        case '>':
          garbage = false;
          break;
        case '<':
          if (garbage) {
            garbageCount++;
          }
          garbage = true;
          break;
        case '{':
          if (garbage) {
            garbageCount++;
          }
          else {
            depth++;
            groupSum += depth;
          }
          break;
        case '}':
          if (garbage) {
            garbageCount++;
          }
          else {
            depth--;
          }
          break;
        default:
          if (garbage) {
            garbageCount++;
          }
      }
    }

    return new StreamInfo(groupSum, garbageCount);
  }

  private static class StreamInfo {

    private final int groupSum;
    private final int garbageCount;

    private StreamInfo(int groupSum, int garbageCount) {
      this.groupSum = groupSum;
      this.garbageCount = garbageCount;
    }
  }
}
