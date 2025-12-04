import java.util.Scanner;

public class TestGP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the data file name: ");
        String fileName = scanner.nextLine();
        
 
        Generation generation = new Generation(500, 6, fileName);
    
        for (int gen = 1; gen <= 50; gen++) {
            System.out.println("\nGeneration " + gen + ":");

            generation.evalAll();
            
            generation.printBestTree();
            generation.printBestFitness();

            if (gen < 50) {
                generation.evolve();
            }
        }
        
        scanner.close();
    }
}