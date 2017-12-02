package be.inniger.advent;

import static be.inniger.advent.util.InputFileReader.read;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.inniger.advent.days01to10.Day2;

public class Main {

  private static final Logger LOG = LoggerFactory.getLogger(Main.class);

  public static void main(String... args) {
    LOG.info(new Day2().solveFirst(read("02.txt")));
  }
}
