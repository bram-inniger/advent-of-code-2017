package be.inniger.advent.solutions;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.regex.Pattern;

import be.inniger.advent.DailyProblem;
import be.inniger.advent.util.KnotHash;

public class Day10 implements DailyProblem<String, String> {

  @Override
  public String solveFirst(String input) {
    List<Integer> lengths = Pattern.compile("\\W+")
        .splitAsStream(input)
        .map(Integer::parseInt)
        .collect(toList());

    KnotHash.Hash hash = new KnotHash.Hash(lengths);
    hash.performHashingRound();

    return String.format("%d", hash.getList().get(0) * hash.getList().get(1));
  }

  @Override
  public String solveSecond(String input) {
    return KnotHash.hash(input);
  }
}
