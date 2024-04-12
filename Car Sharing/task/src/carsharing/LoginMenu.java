package carsharing;


import carsharing.companies.CompanyMenu;
import carsharing.customers.CustomerDao;
import carsharing.customers.Customer;
import carsharing.customers.CustomerMenu;

import java.util.List;
import java.util.Scanner;

enum LogInEnum {
    LOG_IN_AS_A_MANAGER,
    LOG_IN_AS_A_CUSTOMER,
    CREATE_A_CUSTOMER;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).replace('_', ' ').toLowerCase();
    }
}

public class LoginMenu implements Menu {
    public CustomerDao customerDao = new CustomerDao();
    public Scanner scanner = new Scanner(System.in);

    @Override
    public void openMenu() {
        while (true) {
            int menuOptionIndex = 1;
            for (LogInEnum menuOption : LogInEnum.values()) {
                System.out.printf("%d. %s%n", menuOptionIndex++, menuOption);
            }
            System.out.println("0. Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: {
                    Menu menu = new CompanyMenu();
                    menu.openMenu();
                    break;
                }
                case 2: {
                    listObjects();
                    break;
                }
                case 3: {
                    createObjects();
                    break;
                }
                case 0: {
                    System.exit(0);
                }
                default:
                    System.out.println("Please insert 0, 1, 2 or 3.");
            }
        }
    }

    @Override
    public void listObjects() {
        System.out.println();
        if (customerDao.findAll().isEmpty()) {
            System.out.println("The customer list is empty!");
        } else {
            System.out.println("Customer list:");
            int customerIndex = 1;
            List<Customer> customers = customerDao.findAll();
            for (Customer customer : customers) {
                System.out.printf("%d. %s%n", customerIndex++, customer.getName());
            }
            System.out.println("0. Back");
            int customerChoice = Integer.parseInt(scanner.nextLine());
            if (customerChoice > 0 && customerChoice <= customerIndex) {
                Customer customer = customers.get(customerChoice - 1);
                Menu customerMenu = new CustomerMenu(customer);
                customerMenu.openMenu();
            }
        }
        System.out.println();
    }

    @Override
    public void createObjects() {
        System.out.println();
        System.out.println("Enter the customer name:");
        String newCustomerName = scanner.nextLine();
        customerDao.add(new Customer(newCustomerName));
        System.out.println();
    }
}