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
        System.out.println("You have deposited " + amount + "PLN");
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

}
