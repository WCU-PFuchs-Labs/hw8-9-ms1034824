
public class Const extends Unop { //extension of Unop
    /** the constant value */
    private double value;
    /**
     * @param d the value to set the constant to.
     */
    public Const(double d) { // sets that value equal to that double
        value = d;
    }
    /**
     * @return the value of the constant.
     */
    public double eval(double[] values) { //returns value
        return value;
    }
    public String toString(){ //returns value as a string
        return "" + value;
    }
}
