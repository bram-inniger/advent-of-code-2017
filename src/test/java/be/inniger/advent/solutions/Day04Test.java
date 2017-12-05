package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.read;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class Day04Test {

  private static final String FILE_NAME = "04.txt";
  private final Day04 problem = new Day04();

  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst(singletonList("aa bb cc dd ee")))
        .isEqualTo(1);

    assertThat(problem.solveFirst(singletonList("aa bb cc dd aa")))
        .isEqualTo(0);

    assertThat(problem.solveFirst(singletonList("aa bb cc dd aaa")))
        .isEqualTo(1);
  }

  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(read(FILE_NAME)))
        .isEqualTo(383);
  }
}
