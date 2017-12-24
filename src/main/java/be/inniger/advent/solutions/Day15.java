package be.inniger.advent.solutions;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.IntPredicate;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import be.inniger.advent.DailyProblem;

public class Day15 implements DailyProblem<List<String>, Long> {

  private static final int A_FACTOR = 16_807;
  private static final int B_FACTOR = 48_271;

  @Override
  public Long solveFirst(List<String> inputs) {
    return solve(inputs, 40_000_000,
        (a, b) -> __ -> a.next(1) == b.next(1));
  }

  @Override
  public Long solveSecond(List<String> inputs) {
    return solve(inputs, 5_000_000,
        (a, b) -> __ -> a.next(4) == b.next(8));
  }

  private long solve(List<String> inputs, int nrIterations, BiFunction<Generator, Generator, IntPredicate> predicateBiFunction) {
    Generator a = new Generator(A_FACTOR, getSeed(inputs.get(0)));
    Generator b = new Generator(B_FACTOR, getSeed(inputs.get(1)));

    return IntStream.range(0, nrIterations)
        .filter(predicateBiFunction.apply(a, b))
        .count();
  }

  /**
   * Split input in the form of (ignoring quotes):
   * 'Generator A starts with 618'
   */
  private int getSeed(String input) {
    return Integer.parseInt(Pattern.compile("\\W+").split(input)[4]);
  }

  private static class Generator {

    private static final int DIVISOR = 2_147_483_647;
    private final int factor;
    private long currentValue;

    private Generator(int factor, int seed) {
      this.factor = factor;
      this.currentValue = seed;
    }

    /**
     * Calculates the next value of the generator that is a multiple of coefficient.
     *
     * @return The *lowest 16 bits* of the newly generated value
     */
    private long next(int coefficient) {
      do {
        currentValue = (currentValue * factor) % DIVISOR;
      }
      while (currentValue % coefficient != 0);

      return currentValue & 0xFFFF;
    }
  }
}
