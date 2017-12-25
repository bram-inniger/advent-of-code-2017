package be.inniger.advent.solutions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

import be.inniger.advent.DailyProblem;

public class Day18 implements DailyProblem<List<String>, Long> {

  private static final Pattern PATTERN = Pattern.compile(" ");

  @Override
  public Long solveFirst(List<String> instructions) {
    TransferQueue<Long> rcvQueue = new LinkedTransferQueue<>();
    BlockingDeque<Long> sndQueue = new LinkedBlockingDeque<>();

    Program program = new Program(0, instructions, rcvQueue, sndQueue);

    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.submit(program);
    executor.shutdown();

    while (!rcvQueue.hasWaitingConsumer()) {
      try {
        TimeUnit.MILLISECONDS.sleep(10);
      }
      catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    executor.shutdownNow();
    try {
      executor.awaitTermination(10, TimeUnit.MILLISECONDS);
      return sndQueue.takeLast();
    }
    catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Long solveSecond(List<String> instructions) {
    TransferQueue<Long> program0Queue = new LinkedTransferQueue<>();
    TransferQueue<Long> program1Queue = new LinkedTransferQueue<>();

    Program program0 = new Program(0, instructions, program0Queue, program1Queue);
    Program program1 = new Program(1, instructions, program1Queue, program0Queue);

    ExecutorService executor = Executors.newFixedThreadPool(2);
    executor.submit(program0);
    executor.submit(program1);
    executor.shutdown();

    while (!program0Queue.hasWaitingConsumer() || !program1Queue.hasWaitingConsumer()) {
      try {
        TimeUnit.MILLISECONDS.sleep(10);
      }
      catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    executor.shutdownNow();
    try {
      executor.awaitTermination(10, TimeUnit.MILLISECONDS);
    }
    catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    return program1.sendCount;
  }

  private static class Program implements Runnable {

    private final Map<String, Long> registers = new HashMap<>();
    private final List<String> instructions;
    private final AtomicBoolean done = new AtomicBoolean(false);
    private final BlockingQueue<Long> inbox;
    private final BlockingQueue<Long> outbox;
    int instructionPointer = 0;
    long sendCount = 0;

    private Program(long programId,
                    List<String> instructions,
                    BlockingQueue<Long> inbox,
                    BlockingQueue<Long> outbox) {
      registers.put("p", programId);
      this.instructions = instructions;
      this.inbox = inbox;
      this.outbox = outbox;
    }

    @Override
    public void run() {
      while (!done.get()) {
        String[] instruction = PATTERN.split(instructions.get(instructionPointer++));

        switch (instruction[0]) {
          case "snd":
            sendCount++;
            try {
              outbox.put(readRegisterOrParseLong(instruction[1], registers));
            }
            catch (InterruptedException e) {
              throw new RuntimeException(e);
            }
            break;
          case "set":
            updateRegister(instruction, registers, (__, b) -> b);
            break;
          case "add":
            updateRegister(instruction, registers, (a, b) -> a + b);
            break;
          case "mul":
            updateRegister(instruction, registers, (a, b) -> a * b);
            break;
          case "mod":
            updateRegister(instruction, registers, (a, b) -> a % b);
            break;
          case "rcv":
            try {
              registers.put(instruction[1], inbox.take());
            }
            catch (InterruptedException ignore) {
              done.set(true);
            }
            break;
          case "jgz":
            if (readRegisterOrParseLong(instruction[1], registers) > 0) {
              instructionPointer = instructionPointer - 1 + readRegisterOrParseLong(instruction[2], registers).intValue();
            }
            break;
          default:
            throw new IllegalArgumentException("Unknown command!");
        }
      }
    }

    private Long readRegisterOrParseLong(String value, Map<String, Long> registers) {
      return Character.isLetter(value.charAt(0)) ?
          registers.getOrDefault(value, 0L) :
          Long.parseLong(value);
    }

    private void updateRegister(String[] instruction, Map<String, Long> registers, BiFunction<Long, Long, Long> operation) {
      registers.put(instruction[1], operation.apply(registers.getOrDefault(instruction[1], 0L), readRegisterOrParseLong(instruction[2], registers)));
    }
  }
}
