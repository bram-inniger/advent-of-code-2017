package be.inniger.advent.solutions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import be.inniger.advent.DailyProblem;

public class Day18 implements DailyProblem<List<String>, Long> {

  private static final Pattern PATTERN = Pattern.compile(" ");

  @Override
  public Long solveFirst(List<String> instructions) {
    Map<String, Long> registers = new HashMap<>();
    boolean done = false;
    long lastPlayed = 0;
    int instructionPointer = 0;

    while (!done) {
      String[] instruction = PATTERN.split(instructions.get(instructionPointer++));

      switch (instruction[0]) {
        case "snd":
          lastPlayed = readRegisterOrParseInt(instruction[1], registers);
          break;
        case "set":
          registers.put(instruction[1], readRegisterOrParseInt(instruction[2], registers));
          break;
        case "add":
          registers.put(instruction[1],
              registers.getOrDefault(instruction[1], 0L) + readRegisterOrParseInt(instruction[2], registers));
          break;
        case "mul":
          registers.put(instruction[1],
              registers.getOrDefault(instruction[1], 0L) * readRegisterOrParseInt(instruction[2], registers));
          break;
        case "mod":
          registers.put(instruction[1],
              registers.getOrDefault(instruction[1], 0L) % readRegisterOrParseInt(instruction[2], registers));
          break;
        case "rcv":
          if (readRegisterOrParseInt(instruction[1], registers) > 0) {
            done = true;
          }
          break;
        case "jgz":
          if (readRegisterOrParseInt(instruction[1], registers) > 0) {
            instructionPointer = instructionPointer - 1 + (int) readRegisterOrParseInt(instruction[2], registers);
          }
          break;
        default:
          throw new IllegalArgumentException("Unknown command!");
      }
    }

    return lastPlayed;
  }

  @Override
  public Long solveSecond(List<String> instructions) {
    throw new UnsupportedOperationException();
  }

  private long readRegisterOrParseInt(String value, Map<String, Long> registers) {
    return Character.isLetter(value.charAt(0)) ?
        registers.getOrDefault(value, 0L) :
        Long.parseLong(value);
  }
}
