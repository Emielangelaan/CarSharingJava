package carsharing.customers;


import carsharing.LoginMenu;
import carsharing.Menu;
import carsharing.companies.CompanyMenu;

public class CustomerMenu extends LoginMenu {
    Customer customer;
    public CustomerMenu(Customer customer) {
        this.customer = customer;
    }

    public void openMenu() {
        boolean open = true;
        while (open) {
            System.out.println();
            int menuOptionIndex = 1;
            for (CustomerMenuEnum menuOption : CustomerMenuEnum.values()) {
                System.out.printf("%d. %s%n", menuOptionIndex++, menuOption);
            }
            System.out.println("0. Back");
            int choice = Integer.parseInt(scanner.nextLine());
            System.out.println();
            switch (choice) {
                case 1: {
                    if (customer.getForeignId() == 0) {
                        Menu menu = new CompanyMenu(customer);
                        menu.listObjects();
                    } else {
                        System.out.println("You've already rented a car!");
                    }
                    break;
                }
                case 2: {
                    customerDao.returnCar(customer);
                    break;
                }
                case 3: {
                    if (customer.getForeignId() != 0) {
                        customerDao.findCar(customer);
                    } else {
                        System.out.println("You didn't rent a car!");
                    }
                    break;
                }
                case 0: {
                    open = false;
                    break;
                }
                default:
                    System.out.println("Please insert 0, 1, 2 or 3.");
            }
        }
    }
}
