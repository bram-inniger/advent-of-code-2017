package be.inniger.advent.solutions;

import java.util.List;
import java.util.stream.IntStream;

import be.inniger.advent.DailyProblem;

public class Day10 implements DailyProblem<List<Integer>, Integer> {

  private int[] list;

  Day10(int listLength) {
    list = IntStream.range(0, listLength)
        .toArray();
  }

  @Override
  public Integer solveFirst(List<Integer> input) {
    int pos = 0;
    int skip = 0;

    for (int inputLength : input) {
      reverse(pos, inputLength);
      pos = (pos + inputLength + skip) % list.length;
      skip++;
    }

    return list[0] * list[1];
  }

  @Override
  public Integer solveSecond(List<Integer> input) {
    throw new UnsupportedOperationException();
  }

  private void reverse(int pos, int length) {
    for (int i = 0; i < length / 2; i++) {
      int aPos = (pos + i) % list.length;
      int bPas = (pos + length - i - 1) % list.length;

      int tmp = list[aPos];
      list[aPos] = list[bPas];
      list[bPas] = tmp;
    }
  }
}
