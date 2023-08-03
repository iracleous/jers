package gr.quedgq.jers.repository.impl;

import gr.quedgq.jers.model.Employee;
import gr.quedgq.jers.repository.EmployeeRepository;


import javax.enterprise.inject.Alternative;
import javax.inject.Named;
import java.sql.*;
import java.util.Optional;

import static java.lang.System.exit;


public class EmployeeRepositoryImplJdbc implements EmployeeRepository {

    private    static final String DB_URL = "jdbc:sqlserver://codehub-jers.database.windows.net;databaseName=codehub-jers;encrypt=true;trustServerCertificate=true;";
    private  static final String USER = "CodeHubber123";
    private  static final String PASS = "P@ssw0rd1234";
//    private    static final String DB_URL = "jdbc:sqlserver://localhost;databaseName=codehub-jers;encrypt=true;trustServerCertificate=true;";
//    private  static final String USER = "sa";
//    private  static final String PASS = "P@ssword1234";
    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";


    @Override
    public void save(Employee employee) {

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String sqlCommand = "insert into Employee (name) values(?);";

        // Open a connection
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sqlCommand,
                    Statement.RETURN_GENERATED_KEYS );
        ) {
            stmt.setString(1,employee.getName());
            stmt.execute();
            ResultSet results = stmt.getGeneratedKeys();
            results.next(); // Assume just one auto-generated key; otherwise, use a while loop here
            int index = results.getInt(1);
            employee.setId(index);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Employee> findById(int id) {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String sqlCommand = "select * from  Employee where id =?;";
        // Open a connection
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sqlCommand,
                    Statement.RETURN_GENERATED_KEYS );
        ) {
            stmt.setInt(1, id);
            ResultSet results = stmt.executeQuery();
            results.next(); // Assume just one auto-generated key; otherwise, use a while loop here
            Employee employee = new Employee();
            employee.setId(results.getInt("id"));
            employee.setName(results.getString("name"));
            return Optional.of(employee);
        } catch (SQLException e) {
            return Optional.empty();
           // throw new RuntimeException(e);
        }

      }

    @Override
    public Optional<Employee> update(int employeeId, Employee employee) {
        return Optional.empty();
    }

    @Override
    public boolean delete(int employeeId) {
        return false;
    }

    public boolean createTable() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Initializing database...");
        String createCustomerTableSql = "CREATE TABLE EMPLOYEE (" +
                "id BIGINT IDENTITY(1,1) PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL " +
                ");";
        try (Statement statement = DriverManager.getConnection(DB_URL, USER, PASS).createStatement()) {
            int result = statement.executeUpdate(createCustomerTableSql);
            System.out.println(result);
        } catch (SQLException e) {
            System.out.println("Error while creating database: " + e.getMessage());
            exit(-1);
        }
        return true;
    }
}
