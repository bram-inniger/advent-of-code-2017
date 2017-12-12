package be.inniger.advent.solutions;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import be.inniger.advent.DailyProblem;

public class Day12 implements DailyProblem<List<String>, Integer> {

  @Override
  public Integer solveFirst(List<String> inputs) {
    Map<Integer, Program> programs = generateGraph(inputs);

    Deque<Integer> toVisit = new ArrayDeque<>();
    toVisit.push(0);
    int count = 0;

    while (!toVisit.isEmpty()) {
      Program program = programs.get(toVisit.pop());

      if (!program.isVisited()) {
        count++;
        program.markVisited();
        program.connectedTo.forEach(toVisit::push);
      }
    }

    return count;
  }

  @Override
  public Integer solveSecond(List<String> inputs) {
    throw new UnsupportedOperationException();
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
