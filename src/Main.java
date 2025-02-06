
import java.util.Scanner;
import java.util.ArrayList;

abstract class Vehicle {
    String brand;
    String model;
    int year;

    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    abstract void displayInfo();
}

interface RentalOperations {
    void rentVehicle(String model);
    void returnVehicle(String model);
    void displayAvailableVehicles();
    int countAvailableVehicles();
}
class Car extends Vehicle {
    public Car(String brand, String model, int year) {
        super(brand, model, year);
    }

    @Override
    void displayInfo() {
        System.out.println("Samochód: " + brand + " " + model + " (" + year + ")");
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String brand, String model, int year) {
        super(brand, model, year);
    }

    @Override
    void displayInfo() {
        System.out.println("Motocykl: " + brand + " " + model + " (" + year + ")");
    }
}
class RentalService implements RentalOperations {
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    ArrayList<Vehicle> rentedVehicles = new ArrayList<>();

    public RentalService() {
        vehicles.add(new Car("Mercedes-Benz", "A35", 2020));
        vehicles.add(new Car("BMW", "M3 Touring", 2022));
        vehicles.add(new Car("Audi", "RS4", 2018));
        vehicles.add(new Motorcycle("Harley-Davidson", "Electra-Glide", 2008));
    }

    @Override
    public void rentVehicle(String model) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.model.equals(model)) {
                vehicles.remove(vehicle);
                rentedVehicles.add(vehicle);
                System.out.println("Pojazd " + model + " został wypożyczony z wypożyczalni");
                return;
            }
        }
        System.out.println("Pojazd " + model + " nie jest dostępny w wypożyczalni.");
    }

    @Override
    public void returnVehicle(String model) {
        for (Vehicle vehicle : rentedVehicles) {
            if (vehicle.model.equals(model)) {
                rentedVehicles.remove(vehicle);
                vehicles.add(vehicle);
                System.out.println("Pojazd " + model + " został zwrócony do wypożyczalni.");
                return;
            }
        }
        System.out.println("Pojazd " + model + " nie został wypożyczony z wypożyczalni.");
    }

    @Override
    public void displayAvailableVehicles() {
        System.out.println("Dostępne pojazdy w wypożyczalni:");
        for (Vehicle vehicle : vehicles) {
            vehicle.displayInfo();
        }
    }

    @Override
    public int countAvailableVehicles() {
        return vehicles.size();
    }
}

public class Main {
    public static void main(String[] args) {
        RentalService rentalService = new RentalService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Dostępne pojazdy przed wypożyczeniem ---");
        rentalService.displayAvailableVehicles();

        System.out.println();

        rentalService.rentVehicle("M3 Touring");
        rentalService.rentVehicle("Electra-Glide");

        System.out.println();

        System.out.println("--- Dostepne pojazdy po wypożyczeniu ---");
        rentalService.displayAvailableVehicles();

        System.out.println();

        rentalService.returnVehicle("RS4");

        System.out.println();

        System.out.println("--- Dostępne pojazdy po zwrocie ---");
        rentalService.displayAvailableVehicles();

        System.out.println();

        System.out.print("Liczba dostępnych pojazdów: ");
        System.out.println(rentalService.countAvailableVehicles());
    }
}
