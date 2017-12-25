package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.readSingleLine;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.regex.Pattern;

import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day16Test extends DailyTest {

  @Override
  @Test
  public void validateFirstSampleInputs() {
    assertThat(new Day16(4).solveFirst(asList(
        "s1",
        "x3/4",
        "pe/b"
    )))
        .isEqualTo("baedc");
  }

  @Override
  @Test
  public void validateFirstSolution() {
    assertThat(new Day16(15).solveFirst(readSingleLine(FILE_NAME, Pattern.compile(","))))
        .isEqualTo("glnacbhedpfjkiom");
  }

  @Override
  @Test
  public void validateSecondSampleInputs() {
    assertThat(new Day16(4).solveSecond(asList(
        "s1",
        "x3/4",
        "pe/b"
    )))
        .isEqualTo("abcde");
  }

  @Override
  @Test
  public void validateSecondSolution() {
    assertThat(new Day16(15).solveSecond(readSingleLine(FILE_NAME, Pattern.compile(","))))
        .isEqualTo("fmpanloehgkdcbji");
  }
}
