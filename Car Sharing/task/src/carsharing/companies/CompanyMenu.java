package carsharing.companies;

import carsharing.DaoInterface;
import carsharing.LoginMenu;
import carsharing.Menu;
import carsharing.cars.CarMenu;
import carsharing.customers.Customer;

import java.util.List;
import java.util.Scanner;

public class CompanyMenu extends LoginMenu {
    public Customer customer;
    DaoInterface<Company> companyDao;

    public CompanyMenu(Customer customer) {
        this.customer = customer;
        this.companyDao = new CompanyDao(customer);
    }

    public CompanyMenu() {
        this.customer = null;
        this.companyDao = new CompanyDao();
    }

    public void openMenu() {
        boolean open = true;
        while (open) {
            System.out.println();
            if (customer != null) {
                System.out.println("Choose a company:");
            }
            int menuOptionIndex = 1;
            for (CompanyMenuEnum menuOption : CompanyMenuEnum.values()) {
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
        if (companyDao.findAll().isEmpty()) {
            System.out.println("The company list is empty!");
        } else {
            System.out.println("Choose a company:");
            int companyIndex = 1;
            List<Company> companies = companyDao.findAll();
            for (Company company : companies) {
                System.out.printf("%d. %s%n", companyIndex++, company.getName());
            }
            System.out.println("0. Back");
            int companyChoice = Integer.parseInt(scanner.nextLine());
            if (companyChoice > 0 && companyChoice <= companyIndex) {
                Company company = companies.get(companyChoice - 1);
                Menu carMenu = new CarMenu(customer, company);
                System.out.println();
                if (customer == null) {
                    carMenu.openMenu();
                } else {
                    carMenu.listObjects();
                }
            }
        }
    }

    @Override
    public void createObjects() {
        System.out.println("Enter the company name:");
        String newCompanyName = scanner.nextLine();
        companyDao.add(new Company(newCompanyName));
    }
}
