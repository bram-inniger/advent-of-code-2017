package be.inniger.advent.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DailyProblem {

  private static final Logger LOG = LoggerFactory.getLogger(DailyProblem.class);


  public abstract String solveFirst(String input);

  public abstract String solveSecond(String input);

  public void solveFirst(Path inputPath) {
    solve(inputPath, this::solveFirst);
  }

  public void solveSecond(Path inputPath) {
    solve(inputPath, this::solveSecond);
  }

  private void solve(Path inputPath, Function<String, String> solver) {
    try {
      Files.readAllLines(inputPath).stream()
          .map(solver)
          .forEach(LOG::info);
    }
    catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
