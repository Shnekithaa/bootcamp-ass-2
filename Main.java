// Interface Segregation Principle (ISP)
interface EngineOperations {
    void startEngine();
}

interface FuelOperations {
    void refuel();
}

// Dependency Inversion Principle (DIP)
interface Engine {
    void start();
}

class PetrolEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Petrol engine starts.");
    }
}

class ElectricEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Electric engine starts.");
    }
}

// Single Responsibility Principle (SRP)
class InputHandler {
    public String getInput() {
        return "Start Car";
    }
}

class OutputHandler {
    public void displayOutput(String output) {
        System.out.println("Output: " + output);
    }
}

class BusinessLogic {
    public void processAction(String action) {
        if (action.equalsIgnoreCase("Start Car")) {
            System.out.println("Processing car startup.");
        } else {
            System.out.println("Unknown action.");
        }
    }
}

// Open/Closed Principle (OCP)
// Base class: open for extension but closed for modification
abstract class Vehicle implements EngineOperations {
    protected Engine engine;

    public Vehicle(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void startEngine() {
        engine.start();
    }
}

// Liskov Substitution Principle (LSP)
// Car, Bike, Truck classes can substitute for Vehicle
class Car extends Vehicle implements FuelOperations {
    public Car(Engine engine) {
        super(engine);
    }

    @Override
    public void refuel() {
        System.out.println("Car is refueling.");
    }
}

class Bike extends Vehicle {
    public Bike(Engine engine) {
        super(engine);
    }
}

class Truck extends Vehicle implements FuelOperations {
    public Truck(Engine engine) {
        super(engine);
    }

    @Override
    public void refuel() {
        System.out.println("Truck is refueling.");
    }
}

// Polymorphism (Method Overloading & Overriding)
class Calculator {
    // Compile-time Polymorphism (Method Overloading)
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public String add(String a, String b) {
        return a + b;
    }
}


public class Main {
    public static void main(String[] args) {
        // Single Responsibility Principle 
        InputHandler inputHandler = new InputHandler();
        BusinessLogic businessLogic = new BusinessLogic();
        OutputHandler outputHandler = new OutputHandler();

        String userAction = inputHandler.getInput();
        businessLogic.processAction(userAction);
        outputHandler.displayOutput("Car started successfully!");

        // Dependency Inversion Principle (DIP) and Polymorphism
        Engine petrolEngine = new PetrolEngine();
        Vehicle myCar = new Car(petrolEngine);  
        myCar.startEngine();  
        ((Car) myCar).refuel();  

        // Runtime polymorphism (method overriding)
        Vehicle myBike = new Bike(new ElectricEngine());
        myBike.startEngine();  

        // Demonstrating flexibility: Parent class reference holding child objects
        Vehicle myTruck = new Truck(new PetrolEngine());
        myTruck.startEngine();  
        ((Truck) myTruck).refuel(); 

        // Compile-time Polymorphism (Method Overloading)
        Calculator calc = new Calculator();
        System.out.println("Addition of ints: " + calc.add(2, 3));  
        System.out.println("Addition of doubles: " + calc.add(2.5, 3.5));  
        System.out.println("Addition of strings: " + calc.add("Hello", " World"));  
    }
}
