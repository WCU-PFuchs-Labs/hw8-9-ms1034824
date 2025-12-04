import java.util.*;

public class GPTree implements Collector, Comparable<GPTree>, Cloneable {
    private Node root;
    private ArrayList<Node> crossNodes;
    private double fitness; //add

    public static void main (String [] args){
        System.out.println("hello");
    }

    public void evalFitness(DataSet dataset){
        double sum = 0.0;
        for (DataRow row : dataset.getRows()) {
            double[] datasetX = row.getIndependentVariables();
            double predicted = eval(datasetX);
            double actual = row.getDependentVariable();
            double diff = predicted - actual;
            sum += Math.pow(diff, 2);
        }
        this.fitness = sum;
    }

    public double getFitness() {
        return fitness;
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
        this.traverse();
        tree.traverse();
        int thisPoint = rand.nextInt(this.crossNodes.size());
        int treePoint = rand.nextInt(tree.crossNodes.size());
        boolean left = rand.nextBoolean();
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
    }    

    public GPTree(NodeFactory n, int maxDepth, Random rand) {
        root = n.getOperator(rand);
        root.addRandomKids(n, maxDepth, rand);
    }

    public String toString() { 
        return root.toString(); 
    }

    public double eval(double[] data) { 
        return root.eval(data); 
    }

    @Override
    public int compareTo(GPTree other) {
        return Double.compare(this.fitness, other.fitness);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof GPTree)) return false;
        return this.compareTo((GPTree) o) == 0;
    }

    @Override
    public Object clone() {
        try {
            GPTree copy = (GPTree) super.clone();
            copy.root = (Node) this.root.clone();
            return copy;
        } catch (CloneNotSupportedException e) {
            System.out.println("GPTree can't clone.");
            return null;
        }
    }
}
