package be.inniger.advent.solutions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import be.inniger.advent.DailyProblem;

public class Day23 implements DailyProblem<List<String>, Integer> {

  private static final Pattern SPACE = Pattern.compile(" ");

  @Override
  public Integer solveFirst(List<String> instructions) {
    Map<String, Integer> registers = new HashMap<>();
    int mulCount = 0;
    int instructionPointer = 0;

    while (instructionPointer >= 0 && instructionPointer < instructions.size()) {
      String[] splitInstruction = SPACE.split(instructions.get(instructionPointer));
      String command = splitInstruction[0];

      if ("mul".equals(command)) {
        mulCount++;
      }

      instructionPointer += performInstruction(command, splitInstruction[1], splitInstruction[2], registers);
    }

    return mulCount;
  }

  @Override
  public Integer solveSecond(List<String> instructions) {
    throw new UnsupportedOperationException();
  }

  private int performInstruction(String instruction, String operand1, String operand2, Map<String, Integer> registers) {
    switch (instruction) {
      case "set":
        registers.put(operand1, readRegisterOrParseLong(operand2, registers));
        break;
      case "sub":
        registers.put(operand1, readRegisterOrParseLong(operand1, registers) - readRegisterOrParseLong(operand2, registers));
        break;
      case "mul":
        registers.put(operand1, readRegisterOrParseLong(operand1, registers) * readRegisterOrParseLong(operand2, registers));
        break;
      case "jnz":
        if (readRegisterOrParseLong(operand1, registers) != 0) {
          return readRegisterOrParseLong(operand2, registers); // Changes the instructionPointer to jump instead of just iterate
        }

        break;
      default:
        throw new IllegalArgumentException();
    }

    return 1;
  }

  private Integer readRegisterOrParseLong(String value, Map<String, Integer> registers) {
    return Character.isLetter(value.charAt(0)) ?
        registers.getOrDefault(value, 0) :
        Integer.parseInt(value);
  }
}
