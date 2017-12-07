package be.inniger.advent.solutions;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.regex.Pattern;

import be.inniger.advent.DailyProblem;

public class Day02 implements DailyProblem<List<String>, Integer> {

  @Override
  public Integer solveFirst(List<String> inputs) {
    return solve(inputs, this::maxMinDifference);
  }

  @Override
  public Integer solveSecond(List<String> inputs) {
    return solve(inputs, this::evenDivisorDivision);
  }

  private int solve(List<String> inputs, Function<List<Integer>, Integer> mapper) {
    return inputs.stream()
        .map(this::tokenize)
        .map(mapper)
        .reduce(Integer::sum)
        .orElseThrow(IllegalArgumentException::new);
  }

  private List<Integer> tokenize(String line) {
    return Pattern.compile("\\s+")
        .splitAsStream(line)
        .map(Integer::parseInt)
        .collect(toList());
  }

  private int maxMinDifference(List<Integer> values) {
    return reduce(values, Math::max) -
        reduce(values, Math::min);
  }

  private int reduce(List<Integer> values, IntBinaryOperator reducer) {
    return values.stream()
        .mapToInt(Integer::intValue)
        .reduce(reducer)
        .orElseThrow(IllegalArgumentException::new);
  }

  private int evenDivisorDivision(List<Integer> values) {
    return values.stream()
        .map(value -> divideByEvenDivisor(value, values))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .findAny()
        .orElseThrow(IllegalArgumentException::new);
  }

  private Optional<Integer> divideByEvenDivisor(int dividend, List<Integer> divisors) {
    return divisors.stream()
        .filter(divisor -> dividesEvenly(dividend, divisor))
        .map(divisor -> dividend / divisor)
        .findAny();
  }

  private boolean dividesEvenly(int dividend, int divisor) {
    return dividend != divisor &&
        (dividend / divisor) * divisor == dividend;
  }
}
