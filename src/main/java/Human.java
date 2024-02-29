public class Human {
    private int age;
    private String name;
    private String surname;
    private String city;

    private double balance = 0;

    public Human(String name, String surname,int age, String city) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.city = city;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addMoney(double amount) {
        this.balance += amount;
    }

    public void withdrawMoney(double amount) {
        if(amount > balance) {
            System.out.println("The balance of your account is: " + balance + "PLN.\n" +
                    "While you are trying to withdraw: " + amount + "PLN.\n" +
                    "You cannot perform the action, as you would need to have " + Math.floor(balance*100 - amount*100)/100 + "PLN more.");
        }
        else {
            balance = Math.floor(balance*100 - amount*100)/100;
            System.out.println("You have withdrawn " + amount + "PLN and current balance of your account is: " + balance + "PLN.");
        }
    }

    public void personIntroduction() {
        System.out.println("My name is: " + name + " " + surname +".\n" +
                "I am " + age + " years old and I live in " + city + ".\n" +
                "Currently I have " + balance + "PLN amount of money.");
    }
}
