package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.read;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day02Test extends DailyTest {

  private final Day02 problem = new Day02();

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
    assertThat(problem.solveFirst(read(FILE_NAME)))
        .isEqualTo(36766);
  }

  @Test
  public void validateSecondSampleInputs() {
    assertThat(problem.solveSecond(asList(
        "5 9 2 8",
        "9 4 7 3",
        "3 8 6 5"
    )))
        .isEqualTo(9);
  }

  @Test
  public void validateSecondSolution() {
    assertThat(problem.solveSecond(read(FILE_NAME)))
        .isEqualTo(261);
  }
}
