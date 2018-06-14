package com.company.app.service;

import java.util.Optional;

import com.company.app.Employee;
import com.company.app.EmployeeRepository;

public class MyService{
    private EmployeeRepository employeeRepository;
    
    //Setter injector. Allows us to mock the `EmployeeRepository` used in our unit tests
    public void setEmployeeRepository(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    
    //Determines if a passed employee id has the full name 'John Smith'
    public boolean isJohnSmith(Long employeeId){
        Optional<Employee> result = employeeRepository.findByEmployeeId(employeeId);
        if(!result.isPresent()){ //If no employee matches for the passed id
            return false;
        }
        Employee employee = result.get();
        if(employee.getFirstName().equals("John") && employee.getLastName().equals("Smith")){
            return true;
        }
        return false;
    }
    
    //Typically untestable private method
    private Long addSecretly(Long a, Long b){
        return a + b;
    }
}
