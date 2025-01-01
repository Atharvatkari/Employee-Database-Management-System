
package com.example.employee_db.service;

import com.example.employee_db.entity.Employee;
import com.example.employee_db.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees");
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        logger.info("Fetching employee with id {}", id);
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            logger.error("Employee with id {} not found", id);
            throw new EntityNotFoundException("Employee not found with id " + id);
        }
    }

    @Transactional
    public Employee saveEmployee(Employee employee) {
        logger.info("Saving employee: {}", employee.getName());
        return employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        logger.info("Deleting employee with id {}", id);
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            logger.error("Employee with id {} not found", id);
            throw new EntityNotFoundException("Employee not found with id " + id);
        }
    }
}
