package carsharing.cars;

import carsharing.DaoInterface;
import carsharing.companies.Company;
import carsharing.customers.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDao implements DaoInterface<Car> {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
    Customer customer;
    Company company;

    public CarDao(Customer customer, Company company) {
        this.customer = customer;
        this.company = company;
    }

    public void add(Car car) {
        if (customer == null) {
            try {
                String queryString = "INSERT INTO CAR(NAME, COMPANY_ID) VALUES(?, ?)";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);
                ptmt.setString(1, car.getName());
                ptmt.setInt(2, car.getForeignId());
                ptmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("The car was added!");
                close(ptmt, connection);
            }
        }
    }

    public Car findByIndex(int index) {
        Car car = null;
        try {
            String queryString = "SELECT * FROM CAR WHERE COMPANY_ID=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, company.getId());
            resultSet = ptmt.executeQuery();
            resultSet.absolute(index);
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("NAME");
            car = new Car(id, name, company.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(ptmt, connection);
        }
        return car;
    }

    public void rentCar(Car car) {
        try {
            String queryString = "UPDATE CUSTOMER SET RENTED_CAR_ID=? WHERE ID=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, car.getId());
            ptmt.setInt(2, customer.getId());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.printf("You rented '%s'%n", car.getName());
            close(ptmt, connection);
        }
    }

    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        try {
            String queryString ="""
            SELECT * FROM CAR
            WHERE COMPANY_ID=? AND NOT EXISTS(
                SELECT ID FROM CUSTOMER
                WHERE CUSTOMER.RENTED_CAR_ID = CAR.ID
            )
            """;
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, company.getId());
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                Car car = new Car(id, name, company.getId());
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(ptmt, connection);
        }
        return cars;
    }
}
