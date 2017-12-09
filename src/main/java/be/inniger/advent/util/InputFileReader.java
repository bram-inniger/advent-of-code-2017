package be.inniger.advent.util;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

public final class InputFileReader {

  private static final Path INPUTS = Paths.get("src", "main", "resources", "inputs");

  private InputFileReader() {
    // Static utility class
  }

  public static List<String> read(String fileName) {
    try {
      return Files.readAllLines(INPUTS.resolve(fileName), UTF_8);
    }
    catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static String readLine(String fileName) {
    return read(fileName).get(0);
  }

  public static List<Integer> readMultiLineInts(String fileName) {
    return read(fileName).stream()
        .map(Integer::parseInt)
        .collect(toList());
  }

  public static List<Integer> readSameLineInts(String fileName) {
    return Pattern.compile("\\s+")
        .splitAsStream(readLine(fileName))
        .map(Integer::parseInt)
        .collect(toList());
  }
}
