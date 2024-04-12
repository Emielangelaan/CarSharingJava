package carsharing.companies;

import carsharing.DaoInterface;
import carsharing.customers.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao implements DaoInterface<Company> {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
    Customer customer;

    public CompanyDao(Customer customer) {
        this.customer = customer;
    }

    public CompanyDao() {
        this.customer = null;
    }

    public void add(Company company) {
        if (customer == null) {
            try {
                String queryString = "INSERT INTO COMPANY(NAME) VALUES(?)";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);
                ptmt.setString(1, company.getName());
                ptmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("The company was created!");
                close(ptmt, connection);
            }
        }
    }

    public Company findByIndex(int index) {
        Company company = null;
        try {
            String queryString = "SELECT * FROM COMPANY";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            resultSet.absolute(index);
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("NAME");
            company = new Company(id, name);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(ptmt, connection);
        }
        return company;
    }

    public List<Company> findAll() {
        List<Company> companies = new ArrayList<>();
        try {
            String queryString = "SELECT * FROM COMPANY";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                Company company = new Company(id, name);
                companies.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(ptmt, connection);
        }
        return companies;
    }
}
