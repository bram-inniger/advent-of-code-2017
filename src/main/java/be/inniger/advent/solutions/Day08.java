package be.inniger.advent.solutions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.regex.Pattern;

import be.inniger.advent.DailyProblem;

public class Day08 implements DailyProblem<List<String>, Integer> {

  @Override
  public Integer solveFirst(List<String> input) {
    Map<String, Integer> registers = new HashMap<>();
    input.stream()
        .map(instruction -> Pattern.compile(" ").split(instruction))
        .forEach(instruction -> execute(instruction, registers));

    return registers.entrySet()
        .stream()
        .mapToInt(Map.Entry::getValue)
        .max()
        .orElseThrow(IllegalArgumentException::new);
  }

  @Override
  public Integer solveSecond(List<String> input) {
    throw new UnsupportedOperationException();
  }

  private void execute(String[] instruction, Map<String, Integer> registers) {
    String register = instruction[0];
    BinaryOperator<Integer> operation = getOperation(instruction[1]);
    int amount = Integer.parseInt(instruction[2]);
    int testRegister = registers.getOrDefault(instruction[4], 0);
    BiPredicate<Integer, Integer> test = getConditional(instruction[5]);
    int testAmount = Integer.parseInt(instruction[6]);

    if (test.test(testRegister, testAmount)) {
      int registerValue = registers.getOrDefault(register, 0);
      registers.put(register, operation.apply(registerValue, amount));
    }
  }

  private BinaryOperator<Integer> getOperation(String operation) {
    switch (operation) {
      case "inc":
        return (a, b) -> a + b;
      case "dec":
        return (a, b) -> a - b;
      default:
        throw new IllegalArgumentException();
    }
  }

  private BiPredicate<Integer, Integer> getConditional(String conditional) {
    switch (conditional) {
      case "==":
        return (a, b) -> a.equals(b);
      case "!=":
        return (a, b) -> !a.equals(b);
      case ">":
        return (a, b) -> a > b;
      case "<":
        return (a, b) -> a < b;
      case ">=":
        return (a, b) -> a >= b;
      case "<=":
        return (a, b) -> a <= b;
      default:
        throw new IllegalArgumentException();
    }
  }
}
