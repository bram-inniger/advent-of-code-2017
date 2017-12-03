package be.inniger.advent;

public interface DailyProblem<T> {

  int solveFirst(T input);

  int solveSecond(T input);
}
