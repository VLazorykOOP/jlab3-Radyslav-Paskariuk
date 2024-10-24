import java.util.Scanner;

// 1
class Animal {
    protected int age;
    protected String gender;

    public Animal(int age, String gender) {
        this.age = age;
        this.gender = gender;
    }

    public void Show() {
        System.out.println("Age: " + age + ", Gender: " + gender);
    }
}

class Mammal extends Animal {
    public Mammal(int age, String gender) {
        super(age, gender);
    }

    @Override
    public void Show() {
        System.out.println("Mammal -> Age: " + age + ", Gender: " + gender);
    }
}

class Ungulate extends Mammal {
    public Ungulate(int age, String gender) {
        super(age, gender);
    }

    @Override
    public void Show() {
        System.out.println("Ungulate -> Age: " + age + ", Gender: " + gender);
    }
}

class Bird extends Animal {
    private boolean canFly;

    public Bird(int age, String gender, boolean canFly) {
        super(age, gender);
        this.canFly = canFly;
    }

    @Override
    public void Show() {
        System.out.println("Bird -> Age: " + age + ", Gender: " + gender + ", Can Fly: " + canFly);
    }
}

// 2
abstract class Pair {
    public abstract Pair add(Pair other);
    public abstract Pair subtract(Pair other);
    public abstract Pair multiply(int scalar);
    public abstract Pair divide(int scalar);
    
    @Override
    public abstract String toString();
    
    @Override
    public abstract boolean equals(Object obj);
}

class Money extends Pair {
    private int dollars;
    private int cents;

    public Money(int dollars, int cents) {
        this.dollars = dollars;
        this.cents = cents;
    }

    @Override
    public Pair add(Pair other) {
        if (other instanceof Money) {
            Money otherMoney = (Money) other;
            int totalCents = this.cents + otherMoney.cents;
            int newCents = totalCents % 100;
            int newDollars = this.dollars + otherMoney.dollars + totalCents / 100;
            return new Money(newDollars, newCents);
        }
        return null;
    }

    @Override
    public Pair subtract(Pair other) {
        if (other instanceof Money) {
            Money otherMoney = (Money) other;
            int totalCents = (this.dollars * 100 + this.cents) - (otherMoney.dollars * 100 + otherMoney.cents);
            int newDollars = totalCents / 100;
            int newCents = totalCents % 100;
            return new Money(newDollars, newCents);
        }
        return null;
    }

    @Override
    public Pair multiply(int scalar) {
        int totalCents = (this.dollars * 100 + this.cents) * scalar;
        int newDollars = totalCents / 100;
        int newCents = totalCents % 100;
        return new Money(newDollars, newCents);
    }

    @Override
    public Pair divide(int scalar) {
        if (scalar != 0) {
            int totalCents = (this.dollars * 100 + this.cents) / scalar;
            int newDollars = totalCents / 100;
            int newCents = totalCents % 100;
            return new Money(newDollars, newCents);
        }
        return null;
    }

    @Override
    public String toString() {
        return dollars + " dollars and " + cents + " cents";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Money money = (Money) obj;
        return dollars == money.dollars && cents == money.cents;
    }
}

class Complex extends Pair {
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    @Override
    public Pair add(Pair other) {
        if (other instanceof Complex) {
            Complex otherComplex = (Complex) other;
            return new Complex(this.real + otherComplex.real, this.imaginary + otherComplex.imaginary);
        }
        return null;
    }

    @Override
    public Pair subtract(Pair other) {
        if (other instanceof Complex) {
            Complex otherComplex = (Complex) other;
            return new Complex(this.real - otherComplex.real, this.imaginary - otherComplex.imaginary);
        }
        return null;
    }

    @Override
    public Pair multiply(int scalar) {
        return new Complex(this.real * scalar, this.imaginary * scalar);
    }

    @Override
    public Pair divide(int scalar) {
        if (scalar != 0) {
            return new Complex(this.real / scalar, this.imaginary / scalar);
        }
        return null;
    }

    @Override
    public String toString() {
        return real + " + " + imaginary + "i";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Complex complex = (Complex) obj;
        return Double.compare(complex.real, real) == 0 &&
               Double.compare(complex.imaginary, imaginary) == 0;
    }
}



public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Animal[] animals = new Animal[3];
        
        System.out.println("Enter Mammal details (age, gender):");
        int mammalAge = scanner.nextInt();
        String mammalGender = scanner.next();
        animals[0] = new Mammal(mammalAge, mammalGender);
        
        System.out.println("Enter Ungulate details (age, gender):");
        int ungulateAge = scanner.nextInt();
        String ungulateGender = scanner.next();
        animals[1] = new Ungulate(ungulateAge, ungulateGender);
        
        System.out.println("Enter Bird details (age, gender, canFly true/false):");
        int birdAge = scanner.nextInt();
        String birdGender = scanner.next();
        boolean canFly = scanner.nextBoolean();
        animals[2] = new Bird(birdAge, birdGender, canFly);
        
        System.out.println("\nList of animals:");
        for (Animal animal : animals) {
            animal.Show();
        }
        
        scanner.close();

        // 2 
        Pair money1 = new Money(5, 75);  // 5 dollars 75 cents
        Pair money2 = new Money(3, 50);  // 3 dollars 50 cents
        Pair resultMoney = money1.add(money2);
        System.out.println("Result of adding money: " + resultMoney);  // 9 dollars 25 cents

        Pair complex1 = new Complex(3, 4);  // 3 + 4i
        Pair complex2 = new Complex(1, 2);  // 1 + 2i
        Pair resultComplex = complex1.add(complex2);
        System.out.println("Result of adding complex numbers: " + resultComplex);  // 4 + 6i
    }
}
