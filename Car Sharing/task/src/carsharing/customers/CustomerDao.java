package carsharing.customers;


import carsharing.DaoInterface;
import carsharing.cars.Car;
import carsharing.companies.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements DaoInterface<Customer> {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
    public void add(Customer customer) {
        try {
            String queryString = "INSERT INTO CUSTOMER(NAME, RENTED_CAR_ID) VALUES(?, NULL)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, customer.getName());
            ptmt.executeUpdate();
            System.out.println("The customer was added!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(ptmt, connection);
        }
    }

    public Customer findByIndex(int index) {
        Customer customer = null;
        try {
            String queryString = "SELECT * FROM CUSTOMER";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            resultSet.absolute(index);
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("NAME");
            customer = new Customer(id, name);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(ptmt, connection);
        }
        return customer;
    }

    public void findCar(Customer customer) {
        Car car = null;
        Company company = null;
        try {
            String queryString = "SELECT * FROM CAR WHERE ID=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, customer.getForeignId());
            resultSet = ptmt.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("NAME");
            int companyId = resultSet.getInt("COMPANY_ID");
            car = new Car(id, name, companyId);
            queryString = "SELECT * FROM COMPANY WHERE ID=?";
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, companyId);
            resultSet = ptmt.executeQuery();
            resultSet.next();
            company = new Company(companyId, resultSet.getString("NAME"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Your rented car:");
            System.out.println(car.getName());
            System.out.println("Company:");
            System.out.println(company.getName());
            close(ptmt, connection);
        }
    }

    public void returnCar(Customer customer) {
        if (customer.getForeignId() == 0) {
            System.out.println("You didn't rent a car!");
        } else {
            try {
                String queryString = "UPDATE CUSTOMER SET RENTED_CAR_ID=NULL WHERE ID=?";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);
                ptmt.setInt(1, customer.getId());
                ptmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("You've returned a rented car!");
                customer.setForeignId(0);
                close(ptmt, connection);
            }
        }
    }

    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try {
            String queryString = "SELECT * FROM CUSTOMER";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                int rentedCarId = resultSet.getInt("RENTED_CAR_ID");
                Customer customer = new Customer(id, name, rentedCarId);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(ptmt, connection);
        }
        return customers;
    }
}
