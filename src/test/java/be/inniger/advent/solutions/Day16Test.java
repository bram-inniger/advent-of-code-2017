package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.readSingleLine;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.regex.Pattern;

import org.junit.Ignore;
import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day16Test extends DailyTest {

  private final Day16 problem = new Day16();

  @Override
  @Ignore("Test input expects only 5 programs to be present instead of 15")
  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst(asList(
        "s1",
        "x3/4",
        "pe/b"
    )))
        .isEqualTo("baedc");
  }

  @Override
  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(readSingleLine(FILE_NAME, Pattern.compile(","))))
        .isEqualTo("glnacbhedpfjkiom");
  }

  @Override
  @Ignore("No test input solution given...")
  @Test
  public void validateSecondSampleInputs() {
  }

  @Override
  @Test
  public void validateSecondSolution() {
    assertThat(problem.solveSecond(readSingleLine(FILE_NAME, Pattern.compile(","))))
        .isEqualTo("fmpanloehgkdcbji");
  }
}
