import java.util.*;
public class GPTree implements Collector, Comparable<GPTree>, Cloneable{
    private Node root;
    private ArrayList<Node> crossNodes;
    private double fitness; // Added to store fitness value
    
    
    /**
     * @param - node The node to be collected.
     * TODO: implement this method
     */
    public static void main (String [] args){
        System.out.println("hello");
    }
    
    public void evalFitness(DataSet dataset){
        fitness = 0.0;
        for(int i = 0; i < dataset.getRows().size(); i++){        
            double[] datasetX = dataset.getRows().get(i).getIndependentVariables();
            double datasetXEval = eval(datasetX);
            double y = dataset.getRows().get(i).getDependentVariable();
            double deviation = datasetXEval - y;
            fitness += deviation * deviation; // Square of deviation
        }
    }
    
    public double getFitness() {
        return fitness;
    }
    
    public int compareTo(GPTree t) {
        if (this.fitness < t.fitness) {
            return -1;
        } else if (this.fitness > t.fitness) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public boolean equals(Object o) {
        if (o == null || !(o instanceof GPTree)) {
            return false;
        }
        GPTree other = (GPTree) o;
        return this.compareTo(other) == 0;
    }
    
    public Object clone() {
        try {
            GPTree cloned = (GPTree) super.clone();
            // Clone the root node if it exists
            if (root != null && root instanceof Cloneable) {
                cloned.root = (Node) root.clone();
            }
            cloned.fitness = this.fitness;
            cloned.crossNodes = new ArrayList<>();
            return cloned;
        } catch (CloneNotSupportedException e) {
            System.out.println("GPTree can't clone.");
            return null;
        }
    }
    
    public void collect(Node node) {
        if(!node.isLeaf()){
            crossNodes.add(node);
        }
    }

    public void traverse() {
        crossNodes = new ArrayList<Node>();
        root.traverse(this);
    }

    public String getCrossNodes() {
        if (crossNodes == null || crossNodes.isEmpty()) {
            return "";
        }
        StringBuilder string = new StringBuilder();
        int lastIndex = crossNodes.size() - 1;
        for(int i = 0; i < lastIndex; ++i) {
            Node node = crossNodes.get(i);
            string.append(node.toString());
            string.append(";");
        }
        string.append(crossNodes.get(lastIndex));
        return string.toString();
    }
   
    public void crossover(GPTree tree, Random rand) {
        // find the points for crossover
        this.traverse();
        tree.traverse();
        
        // Make sure both trees have nodes for crossover
        if (this.crossNodes.isEmpty() || tree.crossNodes.isEmpty()) {
            return;
        }
        
        int thisPoint = rand.nextInt(this.crossNodes.size());
        int treePoint = rand.nextInt(tree.crossNodes.size());
        boolean left = rand.nextBoolean();
        // get the connection points
        Node thisTrunk = crossNodes.get(thisPoint);
        Node treeTrunk = tree.crossNodes.get(treePoint);

        
        if(left) {
            thisTrunk.swapLeft(treeTrunk);
            
        } else {
            thisTrunk.swapRight(treeTrunk);
        }
        
    }

    GPTree() { 
        root = null; 
        fitness = Double.MAX_VALUE;
    }    
    
    public GPTree(NodeFactory n, int maxDepth, Random rand) {
        root = n.getOperator(rand);
        root.addRandomKids(n, maxDepth, rand);
        fitness = Double.MAX_VALUE;
    }
    
    public String toString() { 
        return root != null ? root.toString() : "Empty Tree"; 
    }
    
    public double eval(double[] data) { 
        return root != null ? root.eval(data) : 0.0; 
    }
    
}