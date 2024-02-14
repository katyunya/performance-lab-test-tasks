package task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task2 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Write path to points file:");
    String pointsFile = scanner.nextLine();
    System.out.println("Write path to circle file:");
    String circleFile = scanner.nextLine();
    Task2 task2 = new Task2();
    try {
      task2.getPointPosition(task2.getPoints(pointsFile), task2.getCircle(circleFile));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void getPointPosition(List<Point> points, Circle circle) throws IOException {
    for (Point point : points) {
      double res = Math.sqrt(Math.pow((point.getX() - circle.getX()), 2) + Math.pow((point.getY() - circle.getY()), 2));
      if (res > circle.getR()) {
        System.out.println(2);
      }
      if (res == circle.getR()) {
        System.out.println(0);
      }
      if (res < circle.getR()) {
        System.out.println(1);
      }
    }
  }

  public Circle getCircle(String circleFile) throws IOException {
    try (BufferedReader circleReader = new BufferedReader(new FileReader(circleFile))) {
      List<String> circleCoordinates = circleReader.lines().collect(Collectors.toList());
      String[] circleXY = circleCoordinates.get(0).split(" ");
      return new Circle(Integer.parseInt(circleXY[0]), Integer.parseInt(circleXY[1]), Integer.parseInt(circleCoordinates.get(1)));
    }
  }

  public List<Point> getPoints(String pointsFile) throws IOException {
    try (BufferedReader pointsReader = new BufferedReader(new FileReader(pointsFile))) {
      return pointsReader.lines()
          .map(line -> line.split(" "))
          .map(array -> new Point(Integer.parseInt(array[0]), Integer.parseInt(array[1])))
          .collect(Collectors.toList());
    }
  }
}
