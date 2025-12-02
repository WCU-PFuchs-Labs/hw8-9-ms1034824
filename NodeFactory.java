import java.util.Random;

public class NodeFactory{
    private Binop[] currentOps;
    public int numIndepVars;
    public int numOperators;

    public NodeFactory(Binop[] binops, int numVars){
        this.currentOps = binops;
        this.numIndepVars = numVars; 
    }

    public Node getOperator (Random rand){
        Binop op = currentOps[rand.nextInt(currentOps.length)];
        return new Node(op);
    }
    public Node getTerminal (Random rand){
        int ranVar = rand.nextInt(numIndepVars); 
        return new Node(new Variable (ranVar));
    }

    public int getNumOps(){
        return currentOps.length;
    }
    public int getNumIndepVars(){
        return numIndepVars;
    }  
}
