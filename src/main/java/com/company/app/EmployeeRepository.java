package com.company.app;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    //A dummy search call. We'll never actually call it in our test methods.
    @Query("select e from #{entityName} e where e.id = ?1")
    public Optional<Employee> findByEmployeeId(Long employeeId);
}
