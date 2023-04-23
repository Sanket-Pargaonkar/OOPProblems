import java.util.*;

import javax.swing.text.Style;

public class Solution {
    public static void main(String[] arge) {
        ArrayList<Car> cars = new ArrayList<Car>();
        cars.add(new Car("economy", 343.20, true));
        cars.add(new Car("mid-size", 443.20, true));
        cars.add(new Car("luxury", 643.20, true));
        CarRentalSystem crs = new CarRentalSystem(cars, new ArrayList<RentedCar>());
        // Customer cust = new Customer(new Location("L1"), new Location("L2"), 6,
        // null);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String mainInput = sc.nextLine();
            switch (mainInput) {
                case "1":
                    System.out.println("case 1");
                    crs.displayAvailableCars();
                    break;
                case "2":
                    System.out.println("case 2");
                    // get type of car input
                    System.out.println("Select the type of car: E M or L");
                    String carType2 = sc.nextLine();
                    Car rentedCar2 = carType2.equals("E") ? cars.get(0)
                            : carType2.equals("M") ? cars.get(1) : carType2.equals("L") ? cars.get(2) : null;
                    if(!rentedCar2.getAvailabilitty()){
                    System.out.println("Car not avialable for rental.");    
                    break;    
                    }
                    // get rental duration input
                    System.out.println("Enter duration of rental in number of hours(with decimals if required):");
                    double rentalDuration2 = sc.nextDouble();
                    sc.nextLine();
                    // get pickup and dropooffpoint input
                    System.out.println("Enter pickup location:");
                    String pickup2 = sc.nextLine();
                    System.out.println("Enter dropoofflocation:");
                    String dropoff2 = sc.nextLine();
                    rentedCar2.updateAvailability(false);
                    Customer cust2 = new Customer(new Location(pickup2), new Location(dropoff2), rentalDuration2,
                            rentedCar2);
                    RentedCar rentedCar22 = new RentedCar(rentedCar2, cust2);
                    crs.addRentedCar(rentedCar22);
                    System.out.println("Total Rental Cost would be: " + cust2.totalRentalCost());
                    break;
                case "3":
                    System.out.println("case 3");
                    System.out.println("Select the type of car: E M or L");
                    String carType3 = sc.nextLine();
                    Car rentedCar3 = carType3.equals("E") ? cars.get(0)
                            : carType3.equals("M") ? cars.get(1) : carType3.equals("L") ? cars.get(2) : null;
                    if (rentedCar3.getAvailabilitty()) {
                        System.out.println("This Car was not rented");
                        break;
                    }
                    rentedCar3.updateAvailability(true);
                    crs.ReturnCar(rentedCar3);
                    break;
                case "4":
                    System.out.println("case 4");
                    crs.displayRentedCars();
                    break;
                case "5":
                    System.out.println("case 5");
                    break;
                case "6":
                    System.out.println("case 6");
                    break;
                default:
                    System.out.println("default case. Wrint input");
                    break;
            }
        }
    }
}

class Car {
    private String CarType;
    private double RentalRate;
    private boolean Availability;

    Car(String CarType, double RentalRate, boolean Availability) {
        this.CarType = CarType;
        this.RentalRate = RentalRate;
        this.Availability = Availability;
    }

    double getRentalRate() {
        return this.RentalRate;
    }

    boolean getAvailabilitty() {
        return this.Availability;
    }

    void display() {
        System.out.println(this.CarType);
        System.out.println(this.RentalRate);
        System.out.println(this.Availability);
    }

    void updateAvailability(boolean availablity) {
        this.Availability = availablity;
    }
}

class RentedCar {
    private Car CarRented;
    private Customer Cust;

    RentedCar(Car rentedCar, Customer cust) {
        this.Cust = cust;
        this.CarRented = rentedCar;
    }

    public boolean equals(RentedCar rc) {
        return rc.Cust == this.Cust && rc.CarRented == this.CarRented ? true : false;
    }

    boolean checkCar(Car c) {
        return this.CarRented == c ? true : false;
    }

    void display() {
        this.CarRented.display();
        System.out.println("Duration: " + this.Cust.getREntalDuration());
    }

}

class Location {
    private String Name;

    Location(String Name) {
        this.Name = Name;
    }

    String getName() {
        return this.Name;
    }

    void setName(String Name) {
        this.Name = Name;
    }

}

class Customer {
    private Location PickupPoint;
    private Location DropOffPoint;
    private double RentalDuration;
    private Car CarRented;

    Customer(Location PickupPoint, Location DropOffPoint, double RentalDuration, Car CarRented) {
        this.PickupPoint = PickupPoint;
        this.DropOffPoint = DropOffPoint;
        this.RentalDuration = RentalDuration;
        this.CarRented = CarRented;
    }

    double getREntalDuration() {
        return this.RentalDuration;
    }

    double totalRentalCost() {
        return CarRented.getRentalRate() * RentalDuration;
    }
}

class CarRentalSystem {
    private List<Car> AvailableCars;
    private List<RentedCar> RentedCars;

    CarRentalSystem(List<Car> AvailableCars, List<RentedCar> RentedCars) {
        this.AvailableCars = AvailableCars;
        this.RentedCars = RentedCars;
    }

    void addRentedCar(RentedCar rentedCar) {
        for (RentedCar rc : RentedCars) {
            if (rc.equals(rentedCar)) {
                rentedCar.display();
                System.out.println("Requested car unavailable");
                return;
            }

        }
        RentedCars.add(rentedCar);
        rentedCar.display();
        System.out.println("You have rented the car successfully");
    }

    void rmRentedCar(RentedCar rentedCar) {
        for (RentedCar rc : RentedCars) {
            if (rc.equals(rentedCar)) {
                RentedCars.remove(rentedCar);
                break;
            }
        }
    }

    void displayAvailableCars() {
        for (Car c : AvailableCars) {
            c.display();
            System.out.println("----------------");
        }
    }

    void displayRentedCars() {
        for (RentedCar c : RentedCars) {
            c.display();
            System.out.println("----------------");
        }
    }    

    Car RentCar() {
        return null;
    }

    Car ReturnCar(Car c) {
        RentedCar rc_1 = null;
        for (RentedCar rc : RentedCars) {
            if (rc.checkCar(c)) {
                rc_1 = rc;
            }
        }
        RentedCars.remove(rc_1);

        return null;
    }

    void UpdateCarAvailabiliyt(Car car) {

    }
}