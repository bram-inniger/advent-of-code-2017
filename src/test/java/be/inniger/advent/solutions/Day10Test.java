package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.readSameLineInts;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day10Test extends DailyTest {

  @Override
  @Test
  public void validateFirstSampleInputs() {
    assertThat(new Day10(5).solveFirst(asList(3, 4, 1, 5)))
        .isEqualTo(12);
  }

  @Override
  @Test
  public void validateFirstSolution() {
    assertThat(new Day10(256).solveFirst(readSameLineInts(FILE_NAME)))
        .isEqualTo(11375);
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
