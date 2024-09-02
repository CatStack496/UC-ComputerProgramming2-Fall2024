import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class ProductReader {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        Scanner inFile;
        String line;
        Scanner inputScanner = new Scanner(System.in);
        
        System.out.println("Choose a file to read product data from:");
        
        try {
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                inFile = new Scanner(selectedFile);
                
                // Print header
                System.out.println(String.format("%-10s %-20s %-30s %s", 
                                                 "ID#", "Name", "Description", "Cost"));
                // Print separator line
                System.out.println(new String(new char[75]).replace('\0', '='));
                
                // Read and display each line
                while (inFile.hasNextLine()) {
                    line = inFile.nextLine();
                    String[] fields = line.split(", ");
                    if (fields.length == 4) {
                        System.out.println(String.format("%-10s %-20s %-30s $%.2f", 
                                                         fields[0], fields[1], fields[2], Double.parseDouble(fields[3])));
                    } else {
                        System.out.println("Invalid record: " + line);
                    }
                }
                inFile.close();
                
                // Ask if the user wants to see the file again
                boolean readAgain = SafeInput.getYNConfirm(inputScanner, "Do you want to see the file again?");
                if (readAgain) {
                    main(args); // Restart the program
                }
            } else {
                System.out.println("No file selected. Exiting program.");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            inputScanner.close();
        }
    }
}