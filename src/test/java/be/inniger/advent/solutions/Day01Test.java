package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.read;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class Day01Test {

  private static final String FILE_NAME = "01.txt";
  private final Day01 problem = new Day01();

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

  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(read(FILE_NAME).get(0)))
        .isEqualTo(1102);
  }

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

  @Test
  public void validateSecondSolution() {
    assertThat(problem.solveSecond(read(FILE_NAME).get(0)))
        .isEqualTo(1076);
  }
}
