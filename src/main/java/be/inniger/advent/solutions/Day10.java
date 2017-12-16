package be.inniger.advent.solutions;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import be.inniger.advent.DailyProblem;

public class Day10 implements DailyProblem<String, String> {

  private int listLength;

  Day10() {
    this(256);
  }

  Day10(int listLength) {
    this.listLength = listLength;
  }

  @Override
  public String solveFirst(String input) {
    List<Integer> lengths = Pattern.compile("\\W+")
        .splitAsStream(input)
        .map(Integer::parseInt)
        .collect(toList());

    Hash hash = new Hash(lengths, listLength);
    hash.performHashingRound();

    return String.format("%d", hash.list.get(0) * hash.list.get(1));
  }

  @Override
  public String solveSecond(String input) {
    List<Integer> asciiCodes = input.chars()
        .boxed()
        .collect(toList());
    asciiCodes.addAll(asList(17, 31, 73, 47, 23));

    Hash sparseHash = new Hash(asciiCodes, listLength);
    IntStream.range(0, 64).forEach(__ -> sparseHash.performHashingRound());

    return IntStream.range(0, sparseHash.list.size() / 16)
        .map(i -> IntStream.range(16 * i, 16 * i + 16)
            .map(sparseHash.list::get)
            .reduce(0, (a, b) -> a ^ b))
        .mapToObj(value -> String.format("%02x", value))
        .collect(joining());
  }

  private static class Hash {

    private final List<Integer> lengths;
    private final List<Integer> list;
    private int currentPosition;
    private int skipSize;

    private Hash(List<Integer> lengths, int listLength) {
      this.lengths = lengths;
      this.list = IntStream.range(0, listLength)
          .boxed()
          .collect(toList());
      this.currentPosition = 0;
      this.skipSize = 0;
    }

    private void performHashingRound() {
      for (int inputLength : lengths) {
        reverse(inputLength);
        currentPosition = (currentPosition + inputLength + skipSize) % list.size();
        skipSize++;
      }
    }

    private void reverse(int length) {
      for (int i = 0; i < length / 2; i++) {
        int aPos = (currentPosition + i) % list.size();
        int bPos = (currentPosition + length - i - 1) % list.size();

        int tmp = list.get(aPos);
        list.set(aPos, list.get(bPos));
        list.set(bPos, tmp);
      }
    }
  }
}
