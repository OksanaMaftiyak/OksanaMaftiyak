package lab1;


import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;
import static lab1.Variant20.Point;

public class Controller {

    Scanner scan = new Scanner(in);
    Variant20 lab1 = new Variant20();

    public void doInputOutputTask() {
        Point pointA = new Point(0,0);
        Point pointB = new Point(0,0);
        out.println("Enter point for 'InputTask': ");
        pointA.setX(scan.nextDouble());
        pointA.setY(scan.nextDouble());
        pointB.setX(scan.nextDouble());
        pointB.setY(scan.nextDouble());
        out.println("Distance between points:  " + lab1.inputOutputTask(pointA, pointB));
    }

    public void doIntegerNumbersTask() {
        out.println("Enter the number of seconds: ");
        out.println("The number of hours: " + lab1.integerNumbersTask(scan.nextInt()));
    }

    public void doIfTask() {

        out.println("Enter coordinate of points A, B, C: ");
        Point point = lab1.ifTask(scan.nextDouble(), scan.nextDouble(), scan.nextDouble());
        out.println("Shortest distance to point A:"
                + point.getY() + "Nearest point:" + point.getX());
    }

    public void doBooleanTask() {
        out.println("Enter three-digit for 'BooleanTask': ");
        if (lab1.booleanTask(scan.nextInt())) {
            out.println("Numbers are different.");
        } else {
            out.println("Numbers aren't different.");
        }
    }

    public void doForTask() {
        out.println("Enter value N for 'forTask': ");
        out.println("Result: " + lab1.forTask(scan.nextInt()) + ".");
    }

    public void doSwitchTask() {
        System.out.println("Enter the date of birth: ");
        System.out.println("Zodiaс: " + lab1.switchTask(scan.nextInt(), scan.nextInt()).name());
    }

    public void doWhileTask() {
        out.println("Enter value N for 'whileTask': ");
        if (lab1.whileTask(scan.nextInt())) {
            out.println("Entering the number has '2'.");
        } else {
            out.println("Entering the number hasn't '2'.");
        }
    }

    public void doArrayTask() {
        out.println("Enter size of array: ");
        double[] array = new double[scan.nextInt()];
        out.println("Enter elements for array: ");
        for (int i = 0; i < array.length; i++) {
            array[i] = scan.nextDouble();
        }
        out.println("Array has " + lab1.arrayTask(array) + " tracts monotony.");

    }

    public void doTwoDimensionArrayTask() {
        out.println("Enter size of array(N, M): ");
        int[][] twoDimensionArray = new int[scan.nextInt()][scan.nextInt()];
        out.println("Enter elements of array: ");
        for (int i = 0; i < twoDimensionArray.length; i++) {
            for (int j = 0; j < twoDimensionArray[i].length; j++) {
                twoDimensionArray[i][j] = scan.nextInt();
            }
        }
        twoDimensionArray = lab1.twoDimensionArrayTask(twoDimensionArray);
        out.println("Result:");
        for (int[] intArray : twoDimensionArray) {
            for (int i : intArray) {
                out.print(i + "     ");
            }
            out.println();
        }
    }

    public void work() {
        do {
            System.out.println(
                    "1. doInputOutputTask\n" +
                            "2. doIntegerNumbersTask\n" +
                            "3. doIfTask\n" +
                            "4. doSwitchTask\n" +
                            "5. doBooleanTask\n" +
                            "6. doForTask\n" +
                            "7. doWhileTask\n" +
                            "8. doArrayTask\n" +
                            "9. doTwoDimensionArrayTask\n"
            );
            System.out.print("Choose action (number of task, that you want to perform): ");
            switch (scan.nextInt()) {
                case 1:
                    doInputOutputTask();
                    break;
                case 2:
                    doIntegerNumbersTask();
                    break;
                case 3:
                    doIfTask();
                    break;
                case 4:
                    doSwitchTask();
                    break;
                case 5:
                    doBooleanTask();
                    break;
                case 6:
                    doForTask();
                    break;
                case 7:
                    doWhileTask();
                    break;
                case 8:
                    doArrayTask();
                    break;
                case 9:
                    doTwoDimensionArrayTask();
                    break;
                default:
                    System.out.println("Invalid value...");
                    break;
            }
            System.out.println("Enter 'y' if you want continue ...");
        } while (Objects.equals(scan.next(), "y"));
    }

    public static void main(String... strings) {
        out.println("Start of zero lab");
        Controller controller = new Controller();
        controller.work();
        out.println("Done!!!");
    }
}
