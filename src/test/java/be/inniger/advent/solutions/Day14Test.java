package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.readLine;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day14Test extends DailyTest {

  private final Day14 problem = new Day14();

  @Override
  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst("flqrgnkx"))
        .isEqualTo(8108);
  }

  @Override
  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(readLine(FILE_NAME)))
        .isEqualTo(8226);
  }

  @Override
  @Test
  public void validateSecondSampleInputs() {
    assertThat(problem.solveSecond("flqrgnkx"))
        .isEqualTo(1242);
  }

  @Override
  @Test
  public void validateSecondSolution() {
    assertThat(problem.solveSecond(readLine(FILE_NAME)))
        .isEqualTo(1128);
  }
}
