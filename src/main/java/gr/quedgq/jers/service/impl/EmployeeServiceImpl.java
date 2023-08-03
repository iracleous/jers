package gr.quedgq.jers.service.impl;

import gr.quedgq.jers.dto.EmployeeDto;
import gr.quedgq.jers.model.Employee;
import gr.quedgq.jers.repository.EmployeeRepository;
import gr.quedgq.jers.repository.impl.EmployeeRepositoryImplJdbc;
import gr.quedgq.jers.service.EmployeeService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    private EmployeeRepository employeeRepository ;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeDto.createEmployee();
        employeeRepository.save(employee);
        return new EmployeeDto(employee);
    }

    @Override
    public EmployeeDto readEmployee(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) return new EmployeeDto(employee.get());
        Employee empl = new Employee();
        empl.setId(-1);
        return new EmployeeDto(empl);
    }

    @Override
    public EmployeeDto  updateEmployee(int employeeId, EmployeeDto employeeDto){
        Employee employee = employeeDto.createEmployee();
        return new EmployeeDto (employeeRepository.update(employeeId, employee).get());
    }

    @Override
    public boolean deleteEmployee(int employeeId) {
        return employeeRepository.delete(employeeId);
    }

    @Override
    public boolean initializeDatabase() {
        return employeeRepository.createTable();
    }
}
