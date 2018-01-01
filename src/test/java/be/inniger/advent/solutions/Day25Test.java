package be.inniger.advent.solutions;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Ignore;
import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day25Test extends DailyTest {

  private final Day25 problem = new Day25();

  @Override
  @Ignore("Program does not read inputs, test or otherwise, and hardcodes the solution input")
  @Test
  public void validateFirstSampleInputs() {
  }

  @Override
  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(emptyList()))
        .isEqualTo(2870);
  }

  @Override
  @Ignore("Part 2 did not involve coding")
  @Test
  public void validateSecondSampleInputs() {
  }

  @Override
  @Test
  public void validateSecondSolution() {
    assertThatThrownBy(() -> problem.solveSecond(emptyList()))
        .isInstanceOf(UnsupportedOperationException.class);
  }
}
