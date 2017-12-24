package be.inniger.advent.solutions;

import static be.inniger.advent.util.InputFileReader.readMultiLine;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import be.inniger.advent.DailyTest;

public class Day07Test extends DailyTest {

  private final Day07 problem = new Day07();

  @Override
  @Test
  public void validateFirstSampleInputs() {
    assertThat(problem.solveFirst(asList(
        "pbga (66)",
        "xhth (57)",
        "ebii (61)",
        "havc (66)",
        "ktlj (57)",
        "fwft (72) -> ktlj, cntj, xhth",
        "qoyq (66)",
        "padx (45) -> pbga, havc, qoyq",
        "tknk (41) -> ugml, padx, fwft",
        "jptl (61)",
        "ugml (68) -> gyxo, ebii, jptl",
        "gyxo (61)",
        "cntj (57)")))
        .isEqualTo("tknk");
  }

  @Override
  @Test
  public void validateFirstSolution() {
    assertThat(problem.solveFirst(readMultiLine(FILE_NAME)))
        .isEqualTo("azqje");
  }

  @Override
  @Test
  public void validateSecondSampleInputs() {
    assertThat(problem.solveSecond(asList(
        "pbga (66)",
        "xhth (57)",
        "ebii (61)",
        "havc (66)",
        "ktlj (57)",
        "fwft (72) -> ktlj, cntj, xhth",
        "qoyq (66)",
        "padx (45) -> pbga, havc, qoyq",
        "tknk (41) -> ugml, padx, fwft",
        "jptl (61)",
        "ugml (68) -> gyxo, ebii, jptl",
        "gyxo (61)",
        "cntj (57)")))
        .isEqualTo("60");
  }

  @Override
  @Test
  public void validateSecondSolution() {
    assertThat(problem.solveSecond(readMultiLine(FILE_NAME)))
        .isEqualTo("646");
  }
}
