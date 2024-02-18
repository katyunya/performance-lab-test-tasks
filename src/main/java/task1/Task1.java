package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Task1 {

  public static void main(String[] args) {
    String n = args[0];
    String m = args[1];
    System.out.println(new Task1().findPath(Integer.parseInt(n), Integer.parseInt(m)));
  }

  public List<Integer> findPath(int n, int m) {
    List<List<Integer>> arraysArray = new ArrayList<>();
    List<Integer> array = new ArrayList<>();
    var i = 1;
    var j = 1;
    do {
      while (j <= m) {
        if (i == n + 1) {
          i = 1;
        }
        array.add(i);
        j++;
        i++;
      }
      i--;

      j = 1;
      arraysArray.add(new ArrayList<>(array));
      array.clear();
    } while (arraysArray.get(arraysArray.size() - 1).get(m - 1) != 1);
    return arraysArray.stream()
        .map(integers -> integers.get(0))
        .collect(Collectors.toList());
  }
}
