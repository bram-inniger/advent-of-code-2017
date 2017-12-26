package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.readSingleLine;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day10Test extends DailyTest {

  private final Day10 problem = new Day10();

  @Override
  @Ignore("Only works if the list-size would be equal to 5")
  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst("3,4,1,5"))
        .isEqualTo("12");
  }

  @Override
  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(readSingleLine(FILE_NAME)))
        .isEqualTo("11375");
  }

  @Override
  @Test
  public void validateSecondSampleInputs() {
    assertThat(problem.solveSecond(""))
        .isEqualTo("a2582a3a0e66e6e86e3812dcb672a272");

    assertThat(problem.solveSecond("AoC 2017"))
        .isEqualTo("33efeb34ea91902bb2f59c9920caa6cd");

    assertThat(problem.solveSecond("1,2,3"))
        .isEqualTo("3efbe78a8d82f29979031a4aa0b16a9d");

    assertThat(problem.solveSecond("1,2,4"))
        .isEqualTo("63960835bcdc130f0b66d7ff4f6a5a8e");
  }

  @Override
  @Test
  public void validateSecondSolution() {
    assertThat(problem.solveSecond(readSingleLine(FILE_NAME)))
        .isEqualTo("e0387e2ad112b7c2ef344e44885fe4d8");
  }
}
