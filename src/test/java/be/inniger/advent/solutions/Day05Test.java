package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.readInts;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class Day05Test {

  private static final String FILE_NAME = "05.txt";
  private final Day05 problem = new Day05();

  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst(asList(0, 3, 0, 1, -3)))
        .isEqualTo(5);
  }

  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(readInts(FILE_NAME)))
        .isEqualTo(388611);
  }
}
