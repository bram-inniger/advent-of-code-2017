package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.readSameLineInts;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day06Test extends DailyTest {

  private final Day06 problem = new Day06();

  @Override
  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst(asList(0, 2, 7, 0)))
        .isEqualTo(5);
  }

  @Override
  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(readSameLineInts(FILE_NAME)))
        .isEqualTo(7864);
  }

  @Override
  @Ignore
  @Test
  public void validateSecondSampleInputs() {
  }

  @Override
  @Ignore
  @Test
  public void validateSecondSolution() {
  }
}
