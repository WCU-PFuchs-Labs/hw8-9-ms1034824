
import java.util.*;
import java.io.*;

public class DataSet {
    ArrayList<DataRow> data = new ArrayList<DataRow>();
    int numIndepVariables;

    /**
     * @param filename the name of the file to read the data set from
     */
    public DataSet(String filename){
    try {
            Scanner scan = new Scanner(new File(filename));
            if (!scan.hasNextLine()) return; //continues reading

            // first line = header
            String header = scan.nextLine();
            String[] cols = header.split(","); //splits sections
            numIndepVariables = cols.length - 1;

            while (scan.hasNextLine()) {
                String line = scan.nextLine().trim();
                if (line.isEmpty()) continue; 

                String[] parts = line.split(",");
                if (parts.length < cols.length) continue;

                double y = Double.parseDouble(parts[0].trim());
                double[] x = new double[numIndepVariables];
                for (int i = 0; i < numIndepVariables; i++) {
                    x[i] = Double.parseDouble(parts[i + 1].trim());
                }
                data.add(new DataRow(y, x));
            }
            scan.close();
        } catch (Exception e) { //
            System.out.println("Error reading file.");
        }
    }

    public ArrayList<DataRow> getRows() {
        return data;
    }

    public int getNumIndependentVariables() {
        return numIndepVariables;
    }
}