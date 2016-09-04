

public interface Equation {

    String getEquationType();

    int getParametersCount();

    void setParameters(double[] parameters);

    double[] getRoots();

}
