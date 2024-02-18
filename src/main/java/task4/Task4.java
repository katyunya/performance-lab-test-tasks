package task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Task4 {
  public static void main(String[] args) {
    String file = args[0];
    try {
      System.out.println(new Task4().getStepsCount(file));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Integer getStepsCount(String file) throws IOException {
    int stepsCount = 0;
    List<Integer> array = getArray(file);
    Collections.sort(array);
    int middleElement = array.get(array.size() / 2);
    for (Integer element : array) {
      if (element < middleElement) {
        stepsCount += middleElement - element;
      }
      if (element > middleElement) {
        stepsCount += element - middleElement;
      }
    }
    return stepsCount;
  }

  public List<Integer> getArray(String file) throws IOException {
    try (BufferedReader arrayReader = new BufferedReader(new FileReader(file))) {
      return arrayReader.lines()
          .map(Integer::parseInt)
          .collect(Collectors.toList());
    }
  }
}
