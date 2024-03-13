import java.math.BigDecimal;

public class Human {
    private int age;
    private String name;
    private String surname;
    private String city;

    private BigDecimal balance;

    public Human(String name, String surname, int age, String city) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.city = city;
        this.balance = BigDecimal.valueOf(0);
    }

    public Human(String name, String surname, int age, String city, BigDecimal balance) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.city = city;
        this.balance = balance;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {

        this.balance = balance;
    }

    public void addMoney(BigDecimal amount) {
        this.balance = balance.add(amount);
    }

    public void withdrawMoney(BigDecimal amount) {
        BigDecimal difference = balance.subtract(amount);
        if (difference.doubleValue() < 0) {
            System.out.println("The balance of your account is: " + balance + "PLN.\n" +
                    "While you are trying to withdraw: " + amount + "PLN.\n" +
                    "You cannot perform the action, as you would need to have " + difference + "PLN more.");
        } else {
            balance = difference;
            System.out.println("You have withdrawn " + amount + "PLN and current balance of your account is: " + (balance) + "PLN.");
        }
    }

    public void personIntroduction() {
        System.out.println("My name is: " + name + " " + surname + ".\n" +
                "I am " + age + " years old and I live in " + city + ".\n" +
                "Currently I have " + balance + "PLN amount of money.");
    }

    public void transferMoney(Human recipient, BigDecimal amount) {
        if (this.balance.compareTo(amount) >= 0 && recipient != null && amount.compareTo(BigDecimal.ZERO) > 0) {
            this.balance = this.balance.subtract(amount); // Subtract the amount from the sender's balance
            recipient.addMoney(amount); // Add the amount to the recipient's balance
            System.out.println("Transferred " + amount + "PLN to " + recipient.getName() + " " + recipient.getSurname() + ". Your current balance is: " + this.balance + "PLN.");
        } else if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("The amount to transfer must be greater than 0 PLN.");
        } else {
            System.out.println("Insufficient funds for the transfer. Your balance is " + this.balance + "PLN, and you attempted to transfer " + amount + "PLN.");
        }
    }


}
