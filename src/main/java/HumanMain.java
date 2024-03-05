import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;

public class HumanMain {
    public static HashMap<String, Human> people = new HashMap<>();

    public static void main(String[] args) {
        Scanner inputData = new Scanner(System.in);
        print("Welcome in thee human creator!\n" +
                "We are really happy that you are here!");

        while (true) {
            print("Chose what you want to achieve:\n" +
                    "1: Show all people\n" +
                    "2: Register\n" +
                    "3: Select human\n" +
                    "4: Make transaction");
            print("Enter your choice: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(inputData.nextLine());
            } catch (Exception e) {
                System.out.println("Jesteś debilem");
            }


            switch (choice) {
                case 1:
                    showPeople();
                    break;
                case 2:
                    registerPerson(inputData);
                    break;
                case 3:
                    selectHuman(inputData);
                    break;
                case 4:
                    //make transaction
                    break;
                default:
                    print("Exiting program");
                    return;
            }
        }

    }

    private static void showPeople() {
        if (!people.isEmpty()) {
            System.out.println("Reggistered people people:");
            for (String key : people.keySet()) {
                System.out.println(key);
            }
            System.out.println("***************************************************");
        } else {
            System.out.println("There are no registered people\n" +
                    "***************************************************");
        }
    }

    private static void registerPerson(Scanner scanner) {
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter surname: ");
        String surname = scanner.nextLine();

        String fullName = name + " " + surname;

        if (!people.containsKey(fullName)) {
            System.out.println("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter city: ");
            String city = scanner.nextLine();

            Human newPerson = new Human(name, surname, age, city);
            people.put(fullName, newPerson);
            System.out.println(fullName + " has been registered.");
        } else {
            System.out.println("Person with those credentials already exists!\n" +
                    "***************************************************");
        }
    }

    private static void selectHuman(Scanner scanner) {
        BigDecimal money = new BigDecimal(0);

        System.out.println("What person would you like to choose?");
        showPeople();
        String fullName = scanner.nextLine();

        System.out.println("Choosen human is: " + fullName);
        while (true) {
            System.out.println("""
                    What would you like to do?
                    1: Show all information about human
                    2: Update information about human
                    3: Make the payment
                    4: Delete the human
                    ANY: Back to the queue""");
            int choice = scanner.nextInt();
            scanner.nextLine();
            Human person = people.get(fullName);
            switch (choice) {
                case 1:
                    person.personIntroduction();
                    break;
                case 2:
                    System.out.println("It will be soon implemented :)");
                case 3:
                    System.out.println("""
                            Would you like to:
                            (W) Withdraw money
                            (D) Deposit money""");
                    String moneyChoice = scanner.nextLine();
                    if(moneyChoice.equals("W")) {
                        System.out.println("How much would you like to withdraw?");
                        money = scanner.nextBigDecimal();
                        person.withdrawMoney(money);

                    } else {
                        System.out.println("How much would you like to deposit?");
                        money = scanner.nextBigDecimal();
                        person.addMoney(money);
                    }
                    break;
                case 4:
                    person = null;
                    deleteHuman(fullName);

                    break;
                default:
                    return;

            }
        }


    }

    private static void deleteHuman(String fullName) {
        people.remove(fullName);
        System.out.println("The " + fullName + " was deleted.");
    }

    private static void print(String input) {
        System.out.println(input);
    }

    private static void print(int input) {
        System.out.println(input);
    }

    private static void print(boolean input) {
        System.out.println(input);
    }
}
