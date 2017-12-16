package be.inniger.advent.solutions;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import be.inniger.advent.DailyProblem;
import be.inniger.advent.util.KnotHash;

public class Day14 implements DailyProblem<String, Integer> {

  private static final Map<Integer, Integer> oneBits = getOneBits();

  @Override
  public Integer solveFirst(String input) {
    return IntStream.range(0, 128)
        .mapToObj(i -> input + "-" + i)
        .map(KnotHash::hash)
        .flatMapToInt(String::chars)
        .map(oneBits::get)
        .sum();
  }

  @Override
  public Integer solveSecond(String input) {
    throw new UnsupportedOperationException();
  }

  // TODO - Auto-create map using Streams
  private static Map<Integer, Integer> getOneBits() {
    Map<Integer, Integer> oneBits = new HashMap<>();

    oneBits.put((int) '0', 0); // 0000
    oneBits.put((int) '1', 1); // 0001
    oneBits.put((int) '2', 1); // 0010
    oneBits.put((int) '3', 2); // 0011
    oneBits.put((int) '4', 1); // 0100
    oneBits.put((int) '5', 2); // 0101
    oneBits.put((int) '6', 2); // 0110
    oneBits.put((int) '7', 3); // 0111
    oneBits.put((int) '8', 1); // 1000
    oneBits.put((int) '9', 2); // 1001
    oneBits.put((int) 'a', 2); // 1010
    oneBits.put((int) 'b', 3); // 1011
    oneBits.put((int) 'c', 2); // 1100
    oneBits.put((int) 'd', 3); // 1101
    oneBits.put((int) 'e', 3); // 1110
    oneBits.put((int) 'f', 4); // 1111

    return oneBits;
  }
}
