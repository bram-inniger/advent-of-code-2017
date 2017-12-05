package be.inniger.advent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DailyTest {

  protected final String FILE_NAME = getFileNameFromClassName();

  public abstract void validateFirstSampleInputs();

  public abstract void validateFirstSolution();

  public abstract void validateSecondSampleInputs();

  public abstract void validateSecondSolution();

  private String getFileNameFromClassName() {
    Matcher matcher = Pattern.compile("Day(.*?)Test")
        .matcher(getClass().getSimpleName());

    if (!matcher.matches()) {
      throw new IllegalArgumentException();
    }

    return matcher.group(1) + ".txt";
  }
}
