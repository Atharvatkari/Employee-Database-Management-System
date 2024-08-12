package org.myCRUDProject.crud_project;
import org.myCRUDProject.crud_project.EmployeeService;

import java.util.ArrayList;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class EmpController {

    //EmployeeService employeeService = new EmployeeServiceImpl();

    // Dependency Injection
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("employees")
    public List<Employee> getAllEmployees(){
        return employeeService.readEmployees();
    }   
    
    @GetMapping("employees/{id}")
    public Employee getEmployeesById(@PathVariable Long id){
        System.out.println("yes mai aa raha hu ");
        return employeeService.readEmployee(id);
    }   
    
    @PostMapping("employees")
    public String createEmployee(@RequestBody Employee employee){
       return employeeService.createEmployee(employee);
    }

    @DeleteMapping("employees/{id}")
    public String deleteEmployee(@PathVariable Long id){
        if(employeeService.deleteEmployee(id))
            return "Delete Successfully";
        return "Not Found";
    }

    @PutMapping("employees/{id}")
    public String putMethodName(@PathVariable Long id , @RequestBody Employee employee){
        return employeeService.updateEmployee(id, employee);
    }
    
}
