package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.readMultiLine;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day21Test extends DailyTest {

  private final Day21 problem = new Day21();

  @Override
  @Ignore("Test input expects only 2 iterations instead of 5")
  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst(asList(
        "../.# => ##./#../...",
        ".#./..#/### => #..#/..../..../#..#"
    )))
        .isEqualTo(12);
  }

  @Override
  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(readMultiLine(FILE_NAME)))
        .isEqualTo(120);
  }

  @Override
  @Ignore("No test input solution given...")
  @Test
  public void validateSecondSampleInputs() {
  }

  @Override
  @Test
  public void validateSecondSolution() {
    assertThat(problem.solveSecond(readMultiLine(FILE_NAME)))
        .isEqualTo(2204099);
  }
}
