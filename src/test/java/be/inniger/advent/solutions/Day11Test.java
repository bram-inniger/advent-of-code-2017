package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.readSingleLine;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;

import be.inniger.advent.DailyTest;
import be.inniger.advent.util.InputFileReader;

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
    assertThat(problem.solveFirst(readSingleLine(FILE_NAME, Pattern.compile("\\W+"))))
        .isEqualTo(650);
  }

  @Override
  @Test
  public void validateSecondSampleInputs() {
    // No new sample inputs given
  }

  @Override
  @Test
  public void validateSecondSolution() {
    assertThat(problem.solveSecond(readSingleLine(FILE_NAME, Pattern.compile("\\W+"))))
        .isEqualTo(1465);
  }
}
