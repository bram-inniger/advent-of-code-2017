package be.inniger.advent.util;

import static java.util.stream.Collectors.joining;

import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Printer {

  private static final Logger LOG = LoggerFactory.getLogger(Printer.class);

  private Printer() throws IllegalAccessException {
    throw new IllegalAccessException();
  }

  public static void prettyPrint(List<?> list, int position) {
    LOG.info(IntStream.range(0, list.size())
        .mapToObj(i -> new StringBuilder()
            .append(i == position ? '(' : ' ')
            .append(list.get(i))
            .append(i == position ? ')' : ' ')
            .append(' '))
        .map(StringBuilder::toString)
        .collect(joining(" ")));
  }
}
