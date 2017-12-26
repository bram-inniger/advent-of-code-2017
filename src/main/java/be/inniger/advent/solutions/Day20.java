package be.inniger.advent.solutions;

import static be.inniger.advent.util.Point3D.point3D;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import be.inniger.advent.DailyProblem;
import be.inniger.advent.util.Point3D;

public class Day20 implements DailyProblem<List<String>, Integer> {

  @Override
  public Integer solveFirst(List<String> inputs) {
    List<Particle> particles = IntStream.range(0, inputs.size())
        .mapToObj(i -> new Particle(i, inputs.get(i)))
        .collect(toList());

    // Assume the system is stable after 500 iterations
    for (int i = 0; i < 500; i++) {
      particles.forEach(Particle::move);
    }

    return particles.stream()
        .sorted(comparing(Particle::getManhattanDistanceToOrigin))
        .map(Particle::getNr)
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

  @Override
  public Integer solveSecond(List<String> inputs) {
    throw new UnsupportedOperationException();
  }

  private static class Particle {

    private static final Pattern PARTICLE_PATTERN = Pattern.compile("^p=<(?<position>.*?)>, v=<(?<velocity>.*?)>, a=<(?<acceleration>.*?)>$");
    private static final Pattern COMMA = Pattern.compile(",");

    private final int nr;
    private Point3D position;
    private Point3D velocity;
    private final Point3D acceleration;

    private Particle(int nr, String particle) {
      Matcher matcher = PARTICLE_PATTERN.matcher(particle);

      if (!matcher.matches()) {
        throw new IllegalArgumentException();
      }

      this.nr = nr;
      this.position = parsePoint3D(matcher.group("position"));
      this.velocity = parsePoint3D(matcher.group("velocity"));
      this.acceleration = parsePoint3D(matcher.group("acceleration"));
    }

    private Point3D parsePoint3D(String value) {
      List<Integer> values = COMMA.splitAsStream(value)
          .map(Integer::parseInt)
          .collect(toList());

      return point3D(values.get(0), values.get(1), values.get(2));
    }

    private void move() {
      velocity = point3D(velocity.getX() + acceleration.getX(), velocity.getY() + acceleration.getY(), velocity.getZ() + acceleration.getZ());
      position = point3D(position.getX() + velocity.getX(), position.getY() + velocity.getY(), position.getZ() + velocity.getZ());
    }

    private int getNr() {
      return nr;
    }

    private long getManhattanDistanceToOrigin() {
      return position.manhattanDistanceToOrigin();
    }
  }
}
