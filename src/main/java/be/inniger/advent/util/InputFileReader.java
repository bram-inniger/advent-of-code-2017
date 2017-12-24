package be.inniger.advent.util;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

public final class InputFileReader {

  private static final Path INPUTS = Paths.get("src", "main", "resources", "inputs");

  private InputFileReader() throws IllegalAccessException {
    throw new IllegalAccessException();
  }

  public static List<String> readMultiLine(String fileName) {
    return readMultiLine(fileName, identity());
  }

  public static <T> List<T> readMultiLine(String fileName, Function<String, T> mapper) {
    try {
      return Files.readAllLines(INPUTS.resolve(fileName), UTF_8)
          .stream()
          .map(mapper)
          .collect(toList());
    }
    catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static String readSingleLine(String fileName) {
    return readSingleLine(fileName, identity());
  }

  public static <T> T readSingleLine(String fileName, Function<String, T> mapper) {
    return readMultiLine(fileName, mapper).get(0);
  }

  public static List<String> readSingleLine(String fileName, Pattern pattern) {
    return readSingleLine(fileName, pattern, identity());
  }

  public static <T> List<T> readSingleLine(String fileName, Pattern pattern, Function<String, T> mapper) {
    return pattern.splitAsStream(readSingleLine(fileName))
        .map(mapper)
        .collect(toList());
  }
}
