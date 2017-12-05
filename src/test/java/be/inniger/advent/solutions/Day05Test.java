package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.readInts;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day05Test extends DailyTest {

  private final Day05 problem = new Day05();

  @Override
  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst(asList(0, 3, 0, 1, -3)))
        .isEqualTo(5);
  }

  @Override
  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(readInts(FILE_NAME)))
        .isEqualTo(388611);
  }

  @Override
  @Test
  public void validateSecondSampleInputs() {
    assertThat(problem.solveSecond(asList(0, 3, 0, 1, -3)))
        .isEqualTo(10);
  }

  @Override
  @Test
  public void validateSecondSolution() {
    assertThat(problem.solveSecond(readInts(FILE_NAME)))
        .isEqualTo(27763113);
  }
}
