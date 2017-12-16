package be.inniger.advent.solutions;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.regex.Pattern;

import be.inniger.advent.DailyProblem;

public class Day13 implements DailyProblem<List<String>, Integer> {

  @Override
  public Integer solveFirst(List<String> inputs) {
    return inputs.stream()
        .map(Scanner::new)
        .filter(scanner -> scanner.depth % ((scanner.range - 1) * 2) == 0)
        .mapToInt(scanner -> scanner.range * scanner.depth)
        .sum();
  }

  @Override
  public Integer solveSecond(List<String> inputs) {
    throw new UnsupportedOperationException();
  }

  private static class Scanner {

    private final int depth;
    private final int range;

    private Scanner(String scanner) {
      List<Integer> parsedScanner = Pattern.compile("\\W+")
          .splitAsStream(scanner)
          .map(Integer::parseInt)
          .collect(toList());

      this.depth = parsedScanner.get(0);
      this.range = parsedScanner.get(1);
    }
  }
}
