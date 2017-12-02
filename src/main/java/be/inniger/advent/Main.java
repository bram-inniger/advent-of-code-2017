package be.inniger.advent;

import java.nio.file.Path;
import java.nio.file.Paths;

import be.inniger.advent.days01to10.Day1;

public class Main {

  private static final Path INPUTS = Paths.get("src", "main", "resources", "inputs");

  public static void main(String... args) {
    new Day1().solveFirst(INPUTS.resolve("01.txt"));
  }
}
