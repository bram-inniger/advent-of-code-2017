package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.readMultiLine;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day04Test extends DailyTest {

  private final Day04 problem = new Day04();

  @Override
  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst(singletonList("aa bb cc dd ee")))
        .isEqualTo(1);

    assertThat(problem.solveFirst(singletonList("aa bb cc dd aa")))
        .isEqualTo(0);

    assertThat(problem.solveFirst(singletonList("aa bb cc dd aaa")))
        .isEqualTo(1);
  }

  @Override
  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(readMultiLine(FILE_NAME)))
        .isEqualTo(383);
  }

  @Override
  @Test
  public void validateSecondSampleInputs() {
    assertThat(problem.solveSecond(singletonList("abcde fghij")))
        .isEqualTo(1);

    assertThat(problem.solveSecond(singletonList("abcde xyz ecdab")))
        .isEqualTo(0);

    assertThat(problem.solveSecond(singletonList("a ab abc abd abf abj")))
        .isEqualTo(1);

    assertThat(problem.solveSecond(singletonList("iiii oiii ooii oooi oooo")))
        .isEqualTo(1);

    assertThat(problem.solveSecond(singletonList("oiii ioii iioi iiio")))
        .isEqualTo(0);
  }

  @Override
  @Test
  public void validateSecondSolution() {
    assertThat(problem.solveSecond(readMultiLine(FILE_NAME)))
        .isEqualTo(265);
  }
}
