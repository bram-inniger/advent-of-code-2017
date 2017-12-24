package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.readSingleLine;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day01Test extends DailyTest {

  private final Day01 problem = new Day01();

  @Override
  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst("1122"))
        .isEqualTo(3);

    assertThat(problem.solveFirst("1111"))
        .isEqualTo(4);

    assertThat(problem.solveFirst("1234"))
        .isEqualTo(0);

    assertThat(problem.solveFirst("91212129"))
        .isEqualTo(9);
  }

  @Override
  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(readSingleLine(FILE_NAME)))
        .isEqualTo(1102);
  }

  @Override
  @Test
  public void validateSecondSampleInputs() {
    assertThat(problem.solveSecond("1212"))
        .isEqualTo(6);

    assertThat(problem.solveSecond("1221"))
        .isEqualTo(0);

    assertThat(problem.solveSecond("123425"))
        .isEqualTo(4);

    assertThat(problem.solveSecond("123123"))
        .isEqualTo(12);

    assertThat(problem.solveSecond("12131415"))
        .isEqualTo(4);
  }

  @Override
  @Test
  public void validateSecondSolution() {
    assertThat(problem.solveSecond(readSingleLine(FILE_NAME)))
        .isEqualTo(1076);
  }
}
