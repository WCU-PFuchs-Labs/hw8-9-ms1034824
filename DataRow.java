

public class DataRow {
    double y;
    double[]x;
    

    /**
     * @param y the dependent variable
     * @param x the array of independent variables
     */
    public DataRow(double y, double[] x){ 
        this.y = y; //sets data within this method to be accessible to all methods
        this.x = x;
    }

    /**
     * @return the dependent variable
     */
    public double getDependentVariable() {
        return y; 
    }

    /**
     * @return the array of independent variables
     */
    public double[] getIndependentVariables() {
        return x;
    }
}
