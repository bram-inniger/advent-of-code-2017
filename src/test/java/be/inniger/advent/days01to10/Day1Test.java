package be.inniger.advent.days01to10;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import be.inniger.advent.DailyProblem;

public class Day1Test {

  private final DailyProblem problem = new Day1();

  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst(singletonList("1122")))
        .isEqualTo("3");

    assertThat(problem.solveFirst(singletonList("1111")))
        .isEqualTo("4");

    assertThat(problem.solveFirst(singletonList("1234")))
        .isEqualTo("0");

    assertThat(problem.solveFirst(singletonList("91212129")))
        .isEqualTo("9");
  }

  @Test
  public void validateSecondSampleInputs() {
    assertThat(problem.solveSecond(singletonList("1212")))
        .isEqualTo("6");

    assertThat(problem.solveSecond(singletonList("1221")))
        .isEqualTo("0");

    assertThat(problem.solveSecond(singletonList("123425")))
        .isEqualTo("4");

    assertThat(problem.solveSecond(singletonList("123123")))
        .isEqualTo("12");

    assertThat(problem.solveSecond(singletonList("12131415")))
        .isEqualTo("4");
  }
}
