import java.math.BigDecimal;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class HumanMain {
    private static final Map<String, Human> people = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //Fixture data:
        people.put("John Doe", new Human("John", "Doe", 30, "New York", BigDecimal.valueOf(100.0)));
        people.put("Jane Doe", new Human("Jane", "Doe", 28, "Los Angeles", BigDecimal.valueOf(150.0)));
        people.put("Michael Smith", new Human("Michael", "Smith", 35, "Chicago", BigDecimal.valueOf(200.0)));
        people.put("Maria Garcia", new Human("Maria", "Garcia", 32, "Miami", BigDecimal.valueOf(250.0)));
        people.put("James Johnson", new Human("James", "Johnson", 40, "Dallas", BigDecimal.valueOf(300.0)));
        people.put("Linda Brown", new Human("Linda", "Brown", 29, "Philadelphia", BigDecimal.valueOf(350.0)));
        people.put("Robert Davis", new Human("Robert", "Davis", 45, "Houston", BigDecimal.valueOf(400.0)));
        people.put("Patricia Miller", new Human("Patricia", "Miller", 27, "Atlanta", BigDecimal.valueOf(450.0)));

        System.out.println("Welcome to the Human Creator! We are really happy that you are here!");
        try {
            mainMenu();
        } finally {
            scanner.close();
        }
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("""
                    Choose what you want to achieve:
                    1: Show all people
                    2: Register
                    3: Select human
                    4: Exit""");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        showPeople();
                        break;
                    case 2:
                        registerPerson();
                        break;
                    case 3:
                        selectHuman();
                        break;
                    case 4:
                        System.out.println("Exiting program.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select a number between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static void showPeople() {
        if (people.isEmpty()) {
            System.out.println("No registered people.");
        } else {
            System.out.println("Registered people:");
            people.forEach((name, human) -> System.out.println(name));
        }
        System.out.println("***************************************************");
    }

    private static void registerPerson() {
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter surname:");
        String surname = scanner.nextLine();
        String fullName = name + " " + surname;

        if (people.containsKey(fullName)) {
            System.out.println("A person with these credentials already exists!");
            return;
        }

        try {
            System.out.println("Enter age:");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter city:");
            String city = scanner.nextLine();

            Human newPerson = new Human(name, surname, age, city);
            people.put(fullName, newPerson);
            System.out.println(fullName + " has been registered.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid age. Please enter a valid number.");
        }
    }

    private static void selectHuman() {
        if (people.isEmpty()) {
            System.out.println("No people available to select.");
            return;
        }

        System.out.println("Choose a person by full name:");
        showPeople();
        String fullName = scanner.nextLine();

        Human selectedPerson = people.getOrDefault(fullName, null);
        if (selectedPerson == null) {
            System.out.println("Person not found.");
            return;
        }

        personMenu(fullName, selectedPerson);
    }

    private static void personMenu(String fullName, Human person) {
        while (true) {
            System.out.println("\nSelected Human: " + fullName);
            System.out.println("1: Show details\n2: Edit details\n3: Make a transaction\n4: Delete person\n5: Return to main menu");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    person.personIntroduction(); // Assuming Human.toString() method is overridden to display details.
                    break;
                case "2":
                    editHuman(fullName, person);
                    fullName = person.getName() + " " + person.getSurname();
                    break;
                case "3":
                    makeTransaction(person, scanner);
                    break;
                case "4":
                    deleteHuman(fullName);
                    return; // Exit after deletion to prevent further actions on a deleted object.
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void editHuman(String fullName, Human person) {
        System.out.println("Editing details for: " + fullName);

        // Prompt for new name and surname
        System.out.println("Enter new name (current: " + person.getName() + "):");
        String newName = scanner.nextLine();
        System.out.println("Enter new surname (current: " + person.getSurname() + "):");
        String newSurname = scanner.nextLine();

        // Prompt for new age
        System.out.println("Enter new age (current: " + person.getAge() + "):");
        int newAge = Integer.parseInt(scanner.nextLine()); // Adding basic validation could improve this

        // Prompt for new city
        System.out.println("Enter new city (current: " + person.getCity() + "):");
        String newCity = scanner.nextLine();

        // Update the Human instance
        person.setName(newName.trim().isEmpty() ? person.getName() : newName);
        person.setSurname(newSurname.trim().isEmpty() ? person.getSurname() : newSurname);
        person.setAge(newAge);
        person.setCity(newCity.trim().isEmpty() ? person.getCity() : newCity);

        String newFullName = person.getName() + " " + person.getSurname();

        // If the full name has changed, update the map
        if (!fullName.equals(newFullName)) {
            people.remove(fullName);
            people.put(newFullName, person);
            System.out.println("Details updated. Note: Name changed, entry moved under new name.");
        } else {
            System.out.println("Details updated.");
        }
    }


    private static void makeTransaction(Human person, Scanner scanner) {
        boolean continueTransaction = true;

        while (continueTransaction) {
            System.out.println("""
                    Would you like to (W) Withdraw the money
                    Would you like to (D) Deposit the money?
                    Would you like to (T) Transfer money to somebody?
                    Type 'E' to exit.
                    """);
            String selectedValue = scanner.nextLine().trim().toUpperCase();

            try {
                BigDecimal value;
                switch (selectedValue) {
                    case "W":
                        System.out.println("How much would you like to withdraw?");
                        value = scanner.nextBigDecimal();
                        scanner.nextLine();
                        if (value.compareTo(BigDecimal.ZERO) > 0) {
                            person.withdrawMoney(value);
                        } else {
                            System.out.println("Please enter a positive amount.");
                        }
                        break;
                    case "D":
                        System.out.println("How much would you like to deposit?");
                        value = scanner.nextBigDecimal();
                        scanner.nextLine();
                        if (value.compareTo(BigDecimal.ZERO) > 0) {
                            person.addMoney(value);
                            System.out.println("You have deposited " + value + "PLN");
                        } else {
                            System.out.println("Please enter a positive amount.");
                        }
                        break;
                    case "T":
                        System.out.println("To who would you like to transfer your money?");
                        showPeople();
                        String fullName = scanner.nextLine();

                        Human selectedPerson = people.getOrDefault(fullName, null);
                        if (selectedPerson == null) {
                            System.out.println("Person not found.");
                            return;
                        }
                        System.out.println("How much would you like to transfer?");
                        value = scanner.nextBigDecimal();
                        scanner.nextLine();
                        if (value.compareTo(BigDecimal.ZERO) > 0) {
                            person.transferMoney(selectedPerson, value);
                        } else {
                            System.out.println("Please enter a positive amount.");
                        }

                        break;
                    case "E":
                        continueTransaction = false;
                        break;
                    default:
                        System.out.println("Inputed value is not correct. Please enter W, D, or E.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static void deleteHuman(String fullName) {
        people.remove(fullName);
        System.out.println(fullName + " has been deleted from the registry.");
    }

    // Placeholder for other methods...
}
