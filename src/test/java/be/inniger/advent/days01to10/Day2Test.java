package be.inniger.advent.days01to10;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import be.inniger.advent.DailyProblem;

public class Day2Test {

  private final DailyProblem problem = new Day2();

  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst(asList(
        "5 1 9 5",
        "7 5 3",
        "2 4 6 8"
    )))
        .isEqualTo(18);
  }
}
