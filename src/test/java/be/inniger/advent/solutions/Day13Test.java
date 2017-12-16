package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.read;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day13Test extends DailyTest {

  private final Day13 problem = new Day13();

  @Override
  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst(asList(
        "0: 3",
        "1: 2",
        "4: 4",
        "6: 4"
    )))
        .isEqualTo(24);
  }

  @Override
  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(read(FILE_NAME)))
        .isEqualTo(2604);
  }

  @Override
  @Test
  public void validateSecondSampleInputs() {
    assertThat(problem.solveSecond(asList(
        "0: 3",
        "1: 2",
        "4: 4",
        "6: 4"
    )))
        .isEqualTo(10);
  }

  @Override
  @Test
  public void validateSecondSolution() {
    assertThat(problem.solveSecond(read(FILE_NAME)))
        .isEqualTo(3941460);
  }
}
