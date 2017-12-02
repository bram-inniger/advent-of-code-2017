package be.inniger.advent.util;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
}
