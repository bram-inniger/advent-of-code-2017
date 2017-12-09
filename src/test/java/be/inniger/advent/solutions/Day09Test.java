package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.read;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day09Test extends DailyTest {

  private final Day09 problem = new Day09();

  @Override
  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst("{}"))
        .isEqualTo(1);

    assertThat(problem.solveFirst("{{{}}}"))
        .isEqualTo(6);

    assertThat(problem.solveFirst("{{},{}}"))
        .isEqualTo(5);

    assertThat(problem.solveFirst("{{{},{},{{}}}}"))
        .isEqualTo(16);

    assertThat(problem.solveFirst("{<a>,<a>,<a>,<a>}"))
        .isEqualTo(1);

    assertThat(problem.solveFirst("{{<ab>},{<ab>},{<ab>},{<ab>}}"))
        .isEqualTo(9);

    assertThat(problem.solveFirst("{{<!!>},{<!!>},{<!!>},{<!!>}}"))
        .isEqualTo(9);

    assertThat(problem.solveFirst("{{<a!>},{<a!>},{<a!>},{<ab>}}"))
        .isEqualTo(3);
  }

  @Override
  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(read(FILE_NAME).get(0)))
        .isEqualTo(14190);
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
