/**
 * ax + b = 0
 */
public class LinearEquation implements Equation {

    private double a;
    private double b;
    private double x;

    public LinearEquation() {
        this(0, 0);
    }

    public LinearEquation(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String getEquationType() {
        return "ax + b = 0";
    }

    @Override
    public int getParametersCount() {
        return 2;
    }

    @Override
    public void setParameters(double[] parameters) {
        a = parameters[0];
        b = parameters[1];
    }

    @Override
    public double[] getRoots() {
        calculateRoot();
        return new double[]{x};
    }

    private void calculateRoot() {
        x = -b / a;
    }

}
