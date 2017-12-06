package be.inniger.advent.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import be.inniger.advent.DailyProblem;

public class Day06 implements DailyProblem<List<Integer>> {

  @Override
  public int solveFirst(List<Integer> input) {
    Set<Memory> states = new HashSet<>();
    Memory state = new Memory(input);

    while (!states.contains(state)) {
      states.add(state);
      state = state.redistribute();
    }

    return states.size();
  }

  @Override
  public int solveSecond(List<Integer> input) {
    int cycles = 0;
    Map<Memory, Integer> states = new HashMap<>();
    Memory state = new Memory(input);

    while (!states.containsKey(state)) {
      states.put(state, cycles++);
      state = state.redistribute();
    }

    return cycles - states.get(state);
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
      int index = 0;
      int value = 0;

      for (int i = 0; i < banks.size(); i++) {
        if (banks.get(i) > value) {
          index = i;
          value = banks.get(i);
        }
      }

      return index;
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
