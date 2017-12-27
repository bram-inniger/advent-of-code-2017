package be.inniger.advent.solutions;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import be.inniger.advent.DailyProblem;

public class Day21 implements DailyProblem<List<String>, Integer> {

  private static final Pattern RULE = Pattern.compile("^(?<from>.*?) => (?<to>.*?)$");
  private static final Pattern SLASH = Pattern.compile("/");

  @Override
  public Integer solveFirst(List<String> inputs) {
    return solve(inputs, 5);
  }

  @Override
  public Integer solveSecond(List<String> inputs) {
    return solve(inputs, 18);
  }

  private int solve(List<String> inputs, int nrIterations) {
    Map<ArtPattern, ArtPattern> rules = parseRules(inputs);
    ArtPattern pattern = ArtPattern.getStartingPattern();

    for (int i = 0; i < nrIterations; i++) {
      pattern = ArtPattern.join(pattern.divide()
          .stream()
          .map(rules::get)
          .collect(toList()));
    }

    return pattern.getOnPixelsCount();
  }

  private Map<ArtPattern, ArtPattern> parseRules(List<String> rules) {
    Map<ArtPattern, ArtPattern> parsedRules = new HashMap<>();

    for (String rule : rules) {
      Matcher matcher = RULE.matcher(rule);

      if (!matcher.matches()) {
        throw new IllegalArgumentException();
      }

      ArtPattern from = parseRule(matcher.group("from"));
      ArtPattern to = parseRule(matcher.group("to"));

      for (int i = 0; i < 4; i++) {
        parsedRules.put(from, to);
        parsedRules.put(from.flip(), to);

        from = from.rotate();
      }
    }

    return parsedRules;
  }

  private ArtPattern parseRule(String rule) {
    String[] row = SLASH.split(rule);
    int size = row.length;

    char[][] pattern = new char[size][];
    for (int i = 0; i < size; i++) {
      pattern[i] = row[i].toCharArray();
    }

    return new ArtPattern(pattern);
  }

  private static class ArtPattern {

    private final char[][] pattern;

    private ArtPattern(char[][] pattern) {
      this.pattern = pattern;
    }

    private static ArtPattern getStartingPattern() {
      return new ArtPattern(new char[][] {
          { '.', '#', '.' },
          { '.', '.', '#' },
          { '#', '#', '#' }
      });
    }

    private ArtPattern rotate() {
      return transform(
          (__, j) -> j,
          (i, __) -> pattern.length - 1 - i);
    }

    private ArtPattern flip() {
      return transform(
          (i, __) -> pattern.length - 1 - i,
          (__, j) -> j);
    }

    private ArtPattern transform(BinaryOperator<Integer> rowOperator, BinaryOperator<Integer> colOperator) {
      int size = pattern.length;
      char[][] transformedPattern = new char[size][size];

      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
          transformedPattern[rowOperator.apply(i, j)][colOperator.apply(i, j)] = pattern[i][j];
        }
      }

      return new ArtPattern(transformedPattern);
    }

    private List<ArtPattern> divide() {
      List<ArtPattern> dividedPatterns = new ArrayList<>();
      int size = pattern.length;
      int dividedSize = size % 2 == 0 ? 2 : 3;

      for (int row = 0; row < size / dividedSize; row++) {
        for (int col = 0; col < size / dividedSize; col++) {
          dividedPatterns.add(extract(dividedSize, row, col));
        }
      }
      return dividedPatterns;
    }

    private ArtPattern extract(int size, int row, int col) {
      char[][] extractedPattern = new char[size][size];

      for (int i = 0; i < size; i++) {
        System.arraycopy(pattern[i + row * size], col * size, extractedPattern[i], 0, size);
      }

      return new ArtPattern(extractedPattern);
    }

    private static ArtPattern join(List<ArtPattern> patterns) {
      int nrPatternsPerRow = (int) Math.sqrt(patterns.size());
      int size = nrPatternsPerRow * patterns.get(0).pattern.length;
      char[][] joinedPattern = new char[size][size];

      for (int i = 0; i < patterns.size(); i++) {
        fillPattern(joinedPattern, patterns.get(i).pattern, i / nrPatternsPerRow, i % nrPatternsPerRow);
      }

      return new ArtPattern(joinedPattern);
    }

    private static void fillPattern(char[][] fullPattern, char[][] filler, int row, int col) {
      int fillerSize = filler.length;

      for (int i = 0; i < fillerSize; i++) {
        System.arraycopy(filler[i], 0, fullPattern[i + fillerSize * row], fillerSize * col, fillerSize);
      }
    }

    private int getOnPixelsCount() {
      int count = 0;

      for (char[] row : pattern) {
        for (char pixel : row) {
          if (pixel == '#') {
            count++;
          }
        }
      }

      return count;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }

      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      ArtPattern pattern1 = (ArtPattern) o;

      return new EqualsBuilder()
          .append(pattern, pattern1.pattern)
          .isEquals();
    }

    @Override
    public int hashCode() {
      return new HashCodeBuilder(17, 37)
          .append(pattern)
          .toHashCode();
    }
  }
}
