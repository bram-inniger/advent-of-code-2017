package be.inniger.advent.solutions;

import static java.util.Objects.isNull;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import be.inniger.advent.DailyProblem;

public class Day07 implements DailyProblem<List<String>, String> {

  private static final Pattern NON_ALPHANUMERIC = Pattern.compile("\\W+");

  @Override
  public String solveFirst(List<String> input) {
    return getRoot(assembleTree(input)).getName();
  }

  @Override
  public String solveSecond(List<String> input) {
    throw new UnsupportedOperationException();
  }

  private Map<String, Program> assembleTree(List<String> input) {
    Map<String, Program> programs = input.stream()
        .map(NON_ALPHANUMERIC::split)
        .map(line -> line[0])
        .map(Program::new)
        .collect(toMap(
            Program::getName,
            identity()));

    input.stream()
        .map(NON_ALPHANUMERIC::split)
        .forEach(line -> markParentChild(line, programs));

    return programs;
  }

  private void markParentChild(String[] line, Map<String, Program> programs) {
    if (line.length > 2) {
      Program parent = programs.get(line[0]);

      Arrays.stream(line, 2, line.length)
          .map(programs::get)
          .forEach(child -> {
            child.setParent(parent);
            parent.addChild(child);
          });
    }
  }

  private Program getRoot(Map<String, Program> programs) {
    return programs.values().stream()
        .filter(Program::isRoot)
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

  private class Program {

    private final String name;
    private final List<Program> children;
    private Program parent;

    private Program(String name) {
      this.name = name;
      this.children = new ArrayList<>();
    }

    private String getName() {
      return name;
    }

    private void setParent(Program parent) {
      this.parent = parent;
    }

    private void addChild(Program child) {
      children.add(child);
    }

    private boolean isRoot() {
      return isNull(parent);
    }
  }
}
