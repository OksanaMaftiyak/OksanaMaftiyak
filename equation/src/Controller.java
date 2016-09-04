import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Controller {

    private Map<String, Equation> type = new HashMap<>();
    private Scanner scan = new Scanner(System.in);

    public Controller() {
        type.put("1", new LinearEquation());
        type.put("2", new SquareEquation());
        type.put("3", new SinCosEquation());
    }

    public void start() {
        do {
            Equation equation = promptEquation();
            double[] parameters = promptParameters(equation);
            equation.setParameters(parameters);
            System.out.println("The roots of equation: " + rootsToString(equation));
            System.out.print("If you want to continue, enter (y): ");
        } while ("y".equals(scan.next()));
    }

    private double[] promptParameters(Equation equation) {
        double[] parameters = new double[equation.getParametersCount()];
        System.out.println("Enter parameters for the type of equation " + equation.getEquationType() + " : ");
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = scan.nextDouble();
        }
        return parameters;
    }

    private Equation promptEquation() {
        System.out.println("Linear equation (enter 1)");
        System.out.println("Square equation (enter 2)");
        System.out.println("Sin-Cos equation (enter 3)");
        System.out.print("Select type equation: ");
        return type.get(scan.next());
    }

    private String rootsToString(Equation equation) {
        StringBuilder sb = new StringBuilder();
        for (double v : equation.getRoots()) {
            sb.append(v);
            sb.append(", ");
        }
        return sb.toString();
    }

}
