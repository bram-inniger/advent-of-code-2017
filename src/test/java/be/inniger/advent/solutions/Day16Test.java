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

  // Test inputs assume programs range a->e instead of the real a->p
  @Override
  @Ignore
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
