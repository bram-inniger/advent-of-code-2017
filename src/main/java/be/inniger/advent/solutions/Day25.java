package be.inniger.advent.solutions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import be.inniger.advent.DailyProblem;

public class Day25 implements DailyProblem<List<Void>, Long> {

  @Override
  public Long solveFirst(List<Void> ignored) {
    TuringMachine turingMachine = new TuringMachine();

    while (turingMachine.stepCount != 12173597) {
      turingMachine.performStep();
    }

    return turingMachine.getDiagnosticCheckSum();
  }

  @Override
  public Long solveSecond(List<Void> ignored) {
    throw new UnsupportedOperationException();
  }

  private static class TuringMachine {

    private final Map<Integer, Boolean> tape;
    private int position;
    private State state;
    private int stepCount;

    private TuringMachine() {
      this.tape = new HashMap<>();
      this.position = 0;
      this.state = State.A;
      this.stepCount = 0;
    }

    private void performStep() {
      boolean currentValue = tape.getOrDefault(position, false);

      tape.put(position, state.changer.valueChanger.apply(currentValue));
      position += state.changer.positionChanger.apply(currentValue);
      state = state.changer.stateChanger.apply(currentValue);

      stepCount++;
    }

    private long getDiagnosticCheckSum() {
      return tape.values().stream()
          .filter(value -> value)
          .count();
    }

    // State and Changer cannot exist in the same enum, as it would give "illegal forward access" compile errors
    private enum State {
      A(Changer.A),
      B(Changer.B),
      C(Changer.C),
      D(Changer.D),
      E(Changer.E),
      F(Changer.F);

      private final Changer changer;

      State(Changer changer) {
        this.changer = changer;
      }
    }

    private enum Changer {
      A(val -> !val, val -> val ? -1 : 1, val -> val ? State.C : State.B),
      B(__ -> true, val -> val ? 1 : -1, val -> val ? State.D : State.A),
      C(val -> !val, val -> val ? -1 : 1, val -> val ? State.E : State.A),
      D(val -> !val, __ -> 1, val -> val ? State.B : State.A),
      E(__ -> true, __ -> -1, val -> val ? State.C : State.F),
      F(__ -> true, __ -> 1, val -> val ? State.A : State.D);

      private final Function<Boolean, Boolean> valueChanger;
      private final Function<Boolean, Integer> positionChanger;
      private final Function<Boolean, State> stateChanger;

      Changer(Function<Boolean, Boolean> valueChanger,
              Function<Boolean, Integer> positionChanger,
              Function<Boolean, State> stateChanger) {
        this.valueChanger = valueChanger;
        this.positionChanger = positionChanger;
        this.stateChanger = stateChanger;
      }
    }
  }
}
