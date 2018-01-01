package be.inniger.advent.solutions;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import be.inniger.advent.DailyProblem;

// TODO - Improve performance (cut away all elements with equal left-right and count them later in looking at available building blocks, and / or pre-compute swapped components)
public class Day24 implements DailyProblem<List<String>, Integer> {

  private static final Pattern COMPONENT = Pattern.compile("^(?<left>\\d+)/(?<right>\\d+)$");

  @Override
  public Integer solveFirst(List<String> inputs) {
    return solve(inputs, comparing(Chain::getStrength));
  }

  @Override
  public Integer solveSecond(List<String> inputs) {
    return solve(inputs, comparing(Chain::getLength).thenComparing(Chain::getStrength));
  }

  private int solve(List<String> inputs, Comparator<Chain> comparator) {
    List<Component> components = inputs.stream()
        .map(this::parseInput)
        .collect(toList());

    return components.stream()
        .filter(component -> component.nrPortsLeft == 0 || component.nrPortsRight == 0)
        .map(component -> getBestChain(Chain.newChain(component), copyAndRemove(components, component), comparator))
        .sorted(comparator.reversed())
        .map(Chain::getStrength)
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

  private Chain getBestChain(Chain seen, List<Component> toSee, Comparator<Chain> comparator) {
    return toSee.stream()
        .flatMap(component -> seen.connectComponent(component).stream()
            .map(chain -> getBestChain(chain, copyAndRemove(toSee, component), comparator)))
        .max(comparator)
        .orElse(seen);
  }

  private Component parseInput(String input) {
    Matcher matcher = COMPONENT.matcher(input);

    if (!matcher.matches()) {
      throw new IllegalArgumentException();
    }

    return new Component(Integer.parseInt(matcher.group("left")), Integer.parseInt(matcher.group("right")));
  }

  private List<Component> copyAndRemove(List<Component> toCopy, Component toRemove) {
    return toCopy.stream()
        .filter(component -> !component.equals(toRemove))
        .collect(toList());
  }

  private static class Component {

    private final int nrPortsLeft;
    private final int nrPortsRight;

    private Component(int nrPortsLeft, int nrPortsRight) {
      this.nrPortsLeft = nrPortsLeft;
      this.nrPortsRight = nrPortsRight;
    }

    private Component swap() {
      return new Component(nrPortsRight, nrPortsLeft);
    }
  }

  private static class Chain {

    private final int nrPortsRight;
    private final int strength;
    private final int length;

    private Chain(int nrPortsRight, int strength, int length) {
      this.nrPortsRight = nrPortsRight;
      this.strength = strength;
      this.length = length;
    }

    private static Chain newChain(Component toStart) {
      return Stream.of(toStart, toStart.swap())
          .filter(component -> component.nrPortsLeft == 0)
          .map(component -> new Chain(component.nrPortsRight, component.nrPortsRight, 1))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }

    private List<Chain> connectComponent(Component toConnect) {
      return Stream.of(toConnect, toConnect.swap())
          .filter(component -> this.nrPortsRight == component.nrPortsLeft)
          .map(component -> new Chain(component.nrPortsRight, this.strength + component.nrPortsLeft + component.nrPortsRight, this.length + 1))
          .collect(toList());
    }

    private int getStrength() {
      return strength;
    }

    public int getLength() {
      return length;
    }
  }
}
