package carsharing.cars;

import carsharing.companies.CompanyMenu;
import carsharing.companies.Company;
import carsharing.customers.Customer;

import java.util.List;
import java.util.Scanner;

public class CarMenu extends CompanyMenu {
    Company company;
    CarDao carDao;

    public CarMenu(Customer customer, Company company) {
        this.customer = customer;
        this.company = company;
        this.carDao = new CarDao(customer, company);
    }

    public void openMenu() {
        boolean open = true;
        while (open) {
            System.out.println("'" + company.getName() + "' company");
            int menuOptionIndex = 1;
            for (CarMenuEnum menuOption : CarMenuEnum.values()) {
                System.out.printf("%d. %s%n", menuOptionIndex++, menuOption);
            }
            System.out.println("0. Back");
            int choice = Integer.parseInt(scanner.nextLine());
            System.out.println();
            switch (choice) {
                case 1: {
                    listObjects();
                    break;
                }
                case 2: {
                    createObjects();
                    break;
                }
                case 0: {
                    open = false;
                    break;
                }
                default:
                    System.out.println("Please insert 0, 1 or 2.");
            }
        }
    }

    public void listObjects() {
        if (carDao.findAll().isEmpty()) {
            System.out.println("The car list is empty!");
        } else {
            System.out.println("Car list:");
            List<Car> cars = carDao.findAll();
            int carIndex = 1;
            for (Car car : cars) {
                System.out.printf("%d. %s%n", carIndex++, car.getName());
            }
            if (customer != null) {
                System.out.println("0. Back");
                int carChoice = Integer.parseInt(scanner.nextLine());
                System.out.println();
                if (carChoice > 0 && carChoice <= carIndex) {
                    Car car = cars.get(carChoice - 1);
                    carDao.rentCar(car);
                    customer.setForeignId(car.getId());
                }
            }
        }
        System.out.println();
    }

    @Override
    public void createObjects() {
        System.out.println("Enter the car name:");
        String newCarName = scanner.nextLine();
        carDao.add(new Car(newCarName, company.getId()));
        System.out.println();
    }
}