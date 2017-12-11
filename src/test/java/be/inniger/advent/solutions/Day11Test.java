package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.readSameLineStrings;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day11Test extends DailyTest {

  private final Day11 problem = new Day11();

  @Override
  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst(asList("ne", "ne", "ne")))
        .isEqualTo(3);

    assertThat(problem.solveFirst(asList("ne", "ne", "sw", "sw")))
        .isEqualTo(0);

    assertThat(problem.solveFirst(asList("ne", "ne", "s", "s")))
        .isEqualTo(2);

    assertThat(problem.solveFirst(asList("se", "sw", "se", "sw", "sw")))
        .isEqualTo(3);
  }

  @Override
  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(readSameLineStrings(FILE_NAME)))
        .isEqualTo(650);
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
