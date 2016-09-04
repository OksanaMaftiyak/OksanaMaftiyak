/**
 * a*sin(b*x)+c*cos(b*x)=0
 */
public class SinCosEquation implements Equation {
    double a;
    double b;
    double c;
    double x;

    public SinCosEquation() {
        this(0, 0, 0);
    }

    public SinCosEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String getEquationType() {
        return "a*sin(b*x)+c*cos(b*x)=0";
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
        return new double[]{x};
    }

    private void calculateRoot() {
        x = Math.atan(-c / a) / b;
    }
}
