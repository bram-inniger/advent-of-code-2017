package be.inniger.advent;

public abstract class DailyTest {

  // FIXME - Simplify
  protected final String FILE_NAME = getClass().getSimpleName().replaceAll("Day(\\w*?)Test", "$1") + ".txt";

}
