package be.inniger.advent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  private static final Logger LOG = LoggerFactory.getLogger(Main.class);

  public static void main(String... args) {
    LOG.info("ABOUT:");
    LOG.info("");
    LOG.info("Every problem is described in \"src/main/resources/problems\".");
    LOG.info("Every input is given in \"src/main/resources/inputs\".");
    LOG.info("The actual problem solutions (both parts) can be found under \"src/main/java/be/inniger/advent/daysXXtoYY\".");
    LOG.info("Tests verifying all solutions (and sample inputs) can be found under \"src/test/java/be/inniger/advent/daysXXtoYY\".");
  }
}
