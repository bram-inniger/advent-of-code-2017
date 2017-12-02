package be.inniger.advent.days01to10;

import static be.inniger.advent.util.InputFileReader.read;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import be.inniger.advent.DailyProblem;

public class Day02Test {

  private final DailyProblem problem = new Day02();

  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst(asList(
        "5 1 9 5",
        "7 5 3",
        "2 4 6 8"
    )))
        .isEqualTo(18);
  }

  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(read("02.txt")))
        .isEqualTo(36766);
  }
}
