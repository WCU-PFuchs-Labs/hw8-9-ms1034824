import java.util.Random;

public class Node {
    private Node left; 
    private Node right;
    private Op operation;
    protected int depth;

    public Node(Unop operation) {
        this.operation = operation; //makes operation accessible to the Node where the operation is passed
    }
    public Node(Binop operation) {
        this.operation = operation;
    }
    public Node(Binop operation,Node left, Node right) { //makes operation and left and right consts accessible
        this.left = left;
        this.right = right;
        this.operation = operation;
    }
    public double eval(double[] values) {
        if (operation instanceof Unop) { //returns operation if it's a Unop
              return ((Unop)operation).eval(values);
        } else if (operation instanceof Binop) { //returns operation if it's a Binop
              return ((Binop)operation).eval(left.eval(values), right.eval(values));
        } else {
              System.err.println("Error operation is not a Unop or a Binop!"); //if neither Binop or unop
              return 0.0;
        }
    }
    public void traverse(Collector c) {
        if (this.operation instanceof Binop) {
            c.collect(this); 
        }
        if (left != null) {
            left.traverse(c);  
        }
        if (right != null) {
            right.traverse(c); 
        }
    }  
    public void swapLeft (Node trunk){
        this.left = trunk.left;
    }
    public void swapRight (Node trunk){
        this.right = trunk.right;
    }
    public boolean isLeaf() {
        if(operation instanceof Unop){
            return true;
        }else{
            return false;
        }
    }
    public Object clone() {
    Object o = null;
    try {
        o =  super.clone();
    }
    catch(CloneNotSupportedException e) {
        System.out.println("Op can't clone.");
    }
    Node b = (Node) o;
    if(left != null) {
        b.left = (Node) left.clone();
    }
    if(right != null) {
       b.right = (Node) right.clone();
    }
    if(operation != null) {
       b.operation = (Op) operation.clone();
    }
    return o;
  }
    public void addRandomKids(NodeFactory nf, int maxDepth, Random rand) { 
        if (this.depth >= maxDepth) {
            return; 
        }
    this.left = nf.getTerminal(rand);
    this.right = nf.getTerminal(rand);
    }
    public String toString(){ //prints string instances
        if (operation instanceof Unop){
            return operation.toString();
        }else if(operation instanceof Binop){
            return "(" + left.toString() + operation.toString() + right.toString() + ")";
        }else{
            System.err.println("Not Unop or Binop!");
            return "Err";
        }
    }
}
