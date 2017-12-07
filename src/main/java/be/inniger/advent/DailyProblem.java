package be.inniger.advent;

public interface DailyProblem<I, R> {

  R solveFirst(I input);

  R solveSecond(I input);
}
