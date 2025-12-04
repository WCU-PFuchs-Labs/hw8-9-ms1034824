import java.util.Scanner;
import java.util.ArrayList;

public class TestGeneration {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the data file name: ");
        String fileName = scanner.nextLine();
        
        Generation generation = new Generation(500, 6, fileName);
        
        generation.evalAll();
        
        System.out.println("\nBest Tree:");
        generation.printBestTree();

        generation.printBestFitness();
        
        ArrayList<GPTree> topTen = generation.getTopTen();
        System.out.print("\nTop Ten Fitness Values: ");
        for (int i = 0; i < topTen.size(); i++) {
            System.out.printf("%.2f", topTen.get(i).getFitness());
            if (i < topTen.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        
        scanner.close();
    }
}