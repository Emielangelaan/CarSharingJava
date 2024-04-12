package carsharing;

import carsharing.ConnectionFactory;

import java.sql.*;

public class InitializeDb {
    Connection connection = null;
    Statement statement = null;
    private static final String CREATE_CUSTOMER_DB = """
            CREATE TABLE IF NOT EXISTS CUSTOMER (
                ID INT PRIMARY KEY AUTO_INCREMENT,
                NAME VARCHAR(30) UNIQUE NOT NULL,
                RENTED_CAR_ID INT NULL,
                CONSTRAINT FK_CAR FOREIGN KEY (RENTED_CAR_ID)
                REFERENCES CAR (ID)
            );
            """;
    private static final String CREATE_COMPANY_DB = """
            CREATE TABLE IF NOT EXISTS COMPANY(
            ID INT PRIMARY KEY AUTO_INCREMENT,
            NAME VARCHAR(30) UNIQUE NOT NULL);
            """;
    private static final String CREATE_CAR_DB = """
            CREATE TABLE IF NOT EXISTS CAR (
                ID INT PRIMARY KEY AUTO_INCREMENT,
                NAME VARCHAR(30) UNIQUE NOT NULL,
                COMPANY_ID INT NOT NULL,
                CONSTRAINT FK_COMPANY FOREIGN KEY (COMPANY_ID)
                REFERENCES COMPANY (ID)
            );
            """;
    public InitializeDb() {
        initializeDatabase(CREATE_CUSTOMER_DB);
        initializeDatabase(CREATE_COMPANY_DB);
        initializeDatabase(CREATE_CAR_DB);
    }
    private Connection getConnection() throws SQLException {
        connection = ConnectionFactory.getInstance().getConnection();
        return connection;
    }

    public void initializeDatabase(String CREATE_DB) {
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_DB);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
