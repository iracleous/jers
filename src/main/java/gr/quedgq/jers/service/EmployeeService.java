package gr.quedgq.jers.service;

import gr.quedgq.jers.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employee);
    EmployeeDto  readEmployee(int id);
    EmployeeDto  updateEmployee(int employeeId, EmployeeDto employeeDto);
    boolean deleteEmployee(int employeeId);
}
