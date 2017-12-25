package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.readSingleLine;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day17Test extends DailyTest {

  private final Day17 problem = new Day17();

  @Override
  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst(3))
        .isEqualTo(638);
  }

  @Override
  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(readSingleLine(FILE_NAME, Integer::parseInt)))
        .isEqualTo(866);
  }

  @Override
  @Ignore
  @Test
  public void validateSecondSampleInputs() {
    // No test input solution given...
  }

  @Override
  @Test
  public void validateSecondSolution() {
    assertThat(problem.solveSecond(readSingleLine(FILE_NAME, Integer::parseInt)))
        .isEqualTo(11995607);
  }
}
