package be.inniger.advent.solutions;

import static java.util.Map.Entry;
import static java.util.Objects.isNull;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import be.inniger.advent.DailyProblem;

// TODO - Fix the bug and clean up / simplify / shorten the code
public class Day07 implements DailyProblem<List<String>, String> {

  private static final Pattern NON_ALPHANUMERIC = Pattern.compile("\\W+");

  @Override
  public String solveFirst(List<String> input) {
    return assembleTree(input).getRoot().getName();
  }

  @Override
  public String solveSecond(List<String> input) {
    List<Program> unbalancedChildren = assembleTree(input).getPrograms().values()
        .stream()
        .filter(Program::hasUnequalChildren)
        .map(Program::getChildren)
        .limit(1) // FIXME - should not be needed, bug!
        .flatMap(Collection::stream)
//        .peek(System.out::println)
        .collect(toList());

    Map<Integer, Long> unbalancedChildrenWeights = unbalancedChildren
        .stream()
        .map(Program::getTotalWeight)
        .collect(groupingBy(
            identity(),
            counting()));

    int wrongAmount = unbalancedChildrenWeights.entrySet()
        .stream()
        .filter(entry -> entry.getValue() == 1)
        .map(Entry::getKey)
        .findAny()
        .orElseThrow(IllegalArgumentException::new);

    int rightAmount = unbalancedChildrenWeights.entrySet()
        .stream()
        .filter(entry -> entry.getValue() > 1)
        .map(Entry::getKey)
        .findAny()
        .orElseThrow(IllegalArgumentException::new);

    Program wrongChild = unbalancedChildren.stream()
        .filter(program -> program.totalWeight == wrongAmount)
        .findAny()
        .orElseThrow(IllegalArgumentException::new);

    return "" + (wrongChild.weight - wrongAmount + rightAmount);
  }

  private Tree assembleTree(List<String> input) {
    Map<String, Program> programs = input.stream()
        .map(NON_ALPHANUMERIC::split)
        .map(line -> new Program(line[0], line[1]))
        .collect(toMap(
            Program::getName,
            identity()));

    input.stream()
        .map(NON_ALPHANUMERIC::split)
        .forEach(line -> markParentChild(line, programs));

    Program root = getRoot(programs);

    root.calculateTotalWeight();

    return new Tree(root, programs);
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

  private class Tree {

    private final Program root;
    private final Map<String, Program> programs;

    private Tree(Program root, Map<String, Program> programs) {
      this.root = root;
      this.programs = programs;
    }

    private Program getRoot() {
      return root;
    }

    private Map<String, Program> getPrograms() {
      return programs;
    }
  }

  private class Program {

    private final String name;
    private final int weight;
    private final List<Program> children;
    private Program parent;
    private int totalWeight;

    private Program(String name, String weight) {
      this.name = name;
      this.weight = Integer.parseInt(weight);
      this.children = new ArrayList<>();
    }

    private String getName() {
      return name;
    }

    private List<Program> getChildren() {
      return children;
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

    private boolean hasUnequalChildren() {
      return !children.isEmpty() &&
          children.stream()
              .noneMatch(Program::hasUnequalChildren) &&
          children.stream()
              .map(Program::getTotalWeight)
              .distinct()
              .count() > 1;
    }

    private int getTotalWeight() {
      return totalWeight;
    }

    private int calculateTotalWeight() {
      totalWeight = weight + children.stream()
          .map(Program::calculateTotalWeight)
          .reduce(Integer::sum)
          .orElse(0);

      return totalWeight;
    }

//    @Override
//    public String toString() {
//      return "Program{" +
//          "name='" + name + '\'' +
//          ", weight=" + weight +
//          ", children=" + children.stream().map(Program::getName).collect(toList()) +
//          ", parent=" + parent.name +
//          ", totalWeight=" + totalWeight +
//          '}';
//    }
  }
}
