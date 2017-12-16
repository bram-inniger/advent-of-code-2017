package be.inniger.advent.util;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.IntStream;

public final class KnotHash {

  private KnotHash() throws IllegalAccessException {
    throw new IllegalAccessException();
  }

  public static String hash(String toHash) {
    List<Integer> asciiCodes = toHash.chars()
        .boxed()
        .collect(toList());
    asciiCodes.addAll(asList(17, 31, 73, 47, 23));

    Hash sparseHash = new Hash(asciiCodes);
    IntStream.range(0, 64).forEach(__ -> sparseHash.performHashingRound());

    return IntStream.range(0, sparseHash.list.size() / 16)
        .map(i -> IntStream.range(16 * i, 16 * i + 16)
            .map(sparseHash.list::get)
            .reduce(0, (a, b) -> a ^ b))
        .mapToObj(value -> String.format("%02x", value))
        .collect(joining());
  }

  public static class Hash {

    private static final int LIST_LENGTH = 256;
    private final List<Integer> lengths;
    private final List<Integer> list;
    private int currentPosition;
    private int skipSize;

    public Hash(List<Integer> lengths) {
      this.lengths = lengths;
      this.list = IntStream.range(0, LIST_LENGTH)
          .boxed()
          .collect(toList());
      this.currentPosition = 0;
      this.skipSize = 0;
    }

    public List<Integer> getList() {
      return list;
    }

    public void performHashingRound() {
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
