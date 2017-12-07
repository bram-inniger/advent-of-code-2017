package be.inniger.advent.solutions;

import static java.util.Comparator.comparingInt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import be.inniger.advent.DailyProblem;

public class Day06 implements DailyProblem<List<Integer>, Integer> {

  @Override
  public Integer solveFirst(List<Integer> input) {
    return solve(input, (__, states) -> states.size());
  }

  @Override
  public Integer solveSecond(List<Integer> input) {
    return solve(input, (state, states) -> states.size() - states.get(state));
  }

  private int solve(List<Integer> startingState, BiFunction<Memory, Map<Memory, Integer>, Integer> solutionCalculator) {
    int cycles = 0;
    Map<Memory, Integer> states = new HashMap<>();
    Memory state = new Memory(startingState);

    while (!states.containsKey(state)) {
      states.put(state, cycles++);
      state = state.redistribute();
    }

    return solutionCalculator.apply(state, states);
  }

  private static final class Memory {

    private final List<Integer> banks;

    private Memory(List<Integer> banks) {
      this.banks = new ArrayList<>(banks);
    }

    private Memory redistribute() {
      Memory next = new Memory(this.banks);

      int highestNumberIndex = getHighestNumberIndex(next.banks);
      int toRedistribute = next.banks.get(highestNumberIndex);
      next.banks.set(highestNumberIndex, 0);

      for (int i = 0; i < toRedistribute; i++) {
        int index = (highestNumberIndex + 1 + i) % next.banks.size();
        next.banks.set(index, next.banks.get(index) + 1);
      }

      return next;
    }

    private int getHighestNumberIndex(List<Integer> banks) {
      return IntStream.range(0, banks.size())
          .boxed()
          .max(comparingInt(banks::get))
          .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }

      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Memory memory = (Memory) o;

      return new EqualsBuilder()
          .append(banks, memory.banks)
          .isEquals();
    }

    @Override
    public int hashCode() {
      return new HashCodeBuilder(17, 37)
          .append(banks)
          .toHashCode();
    }
  }
}
