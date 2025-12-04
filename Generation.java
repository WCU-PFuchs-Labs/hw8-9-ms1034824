import java.util.*;

public class Generation {
    private GPTree[] trees;
    private DataSet dataset;
    private Random rand;

    public Generation(int size, int maxDepth, String fileName) {
        dataset = new DataSet(fileName); // assumes DataSet has a file-based constructor
        Binop[] ops = { new Plus(), new Minus(), new Mult(), new Divide() };
        NodeFactory factory = new NodeFactory(ops, dataset.getNumIndependentVariables());
        rand = new Random();
        trees = new GPTree[size];
        for (int i = 0; i < size; i++) {
            trees[i] = new GPTree(factory, maxDepth, rand);
        }
    }

    public void evalAll() {
        for (GPTree tree : trees) {
            tree.evalFitness(dataset);
        }
        Arrays.sort(trees);
    }

    public ArrayList<GPTree> getTopTen() {
        ArrayList<GPTree> topTen = new ArrayList<>();
        for (int i = 0; i < Math.min(10, trees.length); i++) {
            topTen.add(trees[i]);
        }
        return topTen;
    }

    public void printBestFitness() {
        System.out.printf("Best Fitness: %.2f%n", trees[0].getFitness());
    }

    public void printBestTree() {
        System.out.println("Best Tree: " + trees[0]);
    }

    public void evolve() {
        GPTree[] newGen = new GPTree[trees.length];
        
        for (int i = 0; i < trees.length; i += 2) {
            // Select parents from the top half (more fit trees)
            GPTree parent1 = (GPTree) trees[rand.nextInt(trees.length / 2)].clone();
            GPTree parent2 = (GPTree) trees[rand.nextInt(trees.length / 2)].clone();
            
            // Perform crossover between the cloned parents
            parent1.crossover(parent2, rand);
            
            newGen[i] = parent1;
            if (i + 1 < trees.length) {
                newGen[i + 1] = parent2;
            }
        }

        trees = newGen;
    }
}