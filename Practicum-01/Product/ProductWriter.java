import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class ProductWriter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> productRecords = new ArrayList<>();

        while (true) {
            String id = SafeInput.getNonZeroLenString(scanner, "Enter ID (or press Q to quit)");
            if (id.equalsIgnoreCase("Q")) {
                break;
            }

            String name = SafeInput.getNonZeroLenString(scanner, "Enter Product Name");
            String description = SafeInput.getNonZeroLenString(scanner, "Enter Product Description");
            double cost = SafeInput.getDouble(scanner, "Enter Product Cost");

            String record = String.format("%s, %s, %s, %.1f", id, name, description, cost);
            productRecords.add(record);

            System.out.println("Record added successfully.");
        }

        if (!productRecords.isEmpty()) {
            String fileName = SafeInput.getNonZeroLenString(scanner, "Enter the file name to save the records");
            try (FileWriter writer = new FileWriter(fileName)) {
                for (String record : productRecords) {
                    writer.write(record + "\n");
                }
                System.out.println("Records saved successfully to " + fileName);
            } catch (IOException e) {
                System.out.println("An error occurred while saving the file: " + e.getMessage());
            }
        } else {
            System.out.println("No records to save.");
        }

        scanner.close();
    }
}