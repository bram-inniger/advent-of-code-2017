package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.read;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day12Test extends DailyTest {

  private final Day12 problem = new Day12();

  @Override
  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst(asList(
        "0 <-> 2",
        "1 <-> 1",
        "2 <-> 0, 3, 4",
        "3 <-> 2, 4",
        "4 <-> 2, 3, 6",
        "5 <-> 6",
        "6 <-> 4, 5"
    )))
        .isEqualTo(6);
  }

  @Override
  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(read(FILE_NAME)))
        .isEqualTo(134);
  }

  @Override
  @Test
  public void validateSecondSampleInputs() {
    assertThat(problem.solveSecond(asList(
        "0 <-> 2",
        "1 <-> 1",
        "2 <-> 0, 3, 4",
        "3 <-> 2, 4",
        "4 <-> 2, 3, 6",
        "5 <-> 6",
        "6 <-> 4, 5"
    )))
        .isEqualTo(2);
  }

  @Override
  @Test
  public void validateSecondSolution() {
    assertThat(problem.solveSecond(read(FILE_NAME)))
        .isEqualTo(193);
  }
}
