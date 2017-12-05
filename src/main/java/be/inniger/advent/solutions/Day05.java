package be.inniger.advent.solutions;

import static java.util.stream.Collectors.joining;

import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.inniger.advent.DailyProblem;

public class Day05 implements DailyProblem<List<Integer>> {

  private static final Logger LOG = LoggerFactory.getLogger(Day05.class);

  @Override
  public int solveFirst(List<Integer> offsets) {
    int index = 0;
    int steps = 0;

    while (index >= 0 && index < offsets.size()) {
      int nextIndex = offsets.get(index);
      offsets.set(index, nextIndex + 1);
      index += nextIndex;
      steps++;
    }

    return steps;
  }

  @Override
  public int solveSecond(List<Integer> input) {
    throw new UnsupportedOperationException();
  }

  @SuppressWarnings("unused")
  private void debugPrint(List<Integer> offsets, int index) {
    LOG.info(IntStream.range(0, offsets.size())
        .mapToObj(i -> String.valueOf(
            i == index ? '(' : ' ') +
            offsets.get(i) +
            (i == index ? ')' : ' ') +
            ' ')
        .collect(joining()));
  }
}
