package com.example.employee_db.repository;

import com.example.employee_db.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Custom query method following Spring Data JPA naming conventions
    List<Employee> findByDesignation(String designation);

    // Custom query using @Query annotation
    @Query("SELECT e FROM Employee e WHERE e.email = :email")
    Optional<Employee> findByEmail(@Param("email") String email);

    // Additional custom methods can be defined here
}
