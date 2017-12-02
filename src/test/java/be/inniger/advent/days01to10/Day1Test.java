package be.inniger.advent.days01to10;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import be.inniger.advent.util.DailyProblem;

public class Day1Test {

  private final DailyProblem problem = new Day1();

  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst("1122"))
        .isEqualTo("3");

    assertThat(problem.solveFirst("1111"))
        .isEqualTo("4");

    assertThat(problem.solveFirst("1234"))
        .isEqualTo("0");

    assertThat(problem.solveFirst("91212129"))
        .isEqualTo("9");
  }

  @Ignore
  @Test
  public void validateSecondSampleInputs() {
    assertThat(problem.solveFirst("1212"))
        .isEqualTo("6");

    assertThat(problem.solveFirst("1221"))
        .isEqualTo("0");

    assertThat(problem.solveFirst("123425"))
        .isEqualTo("4");

    assertThat(problem.solveFirst("123123"))
        .isEqualTo("12");

    assertThat(problem.solveFirst("12131415"))
        .isEqualTo("4");
  }
}
