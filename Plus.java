
 public class Plus extends Binop {
     /**
      * @param left the left value
      * @param right the right value
      * @return the result of adding
      * left to right 
      */
    public double eval(double left, double right) { //returns result of the left and right Consts
        return left + right;
    }
    public String toString(){ //returns plus sign
        return " + ";
    }
}
