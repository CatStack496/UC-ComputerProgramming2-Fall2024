import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class PersonGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> personRecords = new ArrayList<>();

        while (true) {
            String id = SafeInput.getNonZeroLenString(scanner, "Enter ID (or press Q to quit)");
            if (id.equalsIgnoreCase("Q")) {
                break;
            }

            String firstName = SafeInput.getNonZeroLenString(scanner, "Enter First Name");
            String lastName = SafeInput.getNonZeroLenString(scanner, "Enter Last Name");
            String title = SafeInput.getNonZeroLenString(scanner, "Enter Title");
            int yearOfBirth = SafeInput.getRangedInt(scanner, "Enter Year of Birth", 1000, 9999);

            String record = String.format("%s, %s, %s, %s, %d", id, firstName, lastName, title, yearOfBirth);
            personRecords.add(record);

            System.out.println("Record added successfully.");
        }

        if (!personRecords.isEmpty()) {
            String fileName = SafeInput.getNonZeroLenString(scanner, "Enter the file name to save the records");
            try (FileWriter writer = new FileWriter(fileName)) {
                for (String record : personRecords) {
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