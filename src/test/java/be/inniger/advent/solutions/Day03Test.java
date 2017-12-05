package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.read;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day03Test extends DailyTest {

  private final Day03 problem = new Day03();

  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst(1))
        .isEqualTo(0);

    assertThat(problem.solveFirst(12))
        .isEqualTo(3);

    assertThat(problem.solveFirst(23))
        .isEqualTo(2);

    assertThat(problem.solveFirst(1024))
        .isEqualTo(31);
  }

  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(Integer.parseInt(read(FILE_NAME).get(0))))
        .isEqualTo(438);
  }
}
