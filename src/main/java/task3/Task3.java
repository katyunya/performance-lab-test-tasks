package task3;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Task3 {
  private ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Write path to values file:");
    String valuesFile = scanner.nextLine();
    System.out.println("Write path to tests file:");
    String testsFile = scanner.nextLine();
    System.out.println("Write path to report file:");
    String reportFile = scanner.nextLine();
    Task3 task3 = new Task3();
    try {
      task3.writeReport(reportFile, task3.getFilledTests(task3.getValues(valuesFile), task3.getTests(testsFile)));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void writeReport(String reportFile, Tests tests) throws IOException {
    objectMapper.writeValue(new File(reportFile), tests);
  }

  public Tests getFilledTests(Values values, Tests tests) throws IOException {
    Map<Integer, String> valueMap = new HashMap<>();
    for (Value value : values.getValues()) {
      valueMap.put(value.getId(), value.getValue());
    }

    Queue<Test> testQueue = new ArrayDeque<>(tests.getTests());

    while (!testQueue.isEmpty()) {
      Test test = testQueue.poll();
      test.setValue(valueMap.get(test.getId()));
      if (test.getValues() != null) {
        testQueue.addAll(test.getValues());
      }
    }
    return tests;
  }

  public Values getValues(String valuesFile) throws IOException {
    return objectMapper.readValue(new File(valuesFile), Values.class);
  }

  public Tests getTests(String testsFile) throws IOException {
    return objectMapper.readValue(new File(testsFile), Tests.class);
  }
}
