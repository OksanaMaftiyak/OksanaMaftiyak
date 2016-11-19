package omaftiyak.javacourse.lab2.dao;


import omaftiyak.javacourse.lab2.model.Employee;

import java.sql.*;

public class EmployeeDao implements Dao<Employee> {
    @Override
    public void persist(Employee employee) {
        try (Connection connection = ConnectionFactory.getCon()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "insert into tickets (id ,first_name,last_name, position, year_birth,salary) values (?, ?, ?, ?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            )) {
                ps.setInt(1, employee.getId());
                ps.setString(2, employee.getFirstName());
                ps.setString(3, employee.getLastName());
                ps.setString(4, employee.getPosition());
                ps.setInt(5, employee.getYearBirth());
                ps.setInt(6, employee.getSalary());

                ps.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not persist ticket", e);
        }
    }

    @Override
    public void update(Employee employee) {
        try (Connection connection = ConnectionFactory.getCon()) {
            try (PreparedStatement ps = connection.prepareStatement("update employee set first_name = ? , last_name = ? , position = ? ," +
                    " year_birth = ?,salary = ?  where id = ?")) {
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                ps.setString(3, employee.getPosition());
                ps.setInt(4, employee.getYearBirth());
                ps.setInt(3, employee.getSalary());
                ps.execute();
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not update ticket", e);
        }
    }

}
