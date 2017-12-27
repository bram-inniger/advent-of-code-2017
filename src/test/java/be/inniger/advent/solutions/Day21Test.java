package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.readMultiLine;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day21Test extends DailyTest {

  @Override
  @Test
  public void validateFirstSampleInputs() {
    assertThat(new Day21(2).solveFirst(asList(
        "../.# => ##./#../...",
        ".#./..#/### => #..#/..../..../#..#"
    )))
        .isEqualTo(12);
  }

  @Override
  @Test
  public void validateFirstSolution() {
    assertThat(new Day21(5).solveFirst(readMultiLine(FILE_NAME)))
        .isEqualTo(120);
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
