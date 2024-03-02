import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final String DIRECTORY_PATH = "/Users/48518/OneDrive/Pulpit/JavaPractisee";

    public static void main(String[] args) {
//        Scanner inputData = new Scanner(System.in);
//        while (true) {
//            System.out.println("\n1: Read a file\n2: Write into a file\n3: Delete the file\nAny other number: Exit");
//            System.out.print("Enter your choice: ");
//            int choice = Integer.parseInt(inputData.nextLine()); // Parsing
//
//            switch (choice) {
//                case 1:
//                    listTxtFiles();
//                    readFromFile(inputData);
//                    break;
//                case 2:
//                    listTxtFiles();
//                    writeToFile(inputData);
//                    break;
//                case 3:
//                    listTxtFiles();
//                    deleteFile(inputData);
//                    break;
//                default:
//                    System.out.println("Exiting program.");
//                    return;
//            }
//        }

        Human person1 = new Human("Michał", "Stefański", 26, "Kraków");
        person1.addMoney(BigDecimal.valueOf(1500.32));
        person1.personIntroduction();
        person1.withdrawMoney(BigDecimal.valueOf(1300));
    }

    private static void readFromFile(Scanner scanner) {
        System.out.print("Enter the file name to read (without .txt extension): ");
        String fileName = scanner.nextLine();
        File file = new File(DIRECTORY_PATH, fileName + ".txt");

        try (Scanner fileScanner = new Scanner(file)) {
            System.out.println("File content:");
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Could not read the file: " + fileName);
        }
    }

    private static void writeToFile(Scanner scanner) {
        System.out.print("Enter the file name to write into (without .txt extension)\nYou can create the new file by inputing new filename: ");
        String fileName = scanner.nextLine();
        System.out.println("Enter the content (single line): ");
        String content = scanner.nextLine();

        try (FileWriter writer = new FileWriter(new File(DIRECTORY_PATH, fileName + ".txt"))) {
            writer.write(content);
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("Could not write to the file: " + fileName);
        }
    }

    private static void listTxtFiles() {
        File directory = new File(DIRECTORY_PATH);
        String[] txtFiles = directory.list((dir, name) -> name.endsWith(".txt"));

        if (txtFiles != null && txtFiles.length > 0) {
            System.out.println("Available .txt files:");
            Arrays.stream(txtFiles).forEach(System.out::println);
        } else {
            System.out.println("No .txt files found.");
        }
    }

    private static void deleteFile(Scanner scanner) {
        System.out.print("Enter the file name you want to delete (without .txt extension): ");
        String fileName = scanner.nextLine();
        File file = new File(DIRECTORY_PATH, fileName + ".txt");

        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the " + file.getName() + " file");
        }
    }
}
