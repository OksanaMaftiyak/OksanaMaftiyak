/**
 * ax^2 + bx + c = 0
 */
public class SquareEquation implements Equation {

    private double a;
    private double b;
    private double c;
    private Double x1;
    private Double x2;

    public SquareEquation() {
        this(0, 0, 0);
    }

    public SquareEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String getEquationType() {
        return "ax^2 + bx + c = 0";
    }

    @Override
    public int getParametersCount() {
        return 3;
    }

    @Override
    public void setParameters(double[] parameters) {
        a = parameters[0];
        b = parameters[1];
        c = parameters[2];
    }

    @Override
    public double[] getRoots() {
        calculateRoot();
        return new double[]{x1, x2};
    }

    private void calculateRoot() {
        double d = b * b - 4 * a * c;
        if (d < 0) {
            x1 = null;
            x2 = null;
        } else if (d == 0) {
            x1 = -b / 2 * a;
            x2 = x1;
        } else {
            x1 = (-b - Math.sqrt(d)) / 2 * a;
            x2 = (-b + Math.sqrt(d)) / 2 * a;
        }
    }
}
