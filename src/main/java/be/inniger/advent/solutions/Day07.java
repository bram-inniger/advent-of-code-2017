package be.inniger.advent.solutions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import be.inniger.advent.DailyProblem;

public class Day07 implements DailyProblem<List<String>, String> {

  @Override
  public String solveFirst(List<String> input) {
    Set<String> nodes = new HashSet<>();
    Set<String> childNodes = new HashSet<>();

    input.stream()
        .map(program -> Pattern.compile("\\W+").split(program))
        .forEach(program -> markNodes(program, nodes, childNodes));

    nodes.removeAll(childNodes);

    return nodes.stream()
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

  @Override
  public String solveSecond(List<String> input) {
    throw new UnsupportedOperationException();
  }

  private void markNodes(String[] program, Set<String> nodes, Set<String> childNodes) {
    nodes.add(program[0]);

    if (program.length > 2) {
      childNodes.addAll(Arrays
          .asList(program)
          .subList(2, program.length));
    }
  }
}
