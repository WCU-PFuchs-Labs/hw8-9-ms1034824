
 public class Divide extends Binop {
    public double eval(double left, double right) { //returns result of the left and right Consts
        if(Math.abs(right) < 0.0001){
            return 1.0;
        }else{
            return left/right;
        }
    }
    public String toString(){ //returns division sign
        return " / ";
    }
}
