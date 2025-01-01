package com.example.employee_db.controller;

import com.example.employee_db.entity.Employee;
import com.example.employee_db.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 if not found
    }

    // Create a new employee
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee); // 201 Created
    }

    // Update existing employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee existingEmployee = employeeService.getEmployeeById(id);
        if (existingEmployee != null) {
            existingEmployee.setName(employee.getName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setDesignation(employee.getDesignation());
            existingEmployee.setPhone(employee.getPhone());
            return ResponseEntity.ok(employeeService.saveEmployee(existingEmployee)); // 200 OK
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 if not found
    }

    // Delete employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        Employee existingEmployee = employeeService.getEmployeeById(id);
        if (existingEmployee != null) {
            employeeService.deleteEmployee(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 if not found
    }
}
