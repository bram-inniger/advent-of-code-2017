package be.inniger.advent.solutions;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import be.inniger.advent.DailyProblem;

// TODO - Externalise 'visited' out of Program
// TODO - Rewrite to use Union Find instead of BFS
public class Day12 implements DailyProblem<List<String>, Long> {

  @Override
  public Long solveFirst(List<String> inputs) {
    Map<Integer, Program> programs = generateGraph(inputs);

    markVisited(programs, 0);

    return programs.values().stream()
        .filter(Program::isVisited)
        .count();
  }

  @Override
  public Long solveSecond(List<String> inputs) {
    Map<Integer, Program> programs = generateGraph(inputs);
    long groupsSeen = 0;

    while (!programs.isEmpty()) {
      groupsSeen++;
      markVisited(programs, programs.keySet().iterator().next());
      programs.values().removeIf(Program::isVisited);
    }

    return groupsSeen;
  }

  private void markVisited(Map<Integer, Program> programs, int from) {
    Deque<Integer> toVisit = new ArrayDeque<>();
    toVisit.push(from);

    while (!toVisit.isEmpty()) {
      Program program = programs.get(toVisit.pop());

      if (!program.isVisited()) {
        program.markVisited();
        program.connectedTo.forEach(toVisit::push);
      }
    }
  }

  private Map<Integer, Program> generateGraph(List<String> inputs) {
    return inputs.stream()
        .map(Pattern.compile("\\W+")::splitAsStream)
        .map(input -> input
            .mapToInt(Integer::parseInt)
            .boxed()
            .collect(toList()))
        .collect(toMap(
            input -> input.get(0),
            input -> new Program(input.subList(1, input.size()))
        ));
  }

  private class Program {

    private final List<Integer> connectedTo;
    private boolean visited = false;

    private Program(List<Integer> connectedTo) {
      this.connectedTo = connectedTo;
    }

    private boolean isVisited() {
      return visited;
    }

    private void markVisited() {
      this.visited = true;
    }
  }
}
