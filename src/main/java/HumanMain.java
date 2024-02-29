import java.util.HashMap;
import java.util.Scanner;

public class HumanMain {
    public static HashMap<String, Human> people = new HashMap<>();

    public static void main(String[] args) {
        Scanner inputData = new Scanner(System.in);
        print("Welcome in thee human creator!\n" +
                "We are really happy that you are here!");

        while(true) {
            print("Chose what you want to achieve:\n" +
                    "1: Show all people\n" +
                    "2: Register\n" +
                    "3: Select human\n" +
                    "4: Make transaction");
            print("Enter your choice: ");
            int choice = Integer.parseInt(inputData.nextLine());

            switch (choice){
                case 1:
                    showPeople();
                    break;
                case 2:
                    registerPerson(inputData);
                    break;
                case 3:
                    //Select human
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
    private static void showPeople(){
        if(!people.isEmpty()) {
            System.out.println("Reggistered people people:");
            for(String key: people.keySet()) {
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

        if(!people.containsKey(fullName)) {
            System.out.println("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter city: ");
            String city = scanner.nextLine();

            Human newPerson = new Human(name,surname,age,city);
            people.put(fullName, newPerson);
            System.out.println(fullName + " has been registered.");
        }
        else {
            System.out.println("Person with those credentials already exists!\n" +
                    "***************************************************");
        }
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
