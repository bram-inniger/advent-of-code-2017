package be.inniger.advent.solutions;

import static java.lang.Integer.parseInt;
import static java.util.Map.Entry;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import be.inniger.advent.DailyProblem;

public class Day16 implements DailyProblem<List<String>, String> {

  @Override
  public String solveFirst(List<String> inputs) {
    Map<Character, Integer> progToPos = IntStream.rangeClosed('a', 'p')
        .boxed()
        .collect(toMap(
            program -> (char) program.intValue(),
            program -> program - 'a'));

    inputs.forEach(danceMove -> performMove(danceMove, progToPos));

    return progToPos.entrySet()
        .stream()
        .sorted(comparingByValue())
        .map(Entry::getKey)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }

  @Override
  public String solveSecond(List<String> inputs) {
    throw new UnsupportedOperationException();
  }

  private void performMove(String danceMove, Map<Character, Integer> progToPos) {
    switch (danceMove.charAt(0)) {
      case 's':
        spin(progToPos, parseInt(danceMove.substring(1)));
        break;
      case 'x':
        String[] positions = Pattern.compile("/").split(danceMove.substring(1));
        exchange(progToPos, parseInt(positions[0]), parseInt(positions[1]));
        break;
      case 'p':
        partner(progToPos, danceMove.charAt(1), danceMove.charAt(3));
        break;
      default:
        throw new IllegalArgumentException("Command not found!");
    }
  }

  private void spin(Map<Character, Integer> progToPos, int nrPrograms) {
    for (char program : progToPos.keySet()) {
      progToPos.put(program, (progToPos.get(program) + nrPrograms) % progToPos.size());
    }
  }

  private void exchange(Map<Character, Integer> progToPos, int pos1, int pos2) {
    char prog1 = findProgramAtPosition(progToPos, pos1);
    char prog2 = findProgramAtPosition(progToPos, pos2);

    partner(progToPos, prog1, prog2);
  }

  private void partner(Map<Character, Integer> progToPos, char prog1, char prog2) {
    int tmp = progToPos.get(prog1);
    progToPos.put(prog1, progToPos.get(prog2));
    progToPos.put(prog2, tmp);
  }

  private char findProgramAtPosition(Map<Character, Integer> progToPos, int position) {
    return progToPos.entrySet()
        .stream()
        .filter(entry -> entry.getValue() == position)
        .map(Entry::getKey)
        .findAny()
        .orElseThrow(IllegalArgumentException::new);
  }
}