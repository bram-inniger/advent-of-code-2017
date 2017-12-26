package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.readMultiLine;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day20Test extends DailyTest {

  private final Day20 problem = new Day20();

  @Override
  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst(asList(
        "p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>",
        "p=<4,0,0>, v=<0,0,0>, a=<-2,0,0>"
    )))
        .isEqualTo(0);
  }

  @Override
  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(readMultiLine(FILE_NAME)))
        .isEqualTo(119);
  }

  @Override
  @Test
  public void validateSecondSampleInputs() {
    assertThat(problem.solveSecond(asList(
        "p=<-6,0,0>, v=<3,0,0>, a=<0,0,0>",
        "p=<-4,0,0>, v=<2,0,0>, a=<0,0,0>",
        "p=<-2,0,0>, v=<1,0,0>, a=<0,0,0>",
        "p=<3,0,0>, v=<-1,0,0>, a=<0,0,0>"
    )))
        .isEqualTo(1);
  }

  @Override
  @Test
  public void validateSecondSolution() {
    assertThat(problem.solveSecond(readMultiLine(FILE_NAME)))
        .isEqualTo(471);
  }
}
