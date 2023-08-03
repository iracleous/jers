package gr.quedgq.jers.repository;

import gr.quedgq.jers.model.Employee;

import java.util.Optional;

public interface EmployeeRepository {
    void save(Employee employee);

    Optional<Employee> findById(int id);

    Optional<Employee> update(int employeeId, Employee employee);

    boolean delete(int employeeId);

    boolean createTable();
}
